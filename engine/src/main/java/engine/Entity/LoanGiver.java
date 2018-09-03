package engine.Entity;

import engine.Preloan;
import java.io.IOException;
import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
        
public class LoanGiver extends BlockchainCommunicator{
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
    private final Web3j web3j;
    private final Credentials credentials;
    
    enum Side {
        BID,
        ASK
    };
    
    public LoanGiver(final String privateKey) throws IOException {
        super(privateKey);
        this.web3j = connectToDefaultNetwork();
        this.credentials = loadCredentials();
    }
    
    public String createPreloanBid(
            final String basis,
            final String interest,
            final String duration,
            final String collateral
    ) throws Exception{  
        String contractAddress = deployLoanOffer();
        setParameters(contractAddress, basis, interest, duration, collateral);        
        
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
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, contractAddress);
        
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
        
        String sideOrdinal = ((Integer)(Side.BID).ordinal()).toString();
        preLoan.setSide(new BigInteger(sideOrdinal));
        log.info("Successfully set parameters for preloan bid.");        
    }
    
    private BigInteger getInterestScaled(String interest){
        return (new BigInteger(interest)).multiply(new BigInteger(scale));
    }
    
    private BigInteger getInterestReciprocal(String interest){
        return (BigInteger.ONE).divide(new BigInteger(interest));
    }
}
