package engine.Entity.Engine;

import engine.Entity.BlockchainCommunicator;
import engine.Entity.LedgerHandler;
import engine.Preloan;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
        
public class Engine extends BlockchainCommunicator{

    
    private static final Logger log = LoggerFactory.getLogger(Engine.class);
    
    private final Web3j web3j;
    private final Credentials credentials;

    enum Side {
        UNDEFINED,
        ASK,
        BID
    };
    
    public Engine() throws IOException {
        super();
        web3j = connectToDefaultNetwork();
        credentials = null;
    }

    public PreloanStructure getSortedOrders(final String ledgerAddress) {
        ArrayList<Preloan> preloans = getAllPreloans(ledgerAddress);
        PreloanStructure structuredPreloans = getStructuredPreloans(preloans);
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
                RemoteCall<BigInteger> call = (RemoteCall<BigInteger>) preloan.getClass().getMethod(classifiers.get(i)).invoke(null, null);
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

}
