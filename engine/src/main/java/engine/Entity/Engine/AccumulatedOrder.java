/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Entity.Engine;

/**
 *
 * @author adas
 */
public class AccumulatedOrder{
    private String interest;

    public void setCumulatedBasis(String cumulatedBasis) {
        this.cumulatedBasis = cumulatedBasis;
    }
    private String cumulatedBasis;

    public AccumulatedOrder(final String interest, final String cumulatedBasis){
        this.interest = interest;
        this.cumulatedBasis = cumulatedBasis;
    }
    
    public String getInterest() {
        return interest;
    }

    public String getCumulatedBasis() {
        return cumulatedBasis;
    }
}