package engine.Entity;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class BlockchainCommunicator {
    
    protected String privateKey;
    protected final String precision;
    protected final String scale;
    protected final String paymentPeriod;               
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiver.class);
    
    protected BlockchainCommunicator(final String privateKey){
        this.privateKey = privateKey;
        this.precision = "8";
        this.scale = "10000000000";
        this.paymentPeriod = "155520000"; // 30 * 5184000 = days * seconds in one day
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
}
