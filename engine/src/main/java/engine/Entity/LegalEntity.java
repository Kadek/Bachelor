package engine.Entity;

import engine.Legal;
import java.io.IOException;
import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class LegalEntity extends BlockchainCommunicator {
    
    private static final Logger log = LoggerFactory.getLogger(LegalEntity.class);
    
    private final Web3j web3j;
    private final Credentials credentials;
    
    public LegalEntity() throws IOException{
        super();
        web3j = connectToDefaultNetwork();
        credentials = null;
    }
    
    public LegalEntity(final String privateKey) throws IOException{
        super(privateKey);
        web3j = connectToDefaultNetwork();
        credentials = loadCredentials();
    }
    
    public String createLegal(
            final String legalInformation,
            final String AESKey
    ) throws Exception {
        log.info("Deploying deposit smart contract");
        Legal legal = Legal.deploy(
            web3j, credentials,
            ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
            BigInteger.ZERO,
            getPublicAddress(credentials),
            legalInformation,
            AESKey
        ).send();
        log.info("Contract deployed successfully");
        return legal.getLegalAddress().send();
    }
    
    private Legal loadLegal(final String legalAddress){
        return loadContractWithoutCredentials(
                Legal.class,
                web3j, 
                legalAddress
        );
    }
    
    public String getLegalInformation(final String legalAddress) throws Exception{
        Legal legal = loadLegal(legalAddress);
        return legal.information().send();
    }
    
    public String getAESKey(final String legalAddress) throws Exception{
        Legal legal = loadLegal(legalAddress);
        return legal.AESKey().send();
    }
    
    public String getUnblockedFlag(final String legalAddress) throws Exception{
        Legal legal = loadLegal(legalAddress);
        return legal.isUnblocked().send().toString();
    }
}
