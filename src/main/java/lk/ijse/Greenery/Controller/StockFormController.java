package lk.ijse.Greenery.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Greenery.model.Stock;
import lk.ijse.Greenery.model.Tm.StockTm;
import lk.ijse.Greenery.repository.StockRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StockFormController {

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStockId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<StockTm> tableStock;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtStockId;

    @FXML
    private TextField txtType;


    private List<Stock> stockList = new ArrayList<>();

    public void initialize() {
        this.stockList = getAllStock();
        setCellDataFactory();
        loadAllStock();
    }

    private void loadAllStock() {
        ObservableList<StockTm> tmList = FXCollections.observableArrayList();

        for (Stock stock : stockList) {
         StockTm stockTm = new StockTm(
                   stock.getStockId(),
                    stock.getName(),
                    stock.getQtyOnHand(),
                    stock.getType()


            );
            tmList.add(stockTm);

        }

        tableStock.setItems(tmList);
        StockTm selectedItem = (StockTm) tableStock.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);

    }

    private void setCellDataFactory() {
        colStockId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

    }


    private List<Stock> getAllStock() {
        List<Stock> stockList = null;
        try {
            stockList = StockRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stockList;
    }






    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String stockId = txtStockId.getText();

        try {
            boolean isDeleted = StockRepo.delete(stockId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String stockId = txtStockId.getText();
        String name = txtName.getText();
        String qtyOnHand =  txtQtyOnHand.getText();
        String type = txtType.getText();



        Stock stock = new Stock(stockId,name,qtyOnHand, type);

        try {
            boolean isSaved = StockRepo.save(stock);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
            }

            txtStockId.setText("");
            txtName.setText("");
            txtQtyOnHand.setText("");
            txtType.setText("");

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String stockId = txtStockId.getText();
        String name = txtName.getText();
        String qtyOnHand =  txtQtyOnHand.getText();
        String type = txtType.getText();

        Stock stock= new Stock(stockId,name,qtyOnHand, type);

        try {
            boolean isUpdated = StockRepo.update(stock);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    @FXML
    void stockIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(ST[0-9]{3})$");
        if (!idPattern.matcher(txtStockId.getText()).matches()) {
            addError(txtStockId);

        }else{
            removeError(txtStockId);
        }


    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboardmain_form.fxml"));
        Stage stage = (Stage) paneHolder.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }


    public void stocksearchById(ActionEvent actionEvent) {
    }
}
