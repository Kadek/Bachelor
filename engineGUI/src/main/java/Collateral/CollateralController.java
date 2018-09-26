/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collateral;

import Exchange.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gui.navbarController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adas
 */
public class CollateralController implements Initializable{
    
    private static final Logger log = LoggerFactory.getLogger(navbarController.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button depositButton;
    @FXML
    private Button legalButton;
    
    @FXML
    private Pane formPlaceholder;
    
    private String currentButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentButton = "Deposit";
        
        FXMLLoader loader = loadCurrentForm();
        loader.<DepositFormController>getController().setParentController(this); 
    }
    
    @FXML
    private void depositButtonClicked(ActionEvent event) {
        log.info("Deposit button clicked.");
        currentButton = "Deposit";
        FXMLLoader loader = loadCurrentForm();
        loader.<DepositFormController>getController().setParentController(this);            
    }
    @FXML
    private void legalButtonClicked(ActionEvent event) {
        log.info("Legal button clicked.");
        currentButton = "Legal";
        FXMLLoader loader = loadCurrentForm();
        loader.<LegalFormController>getController().setParentController(this);            
    }
    
    private FXMLLoader loadCurrentForm(){        
        try {
            log.info(" Getting /fxml/{}Form.fxml.", currentButton);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+currentButton+"Form.fxml"));
            Parent form = (Parent)loader.load();
            formPlaceholder.getChildren().add(form);
            
            return loader;
        } catch (IOException ex) {
            ex.printStackTrace();
            log.info("Can't load fxml of view for {}", currentButton);
        }
        return null;
    }
    
    public Pane getPane(){
        return pane;
    }
}
