package engine.Controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import engine.Entity.LoanGiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@RestController
public class LoanGiverController {
 
    @Autowired
    Environment env;
    
    private static final Logger log = LoggerFactory.getLogger(LoanGiverController.class);
        
    @PostMapping("/giver/offerLoan")
    public String offerLoan(@RequestBody String loanFormJson)
    {
        try{
            LoanForm loanForm = (new Gson()).fromJson(loanFormJson, LoanForm.class);
            log.info("Creating bid preloan with parameters: basis={} interest={} duration={}, collateral={}"
                , loanForm.getBasis(), loanForm.getInterest(), loanForm.getDuration(), loanForm.getCollateral());
            return (new LoanGiver(loanForm.getPrivateKey(), env))
                .createPreloanBid(
                    loanForm.getBasis(), 
                    loanForm.getInterest(), 
                    loanForm.getDuration(), 
                    loanForm.getCollateral(), 
                    loanForm.getLedgerAddress()
            );
        }catch(Exception e){
            return e.toString();
        }
    }
        
    @PostMapping("/taker/cancelOffer")
    public String cancelOffer(
            @RequestBody String cancelFormJson)
    {
        try{
            CancelForm cancelForm = (new Gson()).fromJson(cancelFormJson, CancelForm.class);
            log.info("Cancelling bid preloan with address={}", cancelForm.getOfferAddress());
            return (new LoanGiver(cancelForm.getPrivateKey(), env))
                    .deletePreloanBid(cancelForm.getOfferAddress(), cancelForm.getLedgerAddress());
        }catch(Exception e){
            return e.toString();
        }
    }
    
    private class CancelForm{

        public CancelForm(String privateKey, String offerAddress) {
            this.privateKey = privateKey;
            this.offerAddress = offerAddress;
        }
        private String privateKey;
        private String offerAddress;
        private String ledgerAddress;

        public String getLedgerAddress() {
            return ledgerAddress;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getOfferAddress() {
            return offerAddress;
        }
    }
    
    private class LoanForm{

        public LoanForm(String body) {
            System.out.println(body);
        }
        
        public LoanForm(String privateKey, String basis, String interest, String duration, String collateral, String ledgerAddress) {
            this.privateKey = privateKey;
            this.basis = basis;
            this.interest = interest;
            this.duration = duration;
            this.collateral = collateral;
            this.ledgerAddress = ledgerAddress;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getBasis() {
            return basis;
        }

        public String getInterest() {
            return interest;
        }

        public String getDuration() {
            return duration;
        }

        public String getCollateral() {
            return collateral;
        }

        public String getLedgerAddress() {
            return ledgerAddress;
        }
        private String privateKey;
        private String basis;
        private String interest;
        private String duration;
        private String collateral;
        private String ledgerAddress;
    }
}
