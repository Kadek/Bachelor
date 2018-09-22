package engine.Controller;

import com.google.gson.Gson;
import engine.Entity.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UtilsController {
 
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @PostMapping("/utils/getPublicKey")
    public String getPublicKey(
            @RequestBody String privateKey) 
    {
        try{
            log.info("Getting public key");
            return (new Utils()).getPublicKey(privateKey);
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @PostMapping("/utils/generatePrivateKey")
    public String generatePrivateKey() 
    {
        try{
            log.info("Generating private key");
            return (new Utils()).generatePrivateKey();
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @PostMapping("/utils/generateAES")
    public String generateAES() 
    {
        try{
            log.info("Generating AES key");
            return (new Utils()).generateAESKey();
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @PostMapping("/utils/RSAdecrypt")
    public String RSAdecrypt(
            @RequestBody String cryptoFormJson) 
    {
        try{
            CryptoForm cryptoForm = (new Gson()).fromJson(cryptoFormJson, CryptoForm.class);
            log.info("Decrypting string with RSA");
            return (new Utils()).RSAdecrypt(cryptoForm.getKey(), cryptoForm.getInformation());
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @PostMapping("/utils/RSAencrypt")
    public String RSAencrypt(
            @RequestBody String cryptoFormJson) 
    {
        try{
            CryptoForm cryptoForm = (new Gson()).fromJson(cryptoFormJson, CryptoForm.class);
            log.info("Encrypting string with RSA");
            return (new Utils()).RSAencrypt(cryptoForm.getKey(), cryptoForm.getInformation());
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @PostMapping("/utils/AESencrypt")
    public String AESencrypt(
            @RequestBody String cryptoFormJson) 
    {
        try{
            CryptoForm cryptoForm = (new Gson()).fromJson(cryptoFormJson, CryptoForm.class);
            log.info("Encrypting string with AES");
            return (new Utils()).AESencrypt(cryptoForm.getKey(), cryptoForm.getInformation());
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @PostMapping("/utils/AESdecrypt")
    public String AESdecrypt(
            @RequestBody String cryptoFormJson) 
    {
        try{
            CryptoForm cryptoForm = (new Gson()).fromJson(cryptoFormJson, CryptoForm.class);
            log.info("Decrypting string with AES");
            return (new Utils()).AESdecrypt(cryptoForm.getKey(), cryptoForm.getInformation());
        }catch(Exception e){
            return e.toString();
        }
    }
    
    class CryptoForm{
        private String key;
        private String information;

        public String getKey() {
            return key;
        }

        public String getInformation() {
            return information;
        }
    }
}
