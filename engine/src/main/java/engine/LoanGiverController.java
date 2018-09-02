package engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import engine.LoanGiver;

@RestController
public class LoanGiverController {
 
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
    
    @PostMapping("/giver/checkAccount")
    public String checkAccount(@RequestParam(value="privateKey", defaultValue="0") String privateKey) {
        try{
            log.info("Checking account");
            return (new LoanGiver(privateKey)).checkAccount();
        }catch(Exception e){
            return e.toString();
        }
    } 
    
    @PostMapping("/giver/offerLoan")
    public String offerLoan(
            @RequestParam(value="privateKey", defaultValue="0") String privateKey,
            @RequestParam(value="initialValue", defaultValue="0") String initialValue) 
    {
        try{
            log.info("Offering loan with parameters: intialValue={}", initialValue);
            return (new LoanGiver(privateKey)).offerLoan(initialValue);
        }catch(Exception e){
            return e.toString();
        }
    }
}
