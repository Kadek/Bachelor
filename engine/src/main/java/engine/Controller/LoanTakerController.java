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
public class LoanTakerController {
 
    @Autowired
    Environment env;
    
    private static final Logger log = LoggerFactory.getLogger(LoanTakerController.class);
        
    @PostMapping("/taker/requestLoan")
    public String requestLoan(
            @RequestParam(value="privateKey", defaultValue="0") String privateKey,
            @RequestParam(value="basis", defaultValue="0") String basis,
            @RequestParam(value="interest", defaultValue="0") String interest,
            @RequestParam(value="duration", defaultValue="0") String duration,
            @RequestParam(value="collateral", defaultValue="0") String collateral,
            @RequestParam(value="ledgerAddress", defaultValue="0") String ledgerAddress)
    {
        try{
            log.info("Creating ask preloan with parameters: basis={} interest={} duration={}, collateral={}"
                    , basis, interest, duration, collateral);
            return (new LoanTaker(privateKey, env))
                    .createPreloanAsk(basis, interest, duration, collateral, ledgerAddress);
        }catch(Exception e){
            return e.toString();
        }
    }
}
