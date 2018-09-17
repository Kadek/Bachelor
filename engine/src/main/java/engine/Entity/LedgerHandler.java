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
import utils.PropertiesHandler;

public class LedgerHandler extends BlockchainCommunicator {
    
    private static final Logger log = LoggerFactory.getLogger(LedgerHandler.class);
    
    private final Web3j web3j;
    private final Credentials credentials;

    public LedgerHandler() throws IOException{
        super();
        web3j = connectToDefaultNetwork();
        credentials = null;
    }
    
    public LedgerHandler(final String privateKey) throws IOException{
        super(privateKey);
        web3j = connectToDefaultNetwork();
        credentials = loadCredentials();
    }
    
    public LedgerHandler(Web3j web3j) throws IOException{
        super();
        this.web3j = web3j;
        credentials = null;
    }
    
    public LedgerHandler(Web3j web3j, Credentials credentials) throws IOException{
        super();
        this.web3j = web3j;
        this.credentials = credentials;
    }
    
    public String createLedger() throws Exception {
        log.info("Deploying ledger smart contract");
        Ledger ledger = Ledger.deploy(
            web3j, credentials,
            ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();
        log.info("Contract deployed successfully");
        return ledger.getContractAddress();
    }
    
    public Ledger loadLedger(final String contractAddress) throws Exception {
        return loadContractWithoutCredentials(
                Ledger.class, 
                web3j, 
                contractAddress
        );
    }
    
    public Ledger loadLedgerWithCredentials(final String contractAddress) throws Exception {
        return loadContractWithCredentials(
                Ledger.class, 
                credentials,
                web3j, 
                contractAddress
        );
    }

    public void setLedgerAddress(final String ledgerAddress) {
        PropertiesHandler prop = new PropertiesHandler();
        prop.setProperty("ledgerAddress", ledgerAddress);
    }

    public String getLedgerAddress() {
        PropertiesHandler prop = new PropertiesHandler();
        return prop.getProperty("ledgerAddress");
    }

    public String[] getAsks(final String contractAddress) throws InterruptedException, ExecutionException, Exception {
        Ledger ledger = loadLedger(contractAddress);
        
        Integer count = Integer.parseInt(ledger.getAskAddressCount().send().toString());
        String[] asks = new String[count];
        for(int i = 0 ; i < count ; i++){
            asks[i] = ledger.getAskAddressAtRow(new BigInteger(String.valueOf(i))).send();
        }
        return asks;
    }

    public String[] getBids(final String contractAddress) throws InterruptedException, ExecutionException, Exception {
        Ledger ledger = loadLedger(contractAddress);
                
        Integer count = Integer.parseInt(ledger.getBidAddressCount().send().toString());
        String[] bids = new String[count];
        for(int i = 0 ; i < count ; i++){
            bids[i] = ledger.getBidAddressAtRow(new BigInteger(String.valueOf(i))).send();
        }
        return bids;
    }
    
    public BigInteger findAskIndex(final String ledgerAddress, final String askAddress) throws Exception{
        Ledger ledger = loadLedger(ledgerAddress);
        
        Integer count = Integer.parseInt(ledger.getAskAddressCount().send().toString());
        for(int i = 0 ; i < count ; i++){
            String currentAddress = ledger.getAskAddressAtRow(new BigInteger(String.valueOf(i))).send();
            if(currentAddress.equals(askAddress))
                return new BigInteger(String.valueOf(i));
        }
        return new BigInteger(String.valueOf(-1));        
    }
    
    public BigInteger findBidIndex(final String ledgerAddress, final String bidAddress) throws Exception{
        Ledger ledger = loadLedger(ledgerAddress);
        
        Integer count = Integer.parseInt(ledger.getBidAddressCount().send().toString());
        for(int i = 0 ; i < count ; i++){
            String currentAddress = ledger.getBidAddressAtRow(new BigInteger(String.valueOf(i))).send();
            if(currentAddress.equals(bidAddress))
                return new BigInteger(String.valueOf(i));
        }
        return new BigInteger(String.valueOf(-1));        
    }
    
    public String getLedgerCounter(final String ledgerAddress) throws Exception {
        Ledger ledger = loadLedger(ledgerAddress);    
        return ledger.counter().send().toString();
    }

}
