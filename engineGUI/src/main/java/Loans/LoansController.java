/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loans;

import Exchange.*;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import gui.Utils;
import gui.navbarController;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
public class LoansController{
    
    private static final Logger log = LoggerFactory.getLogger(navbarController.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button loadButton;
    @FXML
    private Button consumeButton;
    @FXML
    private Button refillButton;
    
    @FXML
    private JFXTextField loanAddressInput;
    @FXML
    private JFXTextField privateKeyInput;
    @FXML
    private JFXTextField refillAmountInput;
    
    @FXML
    private Label basisOutput;
    @FXML
    private Label interestOutput;
    @FXML
    private Label scaleOutput;
    @FXML
    private Label durationOutput;
    @FXML
    private Label collateralOutput;
    @FXML
    private Label repaymentOutput;
    @FXML
    private Label paymentCountOutput;
    @FXML
    private Label loanStateOutput;
    @FXML
    private Label accountAmountOutput;
    
    private Task<Loan> loadLoan;
    private Task<String> consumeLoan;
    private Task<String> refillLoan;
        
    @FXML
    private void loadButtonClicked(ActionEvent event) {
        log.info("Load button clicked.");
        
        loadLoan = new Task() {
            final String JSON_URL1 = Utils.getDomain()+"/loanInfo/getBasis";
            final String JSON_URL2 = Utils.getDomain()+"/loanInfo/getInterestScaled";
            final String JSON_URL3 = Utils.getDomain()+"/loanInfo/getScale";
            final String JSON_URL4 = Utils.getDomain()+"/loanInfo/getDuration";
            final String JSON_URL5 = Utils.getDomain()+"/loanInfo/getCollateral";
            final String JSON_URL6 = Utils.getDomain()+"/loanInfo/getRepayment";
            final String JSON_URL7 = Utils.getDomain()+"/loanInfo/getPaymentCount";
            final String JSON_URL8 = Utils.getDomain()+"/loanInfo/getLoanState";
            final String JSON_URL9 = Utils.getDomain()+"/loanInfo/getAccountAmount";

            @Override
            protected Loan call() throws Exception {
                String basis = null;
                String interestScaled = null;
                String scale = null;
                String duration = null;
                String collateral = null;
                String repayment = null;
                String paymentCount = null;
                String loanState = null;
                String accountAmount = null;
                String loanAddress = loanAddressInput.getText();
                try {
                    basis = Utils.sendGET(log, JSON_URL1, "loanAddress", loanAddress);
                    interestScaled = Utils.sendGET(log, JSON_URL2, "loanAddress", loanAddress);
                    scale = Utils.sendGET(log, JSON_URL3, "loanAddress", loanAddress);
                    duration = Utils.sendGET(log, JSON_URL4, "loanAddress", loanAddress);
                    collateral = Utils.sendGET(log, JSON_URL5, "loanAddress", loanAddress);
                    repayment = Utils.sendGET(log, JSON_URL6, "loanAddress", loanAddress);
                    paymentCount = Utils.sendGET(log, JSON_URL7, "loanAddress", loanAddress);
                    loanState = Utils.sendGET(log, JSON_URL8, "loanAddress", loanAddress);
                    accountAmount = Utils.sendGET(log, JSON_URL9, "loanAddress", loanAddress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return new Loan(
                    basis,
                    interestScaled,
                    scale,
                    duration,
                    collateral,
                    repayment,
                    paymentCount,
                    loanState,
                    accountAmount
                );
            }
        };
        
        executorService.submit(loadLoan);
        loadLoan.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully loaded loan from loan address {}", loanAddressInput.getText());
            fillTable(loadLoan.getValue());
            Utils.showInfo(pane, "Success");
        });
    }
    
    private void fillTable(Loan loan){
        basisOutput.setText(loan.getBasis());
        interestOutput.setText(loan.getInterestScaled());
        scaleOutput.setText(loan.getScale());
        durationOutput.setText(loan.getDuration());
        collateralOutput.setText(Utils.reverseCollateralMapping.get(loan.getCollateral()));
        repaymentOutput.setText(loan.getRepayment());
        paymentCountOutput.setText(loan.getPaymentCount());
        loanStateOutput.setText(Utils.reverseLoanStateMapping.get(loan.getLoanState()));
        accountAmountOutput.setText(loan.getAccountAmount());
    }
    
    @FXML
    private void consumeButtonClicked(ActionEvent event) {
        log.info("Consume button clicked.");
        consumeLoan = new Task() {
            final String JSON_URL1 = Utils.getDomain()+"/giver/consumeRepayment";

            @Override
            protected String call() throws Exception {
                String response = null;
                String loanAddress = loanAddressInput.getText();
                String privateKey = privateKeyInput.getText();
                try {
                    HashMap<String, String> consumeForm = new HashMap<>();
                    consumeForm.put("loanAddress", loanAddress);
                    consumeForm.put("privateKey", privateKey);
                    String consumeFormJson = (new Gson()).toJson(consumeForm);
                    response = Utils.sendPOST(log, JSON_URL1, consumeFormJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return response;
            }
        };
        
        executorService.submit(consumeLoan);
        consumeLoan.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully sent consume request for loan address {}", loanAddressInput.getText());
            Utils.showInfo(pane, consumeLoan.getValue());
        });
    }
    @FXML
    private void refillButtonClicked(ActionEvent event) {
        log.info("Refill button clicked.");
        refillLoan = new Task() {
            final String JSON_URL1 = Utils.getDomain()+"/taker/fillRepaymentAccount";

            @Override
            protected String call() throws Exception {
                String response = null;
                String loanAddress = loanAddressInput.getText();
                String privateKey = privateKeyInput.getText();
                String refillAmount = refillAmountInput.getText();
                try {
                    HashMap<String, String> refillForm = new HashMap<>();
                    refillForm.put("loanAddress", loanAddress);
                    refillForm.put("privateKey", privateKey);
                    refillForm.put("amount", refillAmount);
                    String refillFormJson = (new Gson()).toJson(refillForm);
                    response = Utils.sendPOST(log, JSON_URL1, refillFormJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return response;
            }
        };
        
        executorService.submit(refillLoan);
        refillLoan.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully sent refill request for loan address {}", loanAddressInput.getText());
            Utils.showInfo(pane, refillLoan.getValue());
        });
    }
    
    private class Loan{
        private String basis;
        private String interestScaled;
        private String scale;
        private String duration;
        private String collateral;
        private String repayment;
        private String paymentCount;
        private String loanState;
        private String accountAmount;

        public Loan(String basis, String interestScaled, String scale, String duration, String collateral, String repayment, String paymentCount, String loanState, String accountAmount) {
            this.basis = basis;
            this.interestScaled = interestScaled;
            this.scale = scale;
            this.duration = duration;
            this.collateral = collateral;
            this.repayment = repayment;
            this.paymentCount = paymentCount;
            this.loanState = loanState;
            this.accountAmount = accountAmount;
        }

        public String getBasis() {
            return basis;
        }

        public String getInterestScaled() {
            return interestScaled;
        }

        public String getScale() {
            return scale;
        }

        public String getDuration() {
            return duration;
        }

        public String getCollateral() {
            return collateral;
        }

        public String getRepayment() {
            return repayment;
        }

        public String getPaymentCount() {
            return paymentCount;
        }

        public String getLoanState() {
            return loanState;
        }

        public String getAccountAmount() {
            return accountAmount;
        }
        
        
    }
}

