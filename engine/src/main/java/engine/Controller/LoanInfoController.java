package engine.Controller;

import engine.Entity.LoanInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class LoanInfoController {
 
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @GetMapping("/loanInfo/getBasis")
    public String getBasis(
            @RequestParam(value="loanAddress", defaultValue="0") String loanAddress) 
    {
        try{
            log.info("Checking basis of a loan with address {}", loanAddress);
            return (new LoanInfo(loanAddress)).getBasis();
        }catch(Exception e){
            return e.toString();
        }
    }
    
    @GetMapping("/loanInfo/getAccountAmount")
    public String getLoanAccount(
            @RequestParam(value="loanAddress", defaultValue="0") String loanAddress) 
    {
        try{
            log.info("Checking account amount of a loan with address {}", loanAddress);
            return (new LoanInfo(loanAddress)).getAccountAmount();
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/loanInfo/getInterestScaled")
    public String getInterestScaled(
            @RequestParam(value="loanAddress", defaultValue="0") String loanAddress) 
    {
        try{
            log.info("Checking interest of a loan with address {}", loanAddress);
            return (new LoanInfo(loanAddress)).getInterestScaled();
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/loanInfo/getDuration")
    public String getDuration(
            @RequestParam(value="loanAddress", defaultValue="0") String loanAddress) 
    {
        try{
            log.info("Checking duration of a loan with address {}", loanAddress);
            return (new LoanInfo(loanAddress)).getDuration();
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/loanInfo/getRepayment")
    public String getRepayment(
            @RequestParam(value="loanAddress", defaultValue="0") String loanAddress) 
    {
        try{
            log.info("Checking repayment amount of a loan with address {}", loanAddress);
            return (new LoanInfo(loanAddress)).getRepayment();
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/loanInfo/getLatestPaymentDate")
    public String getLatestPaymentDate(
            @RequestParam(value="loanAddress", defaultValue="0") String loanAddress) 
    {
        try{
            log.info("Checking lastest payment date of a loan with address {}", loanAddress);
            return (new LoanInfo(loanAddress)).getLatestPaymentDate();
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/loanInfo/getPaymentCount")
    public String getPaymentCount(
            @RequestParam(value="loanAddress", defaultValue="0") String loanAddress) 
    {
        try{
            log.info("Checking payment count of a loan with address {}", loanAddress);
            return (new LoanInfo(loanAddress)).getPaymentCount();
        }catch(Exception e){
            return e.toString();
        }
    }
}
