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
 public class AccumulatedOrders{
        private HashMap<String, ArrayList<AccumulatedOrder>> asks;
        private HashMap<String, ArrayList<AccumulatedOrder>> bids;
        
        public ArrayList<AccumulatedOrder> getAsks(final String collateral, final String duration){
            return asks.get(getIndex(collateral, duration));
        }

        public ArrayList<AccumulatedOrder> getBids(final String collateral, final String duration){
            return bids.get(getIndex(collateral, duration));            
        }
        
        public HashMap<String, ArrayList<AccumulatedOrder>> getAsks (){
            return asks;
        }
        public HashMap<String, ArrayList<AccumulatedOrder>> getBids (){
            return bids;
        }
        
        public void add(
                final HashMap<String, ArrayList<AccumulatedOrder>> map,
                final String index, 
                final ArrayList<AccumulatedOrder> accumulatedOrders){
            map.put(index, accumulatedOrders);
        }
        
        private String getIndex(final String collateral, final String duration){
            return collateral+"-"+duration;
        }
        
        public void allocateMaps(){
            asks = new HashMap<>();
            bids = new HashMap<>();
        }
    }
    