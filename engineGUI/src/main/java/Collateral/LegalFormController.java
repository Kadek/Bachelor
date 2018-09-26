/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collateral;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import gui.Utils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adas
 */
public class LegalFormController{
        
    private static final Logger log = LoggerFactory.getLogger(LegalFormController.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    private CollateralController parentController;
    private Task<String> createLegal;
    
    @FXML
    private Pane pane;
    
    @FXML
    private JFXButton createButton;
    
    @FXML
    private JFXTextField privateKeyInput;
    @FXML
    private JFXTextField informationInput;
    @FXML
    private JFXTextField AESKeyInput;
    
    @FXML
    private void createButtonClicked(ActionEvent event) {
        log.info("Create button clicked.");
        createLegal = new Task() {
            final String JSON_URL1 = Utils.getDomain()+"/collateral/createLegal";

            @Override
            protected String call() throws Exception {
                String legalAddress = null;
                
                String privateKey = privateKeyInput.getText();
                String legalInformation = informationInput.getText();
                String AESKey = AESKeyInput.getText();
                
                try {
                    LegalForm legalForm = new LegalForm(privateKey, legalInformation, AESKey);
                    String legalFormJson = (new Gson()).toJson(legalForm);
                    legalAddress = Utils.sendPOST(log, JSON_URL1, legalFormJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return legalAddress;
            }
        };
        
        executorService.submit(createLegal);
        createLegal.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully created legal contract with address {}", createLegal.getValue());
            Utils.showInfo(pane, createLegal.getValue());
        });
    }   
    
    public void setParentController(CollateralController parentController){
        this.parentController = parentController;
        pane = parentController.getPane();
    }

    private class LegalForm {
        private String taker;
        private String depositValue;

        public LegalForm(String privateKey, String legalInformation, String AESKey) {
            this.taker = "";
            this.depositValue = "";
            this.privateKey = privateKey;
            this.legalInformation = legalInformation;
            this.legalAESKey = AESKey;
        }
        private String privateKey;
        private String legalInformation;
        private String legalAESKey;
    }
    
}
