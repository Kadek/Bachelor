package engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class LoanInfoController {
 
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @GetMapping("/loanInfo/getLoanBasis")
    public String getLoanBasis(
            @RequestParam(value="contractAddress", defaultValue="0") String contractAddress) 
    {
        try{
            log.info("Checking basis of a loan with address {}", contractAddress);
            return (new LoanInfo(contractAddress)).getLoanBasis();
        }catch(Exception e){
            return e.toString();
        }
    }
}
