package engine.Controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import engine.Entity.LoanTaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoanTakerController {
 
    @Autowired
    Environment env;
    
    private static final Logger log = LoggerFactory.getLogger(LoanTakerController.class);
        
    @PostMapping("/taker/requestLoan")
    public String requestLoan(
            @RequestBody String loanFormJson)
    {
        try{
            LoanForm loanForm = (new Gson()).fromJson(loanFormJson, LoanForm.class);
            log.info("Creating ask preloan with parameters: basis={} interest={} duration={}, collateral={}"
                , loanForm.getBasis(), loanForm.getInterest(), loanForm.getDuration(), loanForm.getCollateral());
            return (new LoanTaker(loanForm.getPrivateKey(), env))
                .createPreloanAsk(
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
        
    @PostMapping("/taker/cancelRequest")
    public String cancelRequest(
            @RequestBody String cancelFormJson)
    {
        try{
            CancelForm cancelForm = (new Gson()).fromJson(cancelFormJson, CancelForm.class);
            log.info("Cancelling ask preloan with address={}", cancelForm.getOfferAddress());
            return (new LoanTaker(cancelForm.getPrivateKey(), env))
                    .deletePreloanAsk(cancelForm.getOfferAddress(), cancelForm.getLedgerAddress());
        }catch(Exception e){
            return e.toString();
        }
    }
    
        
    @PostMapping("/taker/fillRepaymentAccount")
    public String consumeRepayment(
            @RequestBody String fillRepaymentAccountJson)
    {
        try{
            FillRepaymentAccount fillRepaymentAccount = (new Gson()).fromJson(fillRepaymentAccountJson, FillRepaymentAccount.class);
            log.info("Filling repayment account of a loan with address {} with amount {}", 
                    fillRepaymentAccount.getLoanAddress(), fillRepaymentAccount.getAmount());
            return (new LoanTaker(fillRepaymentAccount.getPrivateKey(), env))
                    .fillRepaymentAccount(
                            fillRepaymentAccount.getLoanAddress(),
                            fillRepaymentAccount.getAmount()
                    );
        }catch(Exception e){
            return e.toString();
        }
    }
    
    private class FillRepaymentAccount{
         
        private String privateKey;
        private String loanAddress;
        private String amount;

        public String getAmount() {
            return amount;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getLoanAddress() {
            return loanAddress;
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
