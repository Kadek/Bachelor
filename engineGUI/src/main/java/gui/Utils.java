/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.HashMap;

/**
 *
 * @author adas
 */
public class Utils {
    public static String getDomain(){
        return "http://localhost:8080";
    }
    
    public static HashMap<String, String> collateralMapping = new HashMap<String, String>(){{
       put("None","1");
       put("Deposit","2");
       put("Legal","3");
    }};
}
