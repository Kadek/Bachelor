/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exchange;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
public class MatchesController implements Initializable{
    
    private static final Logger log = LoggerFactory.getLogger(navbarController.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    @FXML
    private Pane pane;
    @FXML
    private JFXTreeTableView<JFXMatch> matchesTable;
    
    @FXML
    private Button createButton;
    @FXML
    private Button updateButton;
    
    @FXML
    private JFXTextField privateKeyInput;
    @FXML
    private JFXTextField ledgerAddressInput;
        
    private Task<String> getMatches;
    private Task<String> createLoans;
    private String collateral;
    private String duration;
    private ExchangeController parentController;
    private ObservableList<JFXMatch> JFXmatches;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXTreeTableColumn<JFXMatch, String> askAddressColumn = createAskAddressColumn();
        JFXTreeTableColumn<JFXMatch, String> bidAddressColumn = createBidAddressColumn();
        JFXTreeTableColumn<JFXMatch, String> askBasisColumn = createAskBasisColumn();
        JFXTreeTableColumn<JFXMatch, String> bidBasisColumn = createBidBasisColumn();
        JFXmatches = setTreeView(askAddressColumn, bidAddressColumn, askBasisColumn, bidBasisColumn, matchesTable);
    }
    
    public void setParentController(ExchangeController parentController){
        this.parentController = parentController;
        pane = parentController.getPane();
    }
    
    private void updateParameters(){
        duration = parentController.getDuration();
        collateral = Utils.collateralMapping.get(parentController.getCollateral());
    }
    
