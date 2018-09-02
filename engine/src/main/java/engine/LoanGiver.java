package engine;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
        
public class LoanGiver {
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
    private final String privateKey;
    private final Web3j web3j;
    private final Credentials credentials;

    public LoanGiver(final String privateKey) throws IOException {
        this.privateKey = privateKey;
        this.web3j = connectToDefaultNetwork();
        this.credentials = loadCredentials();
    }
    
    private Web3j connectToDefaultNetwork() throws IOException{
        log.info("Connecting to ethereum network.");
        Web3j web3jLocal = Web3j.build(new HttpService()); 
        log.info("Connected to Ethereum client version");
        
        return web3jLocal;
    }
    
    private Credentials loadCredentials(){
        Credentials credentialsLocal =
                Credentials.create(this.privateKey);
        log.info("Credentials loaded");
        return credentialsLocal;
    }

    public String checkAccount() throws IOException, InterruptedException, ExecutionException{
        log.info("Checking account for address {}", credentials.getAddress());
        EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        return ethGetBalance.getBalance().toString();
    }
    
    public String offerLoan(String initialValue) throws Exception{  
        String contractAddress = deployLoanOffer(initialValue);
        
        log.info("Starting matching engine");        
        runEngine();
        
        return contractAddress;
    }
    
    private String deployLoanOffer(String initialValue) throws Exception {
        log.info("Deploying loan smart contract with intial value {}", initialValue);
        Loan contract = Loan.deploy(
            web3j, credentials,
            ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
            new BigInteger(initialValue)).send();
        log.info("Contract deployed successfully");
        return contract.getContractAddress();
    }
    
    private void runEngine(){
    }
}
