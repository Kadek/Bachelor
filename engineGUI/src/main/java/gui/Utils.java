/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.slf4j.Logger;

/**
 *
 * @author adas
 */
public class Utils {


    public static String getDomain(){
        return "http://localhost:8080";
    }
    
    public static HashMap<String, String>  reverseLoanStateMapping = new HashMap<String, String>(){{
       put("0", "Undefined");
       put("1", "Set");
       put("2", "Started");
       put("3", "Blocked");
    }};
    public static HashMap<String, String>  reverseCollateralMapping = new HashMap<String, String>(){{
       put("1", "None");
       put("2", "Deposit");
       put("3", "Legal");
    }};
    public static HashMap<String, String> collateralMapping = new HashMap<String, String>(){{
       put("None","1");
       put("Deposit","2");
       put("Legal","3");
    }};
    
    public static void showInfo(final Pane pane, final String info){
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
    
    public static String sendPOST(final Logger log, final String JSON_URL, final String parameters) throws UnirestException{
        log.info("Sending request to {}", JSON_URL);
        HttpResponse<String> stringResponse = Unirest.post(JSON_URL)
            .header("Content-Type", "application/json")
            .header("accept", "application/json").body(parameters).asString();
        return stringResponse.getBody();   
    }
    
    public static String sendGET(final Logger log, final String JSON_URL, final String parameterName, final String parameter) throws UnirestException{
        log.info("Sending request to {}", JSON_URL);
        HttpResponse<String> stringResponse = Unirest.get(JSON_URL)
            .header("accept", "application/json")
            .queryString(parameterName, parameter).asString();
        return stringResponse.getBody();   
    }
}
