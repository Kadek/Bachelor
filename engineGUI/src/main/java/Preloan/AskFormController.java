/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preloan;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gui.Utils;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adas
 */
public class AskFormController implements Initializable, FormController {
        
    private static final Logger log = LoggerFactory.getLogger(AskFormController.class);
    
    @FXML
    private JFXTextField loanBasis;
    @FXML
    private JFXTextField loanDuration;
    @FXML
    private JFXTextField privateKey;
    @FXML
    private JFXTextField loanInterest;
    @FXML
    private JFXTextField ledgerAddress;
    @FXML
    private JFXComboBox collateralInput;
    @FXML
    private JFXTextField collateralAddress;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collateralInput.getItems().add(new Label("None"));
        collateralInput.getItems().add(new Label("Deposit"));
        collateralInput.getItems().add(new Label("Legal"));
    }

    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("basis", loanBasis.getText());
        data.put("duration", loanDuration.getText());
        data.put("privateKey", privateKey.getText());
        data.put("interest", loanInterest.getText());
        data.put("ledgerAddress", ledgerAddress.getText());
        
        String selectedCollateralText = ((Label)collateralInput.getSelectionModel().getSelectedItem()).getText();
        data.put("collateral", Utils.collateralMapping.get(selectedCollateralText));
        data.put("collateralAddress", collateralAddress.getText());
        return data;
    }    
}
