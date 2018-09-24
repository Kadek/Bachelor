/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adas
 */
public class PreloanController implements Initializable{
    
    private static final Logger log = LoggerFactory.getLogger(navbarController.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button askButton;
    @FXML
    private Button bidButton;
    @FXML
    private Button ledgerButton;
    
    @FXML
    private Button createButton;
    @FXML
    private Pane formPlaceholder;
    
    private Task<String> createPreloan;
    private String currentButton;
    private HashMap<String, String> urls;
    private FormController currentFormController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentButton = "ask";
        urls = new HashMap<String, String>(){{
           put("Ask", Utils.getDomain()+"/taker/requestLoan");     
           put("Bid", Utils.getDomain()+"/giver/offerLoan");
           put("Ledger", Utils.getDomain()+"/ledger/createLedger");
        }};
        loadCurrentForm();
    }
    
    @FXML
    private void askButtonClicked(ActionEvent event) {
        log.info("Ask button clicked.");
        currentButton = "Ask";
        loadCurrentForm();
    }
    @FXML
    private void bidButtonClicked(ActionEvent event) {
        log.info("Bid button clicked.");
        currentButton = "Bid";
        loadCurrentForm();
    }
    @FXML
    private void ledgerButtonClicked(ActionEvent event) {
        log.info("Ledger button clicked.");
        currentButton = "Ledger";
        loadCurrentForm();
    }
    
    private void loadCurrentForm(){        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+currentButton+"Form.fxml"));;
            Parent form = (Parent)loader.load();
            formPlaceholder.getChildren().add(form);
            
            currentFormController = loader.<FormController>getController();            
        } catch (IOException ex) {
            log.info("Can't load fxml of form for {}", currentButton);
        }
    }
    
    @FXML
    private void createButtonClicked(ActionEvent event) {
        log.info("Creating {}.", currentButton);
               
        createPreloan = new Task() {
            final String JSON_URL = urls.get(currentButton);

            @Override
            protected String call() throws Exception {
                String preloanAddress = null;
                try {
                    preloanAddress = sendPOST(JSON_URL, getParameters());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return preloanAddress;
            }
        };
        
        executorService.submit(createPreloan);
        createPreloan.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully created {} with address {}", currentButton, createPreloan.getValue());
            showInfo(createPreloan.getValue());
        });
    }
    
    private void showInfo(final String info){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setBody(new Text(info));
        StackPane stackPane = new StackPane();
        stackPane.autosize();
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER, true);
        JFXButton button = new JFXButton("Okay");	
        button.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			dialog.close();
		}
	});
	button.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
        content.setActions(button);
        
        pane.getChildren().add(stackPane);        
        dialog.show();
    }
    
    private String getParameters(){
        log.info("Fetching data from controller {}", currentButton);
        String data = (new Gson()).toJson(currentFormController.getData());
        log.info("Fetched data is {}", data);
        return data;
    }
    
    private String sendPOST(final String JSON_URL, final String parameters) throws UnirestException{
        log.info("Sending request to {}", JSON_URL);
        HttpResponse<String> stringResponse = Unirest.post(JSON_URL)
            .header("Content-Type", "application/json")
            .header("accept", "application/json").body(parameters).asString();
        return stringResponse.getBody();   
    }
}
