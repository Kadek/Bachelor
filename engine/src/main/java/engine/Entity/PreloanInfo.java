package engine.Entity;

import engine.Preloan;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
        
public class PreloanInfo extends BlockchainCommunicator {
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
    private final String contractAddress;
    private final Web3j web3j;
    private final Preloan preLoan;

    public PreloanInfo(final String contractAddress) throws IOException{
        super();
        this.contractAddress = contractAddress;
        this.web3j = connectToDefaultNetwork();
        this.preLoan = loadPreloan();
    }
    
    private Preloan loadPreloan(){
        return loadContractWithoutCredentials(
                Preloan.class,
                web3j, 
                contractAddress
        );
    }
    
    public String getPreloanBasis() throws InterruptedException, ExecutionException{
        return preLoan.basis().sendAsync().get().toString();
    }

    public String getLedgerAddress() throws InterruptedException, ExecutionException {
        return preLoan.ledgerAddress().sendAsync().get();
    }

    public String getSide() throws InterruptedException, ExecutionException {
        return preLoan.side().sendAsync().get().toString();
    }

}
