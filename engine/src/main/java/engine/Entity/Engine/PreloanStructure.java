/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Entity.Engine;

import engine.Preloan;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author adas
 */

public class PreloanStructure {

    private String classifier;
    HashMap<String, PreloanStructure> children;
    PriorityQueue<PreloanData> preloans;

    public PreloanStructure() {
        this.preloans = new PriorityQueue<>(
            new Comparator<PreloanData>(){
                @Override
                public int compare(PreloanData pd1, PreloanData pd2){
                    if(pd1.interest.equals(pd2.interest))
                        return pd1.timeCreated.compareTo(pd2.timeCreated);
                    else
                        return pd1.interest.compareTo(pd2.interest);
                }
            }
        );
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
            if(classifier.equals(""))
                preloanStructure = new PreloanStructure();
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
            preloan.scale().send(),
            preloan.getAddress().send(),
            preloan.timeCreated().send()
        );

        preloans.add(preloanData);
    }

    private class PreloanData{
        public BigInteger basis;
        public BigInteger interest;
        public String address;
        public BigInteger timeCreated;

        public PreloanData(
            final BigInteger basis,
            final BigInteger interestScaled,
            final BigInteger scale,
            final String address,
            final BigInteger timeCreated
        ){
            this.basis = basis;
            this.interest = interestScaled.divide(scale);
            this.address = address;
            this.timeCreated = timeCreated;
        }        
    }
}    
