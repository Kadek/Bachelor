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
public class DepositFormController {
        
    private static final Logger log = LoggerFactory.getLogger(DepositFormController.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    CollateralController parentController;
    private Task<String> createDeposit;
    
    @FXML
    private Pane pane;
    
    @FXML
    private JFXButton createButton;
    
    @FXML
    private JFXTextField loanTakerInput;
    @FXML
    private JFXTextField privateKeyInput;
    @FXML
    private JFXTextField depositAmountInput;
    
    @FXML
    private void createButtonClicked(ActionEvent event) {
        log.info("Create button clicked.");
        createDeposit = new Task() {
            final String JSON_URL1 = Utils.getDomain()+"/collateral/createDeposit";

            @Override
            protected String call() throws Exception {
                String depositAddress = null;
                
                String privateKey = privateKeyInput.getText();
                String loanTakerAddress = loanTakerInput.getText();
                String depositAmount = depositAmountInput.getText();
                
                try {
                    DepositForm depositForm = new DepositForm(privateKey, loanTakerAddress, depositAmount);
                    String depositFormJson = (new Gson()).toJson(depositForm);
                    depositAddress = Utils.sendPOST(log, JSON_URL1, depositFormJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return depositAddress;
            }
        };
        
        executorService.submit(createDeposit);
        createDeposit.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully created deposit contract with address {}", createDeposit.getValue());
            Utils.showInfo(pane, createDeposit.getValue());
        });
    }   
    
    public void setParentController(CollateralController parentController){
        this.parentController = parentController;
        pane = parentController.getPane();
    }

    private class DepositForm {
        private String taker;
        private String depositValue;

        public DepositForm(String privateKey, String taker, String depositValue) {
            this.taker = taker;
            this.depositValue = depositValue;
            this.privateKey = privateKey;
            this.legalInformation = "";
            this.legalAESKey = "";
        }
        private String privateKey;
        private String legalInformation;
        private String legalAESKey;
    }
}
