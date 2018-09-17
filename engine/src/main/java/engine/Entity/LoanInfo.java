package engine.Entity;

import engine.Loan;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
        
public class LoanInfo extends BlockchainCommunicator {
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
    private final String contractAddress;
    private final Web3j web3j;
    private final Loan loan;

    public LoanInfo(final String contractAddress) throws IOException{
        super();
        this.contractAddress = contractAddress;
        this.web3j = connectToDefaultNetwork();
        this.loan = loadLoan();
    }
    
    private Loan loadLoan(){
        return loadContractWithoutCredentials(
            Loan.class,
            web3j, 
            contractAddress
        );
    }
    
    public String getBasis() throws InterruptedException, ExecutionException{
        return loan.basis().sendAsync().get().toString();
    }

    public String getInterestScaled() throws Exception {
        return loan.interestScaled().send().toString();
    }

    public String getDuration() throws Exception {
        return loan.duration().send().toString();
    }

    public String getRepayment() throws Exception {
        return loan.repayment().send().toString();
    }

    public String getLatestPaymentDate() throws Exception {
        return (new Date(Long.parseLong(loan.latestPaymentTimestamp().send().toString()))).toString();
    }

    public String getPaymentCount() throws Exception {
        return loan.paymentCount().send().toString();
    }

    public String getAccountAmount() throws IOException {
        return web3j.ethGetBalance(contractAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString();
    }

}
