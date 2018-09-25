/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exchange;

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
public class ExchangeController implements Initializable{
    
    private static final Logger log = LoggerFactory.getLogger(navbarController.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Button ordersButton;
    @FXML
    private Button matchesButton;
    
    @FXML
    private JFXComboBox collateralInput;
    @FXML
    private JFXTextField durationInput;
    
    @FXML
    private Pane formPlaceholder;
    
    private String currentButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentButton = "Orders";
        
        collateralInput.getItems().add(new Label("None"));
        collateralInput.getItems().add(new Label("Deposit"));
        collateralInput.getItems().add(new Label("Legal"));
        
        FXMLLoader loader = loadCurrentForm();
        loader.<OrdersController>getController().setParentController(this); 
    }
    
    @FXML
    private void ordersButtonClicked(ActionEvent event) {
        log.info("Orders button clicked.");
        currentButton = "Orders";
        FXMLLoader loader = loadCurrentForm();
        loader.<OrdersController>getController().setParentController(this);            
    }
    @FXML
    private void matchesButtonClicked(ActionEvent event) {
        log.info("Matches button clicked.");
        currentButton = "Matches";
        FXMLLoader loader = loadCurrentForm();
        loader.<MatchesController>getController().setParentController(this);            
    }
    
    private FXMLLoader loadCurrentForm(){        
        try {
            log.info(" Getting /fxml/{}View.fxml.", currentButton);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+currentButton+"View.fxml"));
            Parent form = (Parent)loader.load();
            formPlaceholder.getChildren().add(form);
            
            return loader;
        } catch (IOException ex) {
            ex.printStackTrace();
            log.info("Can't load fxml of view for {}", currentButton);
        }
        return null;
    }
    
    public String getDuration(){
        return durationInput.getText();
    }
    
    public String getCollateral(){
        return ((Label)collateralInput.getSelectionModel().getSelectedItem()).getText();
    }
    
    public Pane getPane(){
        return pane;
    }
}
