package engine.Controller;

import engine.Entity.LedgerHandler;
import engine.Entity.LoanGiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LedgerHandlerController{
    
    private static final Logger log = LoggerFactory.getLogger(LedgerHandlerController.class);
        
    @PostMapping("/ledger/createLedger")
    public String offerLoan(
            @RequestParam(value="privateKey", defaultValue="0") String privateKey)
    {
        try{
            log.info("Creating a new ledger");
            return (new LedgerHandler(privateKey)).createLedger();
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @PostMapping("/ledger/setLedgerAddress")
    public String getLedgerAddress(
            @RequestParam(value="ledgerAddress", defaultValue="0x0") String ledgerAddress)
    {
        try{
            log.info("Setting ledger address");
            (new LedgerHandler()).setLedgerAddress(ledgerAddress);
            return "Success";
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/ledger/getLedgerAddress")
    public String getLedgerAddress(){
        try{
            log.info("Checking ledger address");
            return (new LedgerHandler()).getLedgerAddress();
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/ledger/getAsks")
    public String getAsks(
            @RequestParam(value="ledgerAddress", defaultValue="0x0") String ledgerAddress)
    {
        try{
            log.info("Getting ask addresses");
            return (new LedgerHandler()).getAsks(ledgerAddress);
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/ledger/getBids")
    public String getBids(
            @RequestParam(value="ledgerAddress", defaultValue="0x0") String ledgerAddress)
    {
        try{
            log.info("Getting ask addresses");
            return (new LedgerHandler()).getBids(ledgerAddress);
        }catch(Exception e){
            return e.toString();
        }
    }
    
}
