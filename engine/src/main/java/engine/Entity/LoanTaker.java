package engine.Entity;

import engine.Preloan;
import java.io.IOException;
import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
        
public class LoanTaker extends BlockchainCommunicator{

    
    private static final Logger log = LoggerFactory.getLogger(LoanTaker.class);
    
    private final Web3j web3j;
    private final Credentials credentials;
    
    private final String precision;
    private final String scale;
    private final String paymentPeriod;
    
    enum Side {
        UNDEFINED,
        ASK,
        BID
    };
    
    public LoanTaker(final String privateKey, final Environment env) throws IOException {
        super(privateKey);
        web3j = connectToDefaultNetwork();
        credentials = loadCredentials();
        
        precision = env.getProperty("loan.precision");
        scale = env.getProperty("loan.scale");
        paymentPeriod = env.getProperty("loan.paymentPeriod");
    }
    
    public String createPreloanAsk(
            final String basis,
            final String interest,
            final String duration,
            final String collateral,
            final String ledgerAddress
    ) throws Exception{  
        String contractAddress = deployLoanRequest();
        setParameters(contractAddress, basis, interest, duration, collateral);        
        informLedger(contractAddress, ledgerAddress);
        
        return contractAddress;
    }
    
    private String deployLoanRequest() throws Exception {
        log.info("Deploying preloan smart contract with intial value");
        Preloan contract = Preloan.deploy(
            web3j, credentials,
            ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
            new BigInteger("0")).send();
        log.info("Contract deployed successfully");
        return contract.getContractAddress();
    }
    
    private void setParameters(
            final String contractAddress,
            final String basis,
            final String interest,
            final String duration,
            final String collateral
    ) throws Exception{
        log.info("Setting parameters for preloan ask.");
        
        Preloan preLoan = Preloan.load(
                contractAddress, web3j,
                credentials, 
                ManagedTransaction.GAS_PRICE, 
                Contract.GAS_LIMIT);
        
        preLoan.setBasis(new BigInteger(basis)).send();
        preLoan.setInterestScaled(getInterestScaled(interest)).send();
        preLoan.setInterestReciprocal(getInterestReciprocal(interest)).send();
        preLoan.setScale(new BigInteger(scale)).send();
        preLoan.setPrecision(new BigInteger(precision)).send();
        preLoan.setDuration(new BigInteger(duration)).send();
        preLoan.setPaymentPeriod(new BigInteger(paymentPeriod)).send();
        preLoan.setCollateral(new BigInteger(collateral)).send();
        
        String sideOrdinal = ((Integer)(Side.ASK).ordinal()).toString();
        preLoan.setSide(new BigInteger(sideOrdinal)).send();
        log.info("Successfully set parameters for preloan ask.");        
    }
    
    private BigInteger getInterestScaled(String interest){
        return (new BigInteger(interest)).multiply(new BigInteger(scale));
    }
    
    private BigInteger getInterestReciprocal(String interest){
        return (BigInteger.ONE).divide(new BigInteger(interest));
    }
        
    private void informLedger(String contractAddress, String ledgerAddress) throws Exception {
        log.info("Informing ledger of a new ask offer.");
        
        Preloan preloan = loadPreloan(contractAddress);
        preloan.informLedger(ledgerAddress).send();
        
        log.info("Ledger informed.");        
    }
    
    public String deletePreloanAsk(String contractAddress) throws Exception {
        log.info("Cancelling preloan ask and informing ledger.");
        
        Preloan preloan = loadPreloan(contractAddress);
        BigInteger index = findAskIndex(contractAddress);
        preloan.cancelLoanOffer(index).send();
        
        log.info("Preloan cancelled and Ledger informed.");    
        return "Success";
    }
    
    private Preloan loadPreloan(final String contractAddress){
        return loadContractWithCredentials(
                Preloan.class, 
                credentials, 
                web3j, 
                contractAddress
        );
    }

    private BigInteger findAskIndex(String askAddress) throws Exception {
        LedgerHandler ledgerHandler = new LedgerHandler();
        return ledgerHandler.findAskIndex(askAddress);
    }
}
