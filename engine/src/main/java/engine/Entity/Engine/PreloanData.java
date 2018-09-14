/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Entity.Engine;

import java.math.BigInteger;

/**
 *
 * @author adas
 */
public class PreloanData implements Comparable{
    public BigInteger basis;
    public BigInteger interest;
    public String address;
    public BigInteger timeCreated;
    public BigInteger side;

    public PreloanData(
        final BigInteger basis,
        final BigInteger interestScaled,
        final String address,
        final BigInteger timeCreated,
        final BigInteger side
    ){
        this.basis = basis;
        this.interest = interestScaled;
        this.address = address;
        this.timeCreated = timeCreated;
        this.side = side;
    }        

    @Override
    public int compareTo(Object o) {            
        PreloanData compared = (PreloanData) o;
        if(this.side == BigInteger.ONE){
            if(compared.interest.equals(this.interest))
                return compared.timeCreated.compareTo(this.timeCreated);
            else
                return compared.interest.compareTo(this.interest);
        }else{
            if(this.interest.equals(compared.interest))
                return this.timeCreated.compareTo(compared.timeCreated);
            else
                return this.interest.compareTo(compared.interest);
        }
    }
}