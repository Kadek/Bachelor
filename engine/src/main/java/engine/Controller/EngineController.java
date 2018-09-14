package engine.Controller;

import com.google.gson.Gson;
import engine.Entity.Engine.Engine;
import engine.Entity.Engine.MatchData;
import engine.Entity.Engine.PreloanStructure;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EngineController {
 
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @GetMapping("/engine/getSortedOrders")
    public String getLoanBasis(
            @RequestParam(value="ledgerAddress", defaultValue="0x0") String ledgerAddress) 
    {
        try{
            log.info("Getting sorted orders from ledger with address {}", ledgerAddress);
            PreloanStructure preloanStructure = (new Engine()).getSortedOrders(ledgerAddress);
            return (new Gson()).toJson(preloanStructure);
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @PostMapping("/engine/matchOrders")
    public String matchOrders(
            @RequestBody String orders) 
    {
        try{
            log.info("Getting matches from orders");
            ArrayList<MatchData> matchStructure = (new Engine()).matchOrders(orders);
            return (new Gson()).toJson(matchStructure);
        }catch(Exception e){
            return e.toString();
        }
    }
}
