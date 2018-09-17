/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Entity.Engine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author adas
 */
public class TransactionStructure {

    public String getPrivateKey() {
        return privateKey;
    }

    public ArrayList<HashMap<String, String>> getTransactions() {
        return transactions;
    }

    public String getLedgerCounter() {
        return ledgerCounter;
    }

    public String getLedgerAddress() {
        return ledgerAddress;
    }
    
    private String ledgerCounter;
    private String ledgerAddress;
    private String privateKey;
    private ArrayList<HashMap<String, String>> transactions;
    
}
