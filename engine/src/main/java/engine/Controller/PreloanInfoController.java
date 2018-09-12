package engine.Controller;

import engine.Entity.PreloanInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class PreloanInfoController {
 
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @GetMapping("/preloanInfo/getLoanBasis")
    public String getLoanBasis(
            @RequestParam(value="contractAddress", defaultValue="0") String contractAddress) 
    {
        try{
            log.info("Checking basis of a loan with address {}", contractAddress);
            return (new PreloanInfo(contractAddress)).getPreloanBasis();
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @GetMapping("/preloanInfo/getLedgerAddress")
    public String getLedgerAddress(
            @RequestParam(value="contractAddress", defaultValue="0") String contractAddress) 
    {
        try{
            log.info("Checking ledger address of a loan with address {}", contractAddress);
            return (new PreloanInfo(contractAddress)).getLedgerAddress();
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @GetMapping("/preloanInfo/getSide")
    public String getSide(
            @RequestParam(value="contractAddress", defaultValue="0") String contractAddress) 
    {
        try{
            log.info("Checking side of a loan with address {}", contractAddress);
            return (new PreloanInfo(contractAddress)).getSide();
        }catch(Exception e){
            return e.toString();
        }
    }
}
