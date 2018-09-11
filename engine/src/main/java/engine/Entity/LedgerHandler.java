package engine.Entity;

import engine.Ledger;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import utils.PropertiesHandler;
import com.google.gson.Gson;
import java.util.concurrent.CompletableFuture;
import org.web3j.protocol.core.RemoteCall;
        
public class LedgerHandler extends BlockchainCommunicator {
    
    private static final Logger log = LoggerFactory.getLogger(LedgerHandler.class);
    
    private String ledgerAddress;
    private final Web3j web3j;
    private final Credentials credentials;

    public LedgerHandler() throws IOException{
        super("0x0");
        web3j = null;
        credentials = null;
    }
    
    public LedgerHandler(final String privateKey) throws IOException{
        super(privateKey);
        web3j = connectToDefaultNetwork();
        credentials = loadCredentials();
    }
    
    public String createLedger() throws Exception {
        log.info("Deploying ledger smart contract");
        Ledger ledger = Ledger.deploy(
            web3j, credentials,
            ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();
        log.info("Contract deployed successfully");
        return ledger.getContractAddress();
    }

    public void setLedgerAddress(final String ledgerAddress) {
        PropertiesHandler prop = new PropertiesHandler();
        prop.setProperty("ledgerAddress", ledgerAddress);
    }

    public String getLedgerAddress() {
        PropertiesHandler prop = new PropertiesHandler();
        return prop.getProperty("ledgerAddress");
    }

    public String getAsks(final String contractAddress) throws InterruptedException, ExecutionException, Exception {
        log.info("Loading ledger");
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, contractAddress);
        
        Ledger ledger = Ledger.load(
                contractAddress, web3j,
                transactionManager, 
                ManagedTransaction.GAS_PRICE, 
                Contract.GAS_LIMIT);
        log.info("Ledger loaded");
        
        Integer count = Integer.getInteger(ledger.getAskAddressCount().send().toString());
        String[] asks = new String[count];
        for(int i = 0 ; i < count ; i++){
            asks[i] = ledger.getAskAddressAtRow(new BigInteger(String.valueOf(i))).send();
        }
        return (new Gson()).toJson(asks);
    }

    public String getBids(final String contractAddress) throws InterruptedException, ExecutionException, Exception {
        log.info("Loading ledger");
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, contractAddress);
        
        Ledger ledger = Ledger.load(
                contractAddress, web3j,
                transactionManager, 
                ManagedTransaction.GAS_PRICE, 
                Contract.GAS_LIMIT);
        log.info("Ledger loaded");
        
        RemoteCall<BigInteger> remote = ledger.getBidAddressCount();
        CompletableFuture future = remote.sendAsync();
        Integer count = Integer.getInteger(future.get().toString());
        String[] bids = new String[count];
        for(int i = 0 ; i < count ; i++){
            bids[i] = ledger.getBidAddressAtRow(new BigInteger(String.valueOf(i))).send();
        }
        return (new Gson()).toJson(bids);       
    }

}
