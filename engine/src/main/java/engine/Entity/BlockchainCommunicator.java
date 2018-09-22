package engine.Entity;

import engine.Preloan;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Numeric;

public class BlockchainCommunicator {
    
    protected String privateKey;              
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
    protected BlockchainCommunicator(){
        this.privateKey = "0x0";
    }
    
    protected BlockchainCommunicator(final String privateKey){
        this.privateKey = privateKey;
    }
    
    protected Web3j connectToDefaultNetwork() throws IOException{
        log.info("Connecting to ethereum network.");
        Web3j web3jLocal = Web3j.build(new HttpService()); 
        log.info("Connected to Ethereum client version");
        
        return web3jLocal;
    }
    
    protected Credentials loadCredentials(){
        Credentials credentialsLocal =
                Credentials.create(this.privateKey);
        log.info("Credentials loaded");
        return credentialsLocal;
    }
    
    protected <T extends Contract> T loadContractWithCredentials(
            Class T,
            Credentials credentials,
            Web3j web3j,
            String address
    ){
        log.info("Loading {}.", T.toString());
        T contract = null;
        
        try {
            Method load = T.getMethod("load",
                String.class, Web3j.class,
                Credentials.class,
                BigInteger.class,
                BigInteger.class
            );
            contract = (T)load.invoke(T, 
                address, web3j,
                credentials, 
                ManagedTransaction.GAS_PRICE, 
                Contract.GAS_LIMIT);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BlockchainCommunicator.class.getName()).log(Level.SEVERE, null, ex);
        };
        
        log.info("{} successfully loaded.", T.toString());
        return contract;
    }   
    
    protected <T extends Contract> T loadContractWithoutCredentials(
            Class T,
            Web3j web3j,
            String address
    ){
        log.info("Loading {}.", T.toString());
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, address);
        T contract = null;
        
        try {
            Method load = T.getMethod("load",
                String.class, Web3j.class,
                TransactionManager.class,
                BigInteger.class,
                BigInteger.class
            );
            contract = (T)load.invoke(T, 
                address, web3j,
                transactionManager, 
                ManagedTransaction.GAS_PRICE, 
                Contract.GAS_LIMIT
            );
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BlockchainCommunicator.class.getName()).log(Level.SEVERE, null, ex);
        };
        
        log.info("{} successfully loaded.", T.toString());
        return contract;
    }
    
    protected String getPublicAddress(Credentials credentials){
        return credentials.getAddress();
    }
    
    protected String getPublicKey(Credentials credentials){
        return credentials.getEcKeyPair().getPublicKey().toString();
    }
    
    protected void sendFunds(
            final String contractAddress, final String basis,
            Web3j web3j, Credentials credentials
    ) throws InterruptedException, ExecutionException{
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
            credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
            nonce, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT, contractAddress, new BigInteger(basis));
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
    }
   
}
