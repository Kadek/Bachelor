package engine.Entity.Engine;

import com.google.gson.Gson;
import engine.Entity.BlockchainCommunicator;
import engine.Entity.LedgerHandler;
import engine.Entity.LoanGiver;
import engine.Ledger;
import engine.Loan;
import engine.Preloan;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
        
public class Engine extends BlockchainCommunicator{

    
    private static final Logger log = LoggerFactory.getLogger(Engine.class);
    
    private final Web3j web3j;
    private final Credentials credentials;

    enum Side {
        UNDEFINED,
        ASK,
        BID
    };
    
    enum CompareState {
        NONE,
        ASK,
        BID,
        BOTH
    };
    
    public Engine(boolean connectToNetwork) throws IOException {
        super();
        web3j = connectToNetwork ? connectToDefaultNetwork() : null;
        credentials = null;
    }
    
    public Engine(final String privateKey) throws IOException {
        super(privateKey);
        web3j = connectToDefaultNetwork();
        credentials = loadCredentials();
    }

    public PreloanStructure getSortedOrders(final String ledgerAddress) {
        ArrayList<Preloan> preloans = getAllPreloans(ledgerAddress);
        PreloanStructure structuredPreloans = getStructuredPreloans(preloans);
        
        String ledgerCounter = getLedgerCounter(ledgerAddress);
        structuredPreloans.setLedgerCounter(ledgerCounter);
        return structuredPreloans;
    }    

    private PreloanStructure getStructuredPreloans(ArrayList<Preloan> preloans){
        ArrayList<String> classifiers = new ArrayList<String>() {{
            add("side");
            add("collateral");
            add("duration");
            add("");
        }};
        
        PreloanStructure root = new PreloanStructure("side");
        for(Preloan preloan : preloans){
            appendToPreloanStructure(root, preloan, classifiers);
        }
        
        return root;
    }
    
