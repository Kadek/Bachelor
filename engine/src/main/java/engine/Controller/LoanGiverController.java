package engine.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import engine.Entity.LoanGiver;
import engine.Entity.LoanTaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@RestController
public class LoanGiverController {
 
    @Autowired
    Environment env;
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @PostMapping("/giver/offerLoan")
    public String offerLoan(
            @RequestParam(value="privateKey", defaultValue="0") String privateKey,
            @RequestParam(value="basis", defaultValue="0") String basis,
            @RequestParam(value="interest", defaultValue="0") String interest,
            @RequestParam(value="duration", defaultValue="0") String duration,
            @RequestParam(value="collateral", defaultValue="0") String collateral,
            @RequestParam(value="ledgerAddress", defaultValue="0") String ledgerAddress)
    {
        try{
            log.info("Creating bid preloan with parameters: basis={} interest={} duration={}, collateral={}"
                    , basis, interest, duration, collateral);
            return (new LoanGiver(privateKey, env))
                    .createPreloanBid(basis, interest, duration, collateral, ledgerAddress);
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @PostMapping("/taker/cancelOffer")
    public String cancelOffer(
            @RequestParam(value="privateKey", defaultValue="0") String privateKey,
            @RequestParam(value="offerAddress", defaultValue="0x0") String offerAddress)
    {
        try{
            log.info("Cancelling bid preloan with address={}", offerAddress);
            return (new LoanGiver(privateKey, env))
                    .deletePreloanBid(offerAddress);
        }catch(Exception e){
            return e.toString();
        }
    }
}