    private JFXTreeTableColumn<JFXMatch, String> createAskAddressColumn(){
        JFXTreeTableColumn<JFXMatch, String> interestColumn = new JFXTreeTableColumn<>("Ask address");
        interestColumn.setPrefWidth(80);
        interestColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<JFXMatch, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (TreeTableColumn.CellDataFeatures<JFXMatch, String> param){
                return param.getValue().getValue().getAskAddress();
            }
        });
        return interestColumn;
    }
    
    private JFXTreeTableColumn<JFXMatch, String> createBidAddressColumn(){
        JFXTreeTableColumn<JFXMatch, String> interestColumn = new JFXTreeTableColumn<>("Bid address");
        interestColumn.setPrefWidth(80);
        interestColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<JFXMatch, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (TreeTableColumn.CellDataFeatures<JFXMatch, String> param){
                return param.getValue().getValue().getBidAddress();
            }
        });
        return interestColumn;
    }
    
    private JFXTreeTableColumn<JFXMatch, String> createAskBasisColumn(){
        JFXTreeTableColumn<JFXMatch, String> interestColumn = new JFXTreeTableColumn<>("Ask Basis");
        interestColumn.setPrefWidth(80);
        interestColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<JFXMatch, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (TreeTableColumn.CellDataFeatures<JFXMatch, String> param){
                return param.getValue().getValue().getAskBasis();
            }
        });
        return interestColumn;
    }
    
    private JFXTreeTableColumn<JFXMatch, String> createBidBasisColumn(){
        JFXTreeTableColumn<JFXMatch, String> interestColumn = new JFXTreeTableColumn<>("Bid Basis");
        interestColumn.setPrefWidth(80);
        interestColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<JFXMatch, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (TreeTableColumn.CellDataFeatures<JFXMatch, String> param){
                return param.getValue().getValue().getBidBasis();
            }
        });
        return interestColumn;
    }
    
    private ObservableList<JFXMatch> setTreeView(
            JFXTreeTableColumn<JFXMatch, String> firstColumn,
            JFXTreeTableColumn<JFXMatch, String> secondColumn,
            JFXTreeTableColumn<JFXMatch, String> thirdColumn,
            JFXTreeTableColumn<JFXMatch, String> fourthColumn,
            JFXTreeTableView<JFXMatch> table
        ){
        
        ObservableList<JFXMatch> list = FXCollections.observableArrayList();
        
        final TreeItem<JFXMatch> root = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        table.getColumns().setAll(firstColumn, secondColumn, thirdColumn, fourthColumn);
        table.setRoot(root);
        table.setShowRoot(false);
        
        return list;
    }
    
    @FXML
    private void createButtonClicked(ActionEvent event) {
        log.info("Creating loans.");
        updateParameters();
        log.info("Duration: {}, Collateral: {}", duration, collateral);

        createLoans = new Task() {
            final String JSON_URL1 = Utils.getDomain()+"/engine/getSortedOrders";
            final String JSON_URL2 = Utils.getDomain()+"/engine/matchOrders";
            final String JSON_URL3 = Utils.getDomain()+"/ledger/getLedgerCounter";
            final String JSON_URL4 = Utils.getDomain()+"/engine/createLoans";

            @Override
            protected String call() throws Exception {
                String sortedOrders = null;
                String matches = null;
                String transactionCount = null;
                String loanAddresses = null;
                
                String ledgerAddress = ledgerAddressInput.getText();
                String privateKey = privateKeyInput.getText();
                
                try {
                    sortedOrders = Utils.sendGET(log, JSON_URL1, "ledgerAddress", ledgerAddress);
                    matches = Utils.sendPOST(log, JSON_URL2, sortedOrders);
                    transactionCount = Utils.sendGET(log, JSON_URL3, "ledgerAddress", ledgerAddress);
                    loanAddresses = Utils.sendPOST(log, JSON_URL4, 
                            enrichForLoanCreation(
                                    matches, transactionCount,
                                    ledgerAddress, privateKey
                            )
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return loanAddresses;
            }
        };
        
        executorService.submit(createLoans);
        createLoans.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully created loans from ledger address {}", ledgerAddressInput.getText());
            Utils.showInfo(pane, createLoans.getValue());
        });
    }
    
    @FXML
    private void updateButtonClicked(ActionEvent event) {
        log.info("Getting matches.");
        updateParameters();
        log.info("Duration: {}, Collateral: {}", duration, collateral);
        
        getMatches = new Task() {
            final String JSON_URL1 = Utils.getDomain()+"/engine/getSortedOrders";
            final String JSON_URL2 = Utils.getDomain()+"/engine/matchOrders";

            @Override
            protected String call() throws Exception {
                String sortedOrders = null;
                String matches = null;
                String ledgerAddress = ledgerAddressInput.getText();
                try {
                    sortedOrders = Utils.sendGET(log, JSON_URL1, "ledgerAddress", ledgerAddress);
                    matches = Utils.sendPOST(log, JSON_URL2, sortedOrders);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return matches;
            }
        };
        
        executorService.submit(getMatches);
        getMatches.setOnSucceeded((WorkerStateEvent t) -> {
            log.info("Successfully received matches from ledger address {}", ledgerAddressInput.getText());
            fillTable(getMatches.getValue());
            Utils.showInfo(pane, "Success");
        });
    }    
    
    private void fillTable(final String matchesRaw){
        ArrayList<Match> matches = (new Gson()).fromJson(matchesRaw, new TypeToken<ArrayList<Match>>(){}.getType());
        JFXmatches.clear();
        for(Match match : matches){
            JFXmatches.add(new JFXMatch(
                match.getAskAddress(),
                match.getBidAddress(),
                match.getAskBasis(),
                match.getBidBasis()
            ));
        }
    }
    
    private String enrichForLoanCreation(
            final String matches,
            final String counter,
            final String ledgerAddress,
            final String privateKey
    ){
        EnrichedMatches enrichedMatches = new EnrichedMatches(
            counter, 
            ledgerAddress, 
            privateKey,
            (new Gson()).fromJson(matches, new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType())                
        );
        return (new Gson()).toJson(enrichedMatches); 
    }
        
    private class Match{

        private String bidAddress;
        private String bidBasis;
        private String askAddress;

        public String getBidAddress() {
            return bidAddress;
        }

        public String getBidBasis() {
            return bidBasis;
        }

        public String getAskAddress() {
            return askAddress;
        }

        public String getAskBasis() {
            return askBasis;
        }
        private String askBasis;    
        
    }
    
    private class JFXMatch extends RecursiveTreeObject<JFXMatch>{
        private StringProperty askAddress;
        private StringProperty bidAddress;
        private StringProperty askBasis;
        private StringProperty bidBasis;

        public StringProperty getAskAddress() {
            return askAddress;
        }

        public StringProperty getBidAddress() {
            return bidAddress;
        }

        public StringProperty getAskBasis() {
            return askBasis;
        }

        public StringProperty getBidBasis() {
            return bidBasis;
        }
        
        public JFXMatch (
                final String askAddress,
                final String bidAddress,
                final String askBasis,
                final String bidBasis
        ){
            this.askAddress = new SimpleStringProperty(askAddress);
            this.bidAddress = new SimpleStringProperty(bidAddress);
            this.askBasis = new SimpleStringProperty(askBasis);
            this.bidBasis = new SimpleStringProperty(bidBasis);
        }
    }
    
    private class EnrichedMatches{
        private String ledgerCounter;
        private String ledgerAddress;
        private String privateKey;
        private ArrayList<HashMap<String, String>> transactions;

        public EnrichedMatches(String ledgerCounter, String ledgerAddress, String privateKey, ArrayList<HashMap<String, String>> transactions) {
            this.ledgerCounter = ledgerCounter;
            this.ledgerAddress = ledgerAddress;
            this.privateKey = privateKey;
            this.transactions = transactions;
        }
        
    }
}
