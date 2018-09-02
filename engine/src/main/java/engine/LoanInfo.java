package engine;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
        
public class LoanInfo {
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
    private final String contractAddress;
    private final Web3j web3j;
    private final Loan loan;

    public LoanInfo(final String contractAddress) throws IOException {
        this.contractAddress = contractAddress;
        this.web3j = connectToDefaultNetwork();
        this.loan = loadSmartContract();
    }
    
    private Web3j connectToDefaultNetwork() throws IOException{
        Web3j web3jLocal = Web3j.build(new HttpService()); 
        log.info("Connected to Ethereum client version");
        return web3jLocal;
    }
    
    private Loan loadSmartContract(){
        
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, contractAddress);
        
        Loan loanLocal = new Loan(
                contractAddress, web3j,
                transactionManager, 
                ManagedTransaction.GAS_PRICE, 
                Contract.GAS_LIMIT);
        log.info("Loan loaded");
        return loanLocal;
    }
    
    public String getLoanBasis() throws InterruptedException, ExecutionException{
        return loan.basis().sendAsync().get().toString();
    }

}
