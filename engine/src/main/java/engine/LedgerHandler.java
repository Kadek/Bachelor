package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class LedgerHandler {
    
    @Autowired
    private Environment env;
    
    @Autowired 
    private void setAddress(){
        address = env.getProperty("ledger.address");
    }
    
    private String address;
            
    public void handleLedger() {
        System.out.println(this.address);
    }
    
}
