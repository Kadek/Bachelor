/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exchange;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import gui.Utils;
import gui.navbarController;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adas
 */
public class OrdersController implements Initializable{
    
    private static final Logger log = LoggerFactory.getLogger(navbarController.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @FXML
    private Pane pane;
    
    @FXML
    private Button updateButton;
    @FXML
    private JFXTextField ledgerAddressInput;
    
    @FXML
    private JFXTreeTableView<JFXOrder> asksTable;
    @FXML
    private JFXTreeTableView<JFXOrder> bidsTable;
    
    private Task<String> getOrders;
    private String collateral;
    private String duration;
    private ObservableList<JFXOrder> JFXaskOrders;
    private ObservableList<JFXOrder> JFXbidOrders;
    
    private ExchangeController parentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        JFXTreeTableColumn<JFXOrder, String> askInterestColumn = createInterestColumn();
        JFXTreeTableColumn<JFXOrder, String> askCumulatedBasisColumn = createCumulatedBasisColumn();
        JFXaskOrders = setTreeView(askInterestColumn, askCumulatedBasisColumn, asksTable);
        
        JFXTreeTableColumn<JFXOrder, String> bidInterestColumn = createInterestColumn();
        JFXTreeTableColumn<JFXOrder, String> bidCumulatedBasisColumn = createCumulatedBasisColumn();
        JFXbidOrders = setTreeView(bidInterestColumn, bidCumulatedBasisColumn, bidsTable);
        
    }
    
    private JFXTreeTableColumn<JFXOrder, String> createInterestColumn(){
        JFXTreeTableColumn<JFXOrder, String> interestColumn = new JFXTreeTableColumn<>("Interest");
        interestColumn.setPrefWidth(128);
        interestColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<JFXOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (TreeTableColumn.CellDataFeatures<JFXOrder, String> param){
                return param.getValue().getValue().getInterest();
            }
        });
        return interestColumn;
    }
    
    private JFXTreeTableColumn<JFXOrder, String> createCumulatedBasisColumn(){
        JFXTreeTableColumn<JFXOrder, String> cumulatedBasisColumn = new JFXTreeTableColumn<>("Cumulated Basis");
        cumulatedBasisColumn.setPrefWidth(128);
        cumulatedBasisColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<JFXOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (TreeTableColumn.CellDataFeatures<JFXOrder, String> param){
                return param.getValue().getValue().getCumulatedBasis();
            }
        });
        return cumulatedBasisColumn;
    }
    
    private ObservableList<JFXOrder> setTreeView(
            JFXTreeTableColumn<JFXOrder, String> firstColumn,
            JFXTreeTableColumn<JFXOrder, String> secondColumn,
            JFXTreeTableView<JFXOrder> table
        ){
        
        ObservableList<JFXOrder> list = FXCollections.observableArrayList();
        
        final TreeItem<JFXOrder> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(firstColumn, secondColumn);
        table.setRoot(root);
        table.setShowRoot(false);
        
        return list;
    }
    
    private void updateParameters(){
        duration = parentController.getDuration();
        collateral = Utils.collateralMapping.get(parentController.getCollateral());
    }
    
    @FXML
    private void updateButtonClicked(ActionEvent event) {
        log.info("Getting sorted orders");
        updateParameters();
        log.info("Duration: {}, Collateral: {}", duration, collateral);
                   
        getOrders = new Task() {
            final String JSON_URL = Utils.getDomain()+"/engine/getAccumulatedOrders";

            @Override
            protected String call() throws Exception {
                String sortedOrders = null;
                String ledgerAddress = ledgerAddressInput.getText();
                try {
                    sortedOrders = Utils.sendGET(log, JSON_URL, "ledgerAddress", ledgerAddress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return sortedOrders;
            }
        };
        
        executorService.submit(getOrders);
        getOrders.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully received orders from ledger address {}", ledgerAddressInput.getText());
            fillTables(getOrders.getValue());
            Utils.showInfo(pane, "Success");
        });
    }
    
    private void fillTables(String ordersString){
        AccumulatedOrders orders = (new Gson()).fromJson(ordersString, AccumulatedOrders.class);
        
        fillTable(orders.getAsks(collateral, duration), JFXaskOrders);
        fillTable(orders.getBids(collateral, duration), JFXbidOrders);
    }
    
    
    private void fillTable(ArrayList<AccumulatedOrder> orders, ObservableList<JFXOrder> table){
        table.clear();
        for(int i = orders.size()-1; i >= 0; i--){
            table.add(new JFXOrder(orders.get(i).getInterest(), orders.get(i).getCumulatedBasis()));            
        }
    }
    
    private class AccumulatedOrders{
        private HashMap<String, ArrayList<AccumulatedOrder>> asks;
        private HashMap<String, ArrayList<AccumulatedOrder>> bids;

        public ArrayList<AccumulatedOrder> getAsks(final String collateral, final String duration){
            return asks.get(getIndex(collateral, duration));
        }

        public ArrayList<AccumulatedOrder> getBids(final String collateral, final String duration){
            return bids.get(getIndex(collateral, duration));            
        }
        
        private String getIndex(final String collateral, final String duration){
            return collateral+"-"+duration;
        }
    }
    
    private class AccumulatedOrder{
        private String interest;
        private String cumulatedBasis;

        public String getInterest() {
            return interest;
        }

        public String getCumulatedBasis() {
            return cumulatedBasis;
        }
    }
    
    public class JFXOrder extends RecursiveTreeObject<JFXOrder>{
        private StringProperty interest;

        public StringProperty getInterest() {
            return interest;
        }

        public StringProperty getCumulatedBasis() {
            return cumulatedBasis;
        }
        private StringProperty cumulatedBasis;

        public JFXOrder(final String interest, final String cumulatedBasis){
            this.interest = new SimpleStringProperty(interest);
            this.cumulatedBasis = new SimpleStringProperty(cumulatedBasis);
        }
    }
    
    public void setParentController(ExchangeController parentController){
        this.parentController = parentController;
        pane = parentController.getPane();
    }
}
