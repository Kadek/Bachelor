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
public class MatchData {

    public MatchData(String bidAddress, String bidBasis, String askAddress, String askBasis) {
        this.bidAddress = bidAddress;
        this.bidBasis = bidBasis;
        this.askAddress = askAddress;
        this.askBasis = askBasis;
    }
    private String bidAddress;
    private String bidBasis;
    private String askAddress;
    private String askBasis;    
}