    private ArrayList<Preloan> getAllPreloans(final String ledgerAddress) {
        ArrayList<Preloan> preloans = new ArrayList<>();
        try {
            addAsks(preloans, ledgerAddress);
            addBids(preloans, ledgerAddress);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preloans;
    }
    
    private void addAsks(final ArrayList<Preloan> preloans, final String ledgerAddress) throws Exception {
        LedgerHandler ledgerHandler = new LedgerHandler(web3j);
        String[] asks = ledgerHandler.getAsks(ledgerAddress);
        for(String askAddress : asks){
            Preloan preloan = loadPreloan(askAddress);
            preloans.add(preloan);                   
        }
    }

    private void addBids(ArrayList<Preloan> preloans, final String ledgerAddress) throws Exception {
        LedgerHandler ledgerHandler = new LedgerHandler(web3j);
        String[] bids = ledgerHandler.getBids(ledgerAddress);
        for(String bidAddress : bids){
            Preloan preloan = loadPreloan(bidAddress);
            preloans.add(preloan);                   
        }
    }
    
    private Preloan loadPreloan(final String address){
        return loadContractWithoutCredentials(
                Preloan.class, 
                web3j, 
                address
        );
    }
    
    private void appendToPreloanStructure(
            final PreloanStructure root, 
            final Preloan preloan, 
            final ArrayList<String> classifiers
    ){
        PreloanStructure current = root;
        for(int i = 0; i < classifiers.size()-1; i++){
            BigInteger argument = new BigInteger("-1");
            try {
                RemoteCall<BigInteger> call = (RemoteCall<BigInteger>) Preloan.class
                        .getMethod(classifiers.get(i))
                        .invoke(preloan, null);
                argument = call.send();
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
            current = current.getChild(argument.toString(), classifiers.get(i+1));
        }
        try {
            current.addPreloan(preloan);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getLedgerCounter(String ledgerAddress){
        
        LedgerHandler ledgerHandler;
        String ledgerCounter = "";
        try {
            ledgerHandler = new LedgerHandler(web3j);
            ledgerCounter = ledgerHandler.getLedgerCounter(ledgerAddress);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ledgerCounter;
    }

    public ArrayList<MatchData> matchOrders(String orders){
        PreloanStructure preloanStructure = (new Gson()).fromJson(orders, PreloanStructure.class);
        ArrayList<MatchData> matchStructure = new ArrayList<>();
        
        PreloanStructure asks = preloanStructure.getChild("1", "");
        Set<String> askCollaterals = asks.getChildren().keySet();
        PreloanStructure bids = preloanStructure.getChild("2", "");
        Set<String> bidCollaterals = bids.getChildren().keySet();
        
        Iterator collateralIterator = askCollaterals.iterator();
        while(collateralIterator.hasNext()){
            String collateral = (String)collateralIterator.next();
            if(bidCollaterals.contains(collateral)){
                matchStructure.addAll(
                    secondMatchStep(asks.getChild(collateral, ""), bids.getChild(collateral, ""))
                );
            }
        }
        return matchStructure;
    }
    
    private ArrayList<MatchData> secondMatchStep(PreloanStructure asks, PreloanStructure bids){
        Set<String> askDurations = asks.getChildren().keySet();
        Set<String> bidDurations = bids.getChildren().keySet();
        
        Iterator durationIterator = askDurations.iterator();
        ArrayList<MatchData> matches = new ArrayList<>();
        while(durationIterator.hasNext()){
            String duration = (String)durationIterator.next();
            if(bidDurations.contains(duration)){
                ArrayList<MatchData> match = actualMatch(
                    asks.getChild(duration, "").getPreloans(),
                    bids.getChild(duration, "").getPreloans()
                );
                if(match != null)
                    matches.addAll(match);
            }
        }
        return matches;
    }
    
    private ArrayList<MatchData> actualMatch(
        final PriorityQueue<PreloanData> asks,
        final PriorityQueue<PreloanData> bids
    ){
        Iterator asksIterator = asks.iterator();
        Iterator bidsIterator = bids.iterator();
        ArrayList<MatchData> matches = new ArrayList<>();
        
        boolean flag = true;
        PreloanData ask = null;
        PreloanData bid = null;
        while(flag){
            if(ask == null && asksIterator.hasNext()){
                ask = (PreloanData)asksIterator.next();
            }
            if(bid == null && bidsIterator.hasNext()){
                bid = (PreloanData)bidsIterator.next();
            }
            
            if(ask == null || bid == null){
                flag = false;
                continue;
            }
            
            CompareState compareState = compareSides(ask, bid, matches);
            if(compareState == CompareState.BOTH){
                bid = null;
                ask = null;
            }else if(compareState == CompareState.ASK){
                bid.basis.subtract(ask.basis);
                ask = null;
            }else if(compareState == CompareState.BID){    
                ask.basis.subtract(bid.basis);
                bid = null;
            }else{
                flag = false;
            }
            
        }
        
        return matches;
    }
    
    private CompareState compareSides(
        PreloanData ask, 
        PreloanData bid,
        ArrayList<MatchData> matches
    ){
        final String bidAddress = bid.address;
        final String askAddress = ask.address;
        final String bidBasis = bid.basis.toString();
        final String askBasis = ask.basis.toString();
        
        if(bid.interest.compareTo(ask.interest) <= 0){
            
            matches.add(
                new MatchData(
                    bidAddress, bidBasis,
                    askAddress, askBasis
                )
            );
            
            if(bid.basis.compareTo(ask.basis) == 0){
                return CompareState.BOTH;
            }else if(bid.basis.compareTo(ask.basis) > 0){
                return CompareState.ASK;
            }else{
                return CompareState.BID;
            }
        }else{
            return CompareState.NONE;
        }
    }
    
    public ArrayList<String> createLoans(TransactionStructure transactions){
        ArrayList<String> addresses = new ArrayList<>();
        
        log.info("Creating loans");
        
        String result = isTransactionConsistent(transactions.getLedgerCounter(), transactions.getLedgerAddress());
        if(!result.equals("")){
            addresses.add(result);
            return addresses;
        }
        log.info("Transaction {} is consistent.", transactions.getLedgerCounter());
        
        ArrayList<HashMap<String, String>> transactionsRecords = transactions.getTransactions();
        for(HashMap<String, String> transaction : transactionsRecords){
            addresses.add(createLoan(transaction, transactions.getLedgerAddress()));
        }
        log.info("Loans successfully created.");
        
        return addresses;
    }
    
    private String isTransactionConsistent(String ledgerCounter, String ledgerAddress){
        LedgerHandler ledgerHandler;
        String currentLedgerCounter;
        try {
            ledgerHandler = new LedgerHandler(web3j);
            currentLedgerCounter = ledgerHandler.getLedgerCounter(ledgerAddress);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            return "Can't check current transaction number";
        }
        if(ledgerCounter.equals(currentLedgerCounter)){
            return "";
        }else{
            return "Current transaction number ("+currentLedgerCounter+") differs from the used one ("+ledgerCounter+")";
        }
    }
    
    private String createLoan(final HashMap<String, String> transaction, final String ledgerAddress){
        String bidAddress = transaction.get("bidAddress");
        String askAddress = transaction.get("askAddress");
        Preloan bid;
        Preloan ask;
        
        log.info("Deploying loan.");
        String loanAddress;
        try {
            bid = (new LoanGiver(web3j, credentials)).loadPreloan(bidAddress);
            ask = (new LoanGiver(web3j, credentials)).loadPreloan(askAddress);
            
            bid.createLoan(askAddress).send();
            loanAddress = bid.loanAddress().send();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            return "Couldn't create loan.";
        }
        log.info("Loan deployed.");
        
        log.info("Clearing ledger.");
        LedgerHandler ledgerHandler;
        try {
            ledgerHandler = new LedgerHandler(web3j, credentials);
            Ledger ledger = ledgerHandler.loadLedgerWithCredentials(ledgerAddress);
            if(bid.basis().send().compareTo(BigInteger.ZERO) == 0 ){
                BigInteger index = ledgerHandler.findBidIndex(ledgerAddress, bidAddress);
                ledger.deleteBid(index).send();
            }
            if(ask.basis().send().compareTo(BigInteger.ZERO) == 0 ){
                BigInteger index = ledgerHandler.findAskIndex(ledgerAddress, askAddress);
                ledger.deleteAsk(index).send();
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }        
        log.info("Ledger cleared.");
        
        return loanAddress;
    }
}
