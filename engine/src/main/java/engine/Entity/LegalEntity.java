package engine.Entity;

import engine.Deposit;
import engine.Legal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import javax.crypto.Cipher;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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
            final String publicKey
    ) throws Exception {
        log.info("Deploying deposit smart contract");
        Legal legal = Legal.deploy(
            web3j, credentials,
            ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
            BigInteger.ZERO,
            getPublicAddress(credentials),
            legalInformation
        ).send();
        log.info("Contract deployed successfully");
        return legal.getLegalAddress().send();
    }
    
    private String encrypt (
            final String plainInformation,
            final String publicKey
    ){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            String filePath = new File("").getAbsolutePath();
            log.info("Searching for script in dir: {}", filePath);
            engine.eval(new FileReader("src/main/java/utils/crypto.js"));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LegalEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Invocable invocable = (Invocable)engine;
        String result = "";
        log.info("Encrypting string: {}", plainInformation);
        try {
            result = (String)invocable.invokeFunction("encrypt", plainInformation, publicKey);
        } catch (ScriptException ex) {
            java.util.logging.Logger.getLogger(LegalEntity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            java.util.logging.Logger.getLogger(LegalEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("Encrypted string into: {}", result);
        return result;
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
    
    public String getUnblockedFlag(final String legalAddress) throws Exception{
        Legal legal = loadLegal(legalAddress);
        return legal.isUnblocked().send().toString();
    }
}
