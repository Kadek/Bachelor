package engine.Controller;

import com.google.gson.Gson;
import engine.Entity.LedgerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LedgerHandlerController{
    
    private static final Logger log = LoggerFactory.getLogger(LedgerHandlerController.class);
        
    @PostMapping("/ledger/createLedger")
    public String createLedger(@RequestBody String privateKey){
        try{
            log.info("Creating a new ledger");
            return (new LedgerHandler(privateKey)).createLedger();
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @PostMapping("/ledger/setLedgerAddress")
    public String getLedgerAddress(
            @RequestBody String ledgerAddress)
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
            String[] asks = (new LedgerHandler()).getAsks(ledgerAddress);
            return (new Gson()).toJson(asks);
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/ledger/getBids")
    public String getBids(
            @RequestParam(value="ledgerAddress", defaultValue="0x0") String ledgerAddress)
    {
        try{
            log.info("Getting bid addresses");
            String[] bids = (new LedgerHandler()).getBids(ledgerAddress);
            return (new Gson()).toJson(bids);
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @GetMapping("/ledger/getLedgerCounter")
    public String getLedgerCounter(
            @RequestParam(value="ledgerAddress", defaultValue="0x0") String ledgerAddress)
    {
        try{
            log.info("Getting ledger counter");
            String ledgerCounter = (new LedgerHandler()).getLedgerCounter(ledgerAddress);
            return ledgerCounter;
        }catch(Exception e){
            return e.toString();
        }
    }
    
}
