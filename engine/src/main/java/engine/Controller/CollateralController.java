package engine.Controller;

import com.google.gson.Gson;
import engine.Entity.DepositEntity;
import engine.Entity.LegalEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CollateralController {
 
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @PostMapping("/collateral/createDeposit")
    public String createDeposit(
            @RequestBody String collateralFormJson) 
    {
        try{
            CollateralForm collateralForm = (new Gson()).fromJson(collateralFormJson, CollateralForm.class);
            log.info("Creating deposit for address {}", collateralForm.getTaker());
            return (new DepositEntity(collateralForm.getPrivateKey())).createDeposit(
                    collateralForm.getTaker(), collateralForm.getDepositValue());
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @PostMapping("/collateral/createLegal")
    public String createLegal(
            @RequestBody String collateralFormJson) 
    {
        try{
            CollateralForm collateralForm = (new Gson()).fromJson(collateralFormJson, CollateralForm.class);
            log.info("Creating legal collateral for address {}", collateralForm.getTaker());
            return (new LegalEntity(collateralForm.getPrivateKey())).createLegal(
                    collateralForm.getLegalInformation(), collateralForm.getLegalAESKey());
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @GetMapping("/collateral/getLegalInformation")
    public String getLegalInformation(
            @RequestParam(value="legalAddress", defaultValue="0x0") String legalAddress) 
    {
        try{
            log.info("Getting legal information from address {}", legalAddress);
            return (new LegalEntity()).getLegalInformation(legalAddress);
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @GetMapping("/collateral/getUnblockedFlag")
    public String getUnblockedFlag(
            @RequestParam(value="legalAddress", defaultValue="0x0") String legalAddress) 
    {
        try{
            log.info("Getting unblocked flag from address {}", legalAddress);
            return (new LegalEntity()).getUnblockedFlag(legalAddress);
        }catch(Exception e){
            return e.toString();
        }
    }
    
    
    private class CollateralForm {

        public String getTaker() {
            return taker;
        }
        public String getPrivateKey() {
            return privateKey;
        }
        public String getDepositValue() {
            return depositValue;
        }
        public String getLegalInformation() {
            return legalInformation;
        }
        public String getLegalAESKey() {
            return legalAESKey;
        }
        
        private String taker;
        private String privateKey;
        private String depositValue;
        private String legalInformation;
        private String legalAESKey;

    }
}
