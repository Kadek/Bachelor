/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 *
 * @author adas
 */
public class AccountsController {
    
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    @FXML
    private Label loanBalance;
    @FXML
    private TextField loanAddress;    
    
    private Task<String> getLoanBalance;
    
    @FXML
    private void checkLoanBalance(ActionEvent event) {
        System.out.println("You clicked me!");
        
        getLoanBalance = new Task() {
            final String JSON_URL = "http://localhost:8080/loanInfo/getAccountAmount";

            @Override
            protected String call() throws Exception {
                String loanBalance = null;
                String loanAddressContent = loanAddress.getText();
                System.out.println("Checking balance of a loan with address :" + loanAddressContent);
                try {
                    loanBalance = readUrl(JSON_URL, loanAddressContent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return loanBalance;
            }
        };
        
        executorService.submit(getLoanBalance);
        System.out.println("Looking");
        getLoanBalance.setOnSucceeded((WorkerStateEvent t) -> {
            loanBalance.setText(getLoanBalance.getValue());
            System.out.println("Done");
        });
    }
    
    private String readUrl(final String JSON_URL, final String loanAddressContent) throws UnirestException{
        System.out.println("Sending request to " + JSON_URL);
        HttpResponse<String> stringResponse = Unirest.get(JSON_URL)
            .header("accept", "application/json").queryString("loanAddress", loanAddressContent).asString();
        return stringResponse.getBody();
        
    }
}
