/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adas
 */
public class navbarController {
        
    private static final Logger log = LoggerFactory.getLogger(navbarController.class);
           
    @FXML
    private Button preloanButton;
    
    @FXML
    private void preloanButtonClicked(ActionEvent event) throws IOException {
        log.info("The scene Preloan is being started.");
        
        Stage stage = (Stage) preloanButton.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Preloan.fxml"));
        Scene scene =  new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
       
    
}
