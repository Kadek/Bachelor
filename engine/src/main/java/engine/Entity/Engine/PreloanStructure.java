/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Entity.Engine;

import engine.Preloan;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author adas
 */

public class PreloanStructure {

    private String classifier;
    private HashMap<String, PreloanStructure> children;
    private PriorityQueue<PreloanData> preloans;

    public PreloanStructure(){
        
    }
    
    public PreloanStructure(boolean placeholder) {
        this.preloans = new PriorityQueue<>();
    }

    public PreloanStructure(String classifier) {
        this.classifier = classifier;
        children = new HashMap<>();
    }

    public PreloanStructure getChild(String argument, String nextClassifier) {
        if(children.containsKey(argument))
            return children.get(argument);
        else{
            PreloanStructure preloanStructure;
            if(nextClassifier.equals(""))
                preloanStructure = new PreloanStructure(true);
            else{
                preloanStructure = new PreloanStructure(nextClassifier);
            }
            children.put(argument, preloanStructure);
            return preloanStructure;
        }
    }

    public void addPreloan(Preloan preloan) throws Exception{
        PreloanData preloanData = new PreloanData(
            preloan.basis().send(),
            preloan.interestScaled().send(),
            preloan.getAddress().send(),
            preloan.timeCreated().send(),
            preloan.side().send()
        );

        preloans.add(preloanData);
    }

    public HashMap<String, PreloanStructure> getChildren() {
        return children;
    }

    public PriorityQueue<PreloanData> getPreloans() {
        return preloans;
    }


}    
