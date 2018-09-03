package engine.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import engine.Entity.LoanGiver;

@RestController
public class LoanGiverController {
 
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @PostMapping("/giver/offerLoan")
    public String offerLoan(
            @RequestParam(value="privateKey", defaultValue="0") String privateKey,
            @RequestParam(value="basis", defaultValue="0") String basis,
            @RequestParam(value="interest", defaultValue="0") String interest,
            @RequestParam(value="duration", defaultValue="0") String duration,
            @RequestParam(value="collateral", defaultValue="0") String collateral)
    {
        try{
            log.info("Creating bid preloan with parameters: basis={} interest={} duration={}, collateral={}"
                    , basis, interest, duration, collateral);
            return (new LoanGiver(privateKey))
                    .createPreloanBid(basis, interest, duration, collateral);
        }catch(Exception e){
            return e.toString();
        }
    }
}
