/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preloan;

import com.jfoenix.controls.JFXTextField;
import java.util.HashMap;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adas
 */
public class LedgerFormController implements FormController{
        
    private static final Logger log = LoggerFactory.getLogger(LedgerFormController.class);

    @FXML
    private JFXTextField privateKey;
    
    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("privateKey", privateKey.getText());
        return data;
    }
    
}
