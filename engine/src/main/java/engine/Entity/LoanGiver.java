package engine.Entity;

import engine.Loan;
import engine.Preloan;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
        
public class LoanGiver extends BlockchainCommunicator{

    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
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
    
    public LoanGiver(final Web3j web3j, final Credentials credentials) throws IOException {
        super();
        this.web3j = web3j;
        this.credentials = credentials;
        
        precision = null;
        scale = null;
        paymentPeriod = null;
    }
    
    public LoanGiver(final String privateKey, final Environment env) throws IOException {
        super(privateKey);
        web3j = connectToDefaultNetwork();
        credentials = loadCredentials();
        
        precision = env.getProperty("loan.precision");
        scale = env.getProperty("loan.scale");
        paymentPeriod = env.getProperty("loan.paymentPeriod");
    }
    
    public String createPreloanBid(
            final String basis,
            final String interest,
            final String duration,
            final String collateral,
            final String ledgerAddress
    ) throws Exception{  
        String contractAddress = deployLoanOffer();
        setParameters(contractAddress, basis, interest, duration, collateral);        
        informLedger(contractAddress, ledgerAddress);
        
        return contractAddress;
    }
    
    private String deployLoanOffer() throws Exception {
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
        log.info("Setting parameters for preloan bid.");
        
        Preloan preLoan = Preloan.load(
                contractAddress, web3j,
                credentials, 
                ManagedTransaction.GAS_PRICE, 
                Contract.GAS_LIMIT);
                
        String sideOrdinal = ((Integer)(Side.BID).ordinal()).toString();
        preLoan.setSide(new BigInteger(sideOrdinal)).send();
        
        sendFunds(contractAddress, basis, web3j, credentials);
        
        preLoan.setInterestScaled(getInterestScaled(interest)).send();
        preLoan.setInterestReciprocal(getInterestReciprocal(interest)).send();
        preLoan.setScale(new BigInteger(scale)).send();
        preLoan.setPrecision(new BigInteger(precision)).send();
        preLoan.setDuration(new BigInteger(duration)).send();
        preLoan.setPaymentPeriod(new BigInteger(paymentPeriod)).send();
        preLoan.setCollateral(new BigInteger(collateral), "0x0").send();
        
        log.info("Successfully set parameters for preloan bid.");        
    }
    
    private BigInteger getInterestScaled(String interest){
        return (new BigDecimal(interest)).multiply(new BigDecimal(scale)).toBigInteger();
    }
    
    private BigInteger getInterestReciprocal(String interest){
        return (BigDecimal.ONE).divide(new BigDecimal(interest)).toBigInteger();
    }
        
    private void informLedger(String contractAddress, String ledgerAddress) throws Exception {
        log.info("Informing ledger of a new bid offer.");
        
        Preloan preLoan = loadPreloan(contractAddress);
        preLoan.informLedger(ledgerAddress).send();
        
        log.info("Ledger informed.");        
    }
    
    public String deletePreloanBid(final String bidAddress, final String ledgerAddress) throws Exception {
        log.info("Cancelling preloan bid and informing ledger.");
        
        Preloan preloan = loadPreloan(bidAddress);
        BigInteger index = findBidIndex(bidAddress, ledgerAddress);
        preloan.cancelLoanOffer(index).send();
        
        log.info("Preloan cancelled and Ledger informed.");    
        return "Success";
    }
    
    public Preloan loadPreloan(final String bidAddress){
        return loadContractWithCredentials(
                Preloan.class, 
                credentials, 
                web3j, 
                bidAddress
        );
    }

    private BigInteger findBidIndex(final String bidAddress, final String ledgerAddress) throws Exception {
        LedgerHandler ledgerHandler = new LedgerHandler();
        return ledgerHandler.findBidIndex(ledgerAddress, bidAddress);
    }
    
    public String consumeRepayment(String loanAddress) {
        log.info("Consuming repayment");
        try {
            String publicAddress = getPublicAddress(credentials);
            String balance = web3j.ethGetBalance(publicAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
            log.info("Balance of address {} before consumption is {}", publicAddress, balance);
            balance = web3j.ethGetBalance(loanAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
            log.info("Balance of a loan on address {} before consumption is {}", loanAddress, balance);

            Loan loan = loadLoan(loanAddress);
            loan.consumeRepayment(BigInteger.ZERO).send();            
            log.info("Repayment consumed successfully");

            balance = web3j.ethGetBalance(publicAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
            log.info("Balance of address {} after consumption is {}", publicAddress, balance);
            balance = web3j.ethGetBalance(loanAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
            log.info("Balance of a loan on address {} after consumption is {}", loanAddress, balance);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoanGiver.class.getName()).log(Level.SEVERE, null, ex);
            return "Failure";
        }
        return "Success";
    }   
    
    private Loan loadLoan(final String loanAddress){
        return loadContractWithCredentials(
            Loan.class,
            credentials,
            web3j, 
            loanAddress
        );
    }
    
}
