package engine.Entity;

import engine.Preloan;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
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
            final String ledgerAddress,
            final String collateralAddress
    ) throws Exception{  
        String contractAddress = deployLoanRequest();
        setParameters(contractAddress, basis, interest, duration, collateral, collateralAddress);        
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
            final String collateral,
            final String collateralAddress
    ) throws Exception{
        log.info("Setting parameters for preloan ask.");
        
        Preloan preLoan = Preloan.load(
                contractAddress, web3j,
                credentials, 
                ManagedTransaction.GAS_PRICE, 
                Contract.GAS_LIMIT);
        
        
        String sideOrdinal = ((Integer)(Side.ASK).ordinal()).toString();
        preLoan.setSide(new BigInteger(sideOrdinal)).send();
        preLoan.setBasis(new BigInteger(basis)).send();
        
        preLoan.setInterestScaled(getInterestScaled(interest)).send();
        preLoan.setInterestReciprocal(getInterestReciprocal(interest)).send();
        preLoan.setScale(new BigInteger(scale)).send();
        preLoan.setPrecision(new BigInteger(precision)).send();
        preLoan.setDuration(new BigInteger(duration)).send();
        preLoan.setPaymentPeriod(new BigInteger(paymentPeriod)).send();
        BigInteger collateralBig = new BigInteger(collateral);
        preLoan.setCollateral(collateralBig,  collateralAddress).send();
        log.info("Successfully set parameters for preloan ask.");        
    }
    
    private BigInteger getInterestScaled(String interest){
        return (new BigDecimal(interest)).multiply(new BigDecimal(scale)).toBigInteger();
    }
    
    private BigInteger getInterestReciprocal(String interest){
        return (BigDecimal.ONE).divide(new BigDecimal(interest)).toBigInteger();
    }
        
    private void informLedger(String contractAddress, String ledgerAddress) throws Exception {
        log.info("Informing ledger of a new ask offer.");
        
        Preloan preloan = loadPreloan(contractAddress);
        preloan.informLedger(ledgerAddress).send();
        
        log.info("Ledger informed.");        
    }
    
    public String deletePreloanAsk(final String contractAddress, final String ledgerAddress) throws Exception {
        log.info("Cancelling preloan ask and informing ledger.");
        
        Preloan preloan = loadPreloan(contractAddress);
        BigInteger index = findAskIndex(contractAddress, ledgerAddress);
        preloan.cancelLoanOffer(index).send();
        
        log.info("Preloan cancelled and Ledger informed.");    
        return "Success";
    }
    
    private Preloan loadPreloan(final String askAddress){
        return loadContractWithCredentials(
                Preloan.class, 
                credentials, 
                web3j, 
                askAddress
        );
    }

    private BigInteger findAskIndex(final String ledgerAddress, final String askAddress) throws Exception {
        LedgerHandler ledgerHandler = new LedgerHandler();
        return ledgerHandler.findAskIndex(ledgerAddress, askAddress);
    }
    
    public String fillRepaymentAccount(String loanAddress, String amount) {
        log.info("Filling repayment account");
        try {
            String publicAddress = getPublicAddress(credentials);
            String balance = web3j.ethGetBalance(publicAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
            log.info("Balance of address {} before filling is {}", publicAddress, balance);
            balance = web3j.ethGetBalance(loanAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
            log.info("Balance of a loan on address {} before filling is {}", loanAddress, balance);
            
            sendFunds(loanAddress, amount, web3j, credentials);
            log.info("Repayment account filled successfully");

            balance = web3j.ethGetBalance(publicAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
            log.info("Balance of address {} after filling is {}", publicAddress, balance);
            balance = web3j.ethGetBalance(loanAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
            log.info("Balance of a loan on address {} after filling is {}", loanAddress, balance);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoanTaker.class.getName()).log(Level.SEVERE, null, ex);
            return "Failure";
        }
        
        return "Success";
    }
}
