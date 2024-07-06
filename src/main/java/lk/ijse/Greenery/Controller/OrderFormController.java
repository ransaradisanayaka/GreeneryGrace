package lk.ijse.Greenery.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import lk.ijse.Greenery.model.CustomerDTo;
import lk.ijse.Greenery.model.OrdersDTo;

import lk.ijse.Greenery.model.Tm.OrderTm;

import lk.ijse.Greenery.repository.OrdersRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class OrderFormController {

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderQty;
    @FXML
    private TableColumn<?, ?> colCustomerId;
    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<OrderTm> orderTable;
    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtOrderQty;
    @FXML
    private ComboBox<String> cmbCustomer;
    private List<OrdersDTo> orderList = new ArrayList<>();


    public void initialize() {
        this.orderList = getAllOrder();
        setCellDataFactory();
        loadAllOrder();
      //  getCustomerId();
    }

    /*private void getCustomerId() {

            ObservableList<String> obList = FXCollections.observableArrayList();
            try {
                List<String> codeList = CustomerRepo.getId();
                for (String code : codeList) {
                    obList.add(code);
                }

                cmbCustomer.setItems(obList);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

*/

    private List<OrdersDTo> getAllOrder() {
        List<OrdersDTo> orderList  ;
        try {
            orderList  = OrdersRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;


    }

    private void setCellDataFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));


    }

    private void loadAllOrder() {
        ObservableList<OrderTm> tmList = FXCollections.observableArrayList();

        for (OrdersDTo orders : orderList) {
            OrderTm orderTm = new OrderTm(

                    orders.getOrderId(),
                    orders.getOrderQty(),
                    orders.getAmount(),
                    orders.getDescription(),
                    orders.getCustomerId()


            );
            tmList.add(orderTm);

        }

        orderTable.setItems(tmList);
        OrderTm selectedItem = orderTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);


    }

      @FXML
  /*  public void btnDeleteOnAction(javafx.event.ActionEvent actionEvent) {


        String orderId = txtOrderId.getText();

        try {
           boolean isDeleted =OrdersRepo.delete(orderId);if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
*/
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        String orderQty = txtOrderQty.getText();
        String amount =  txtAmount.getText();
        String description = txtDescription.getText();
        String customerId= cmbCustomer.getValue();

         boolean hasError = false;
        // Set border color of empty text fields to red
        if (txtOrderId.getText().isEmpty()) {
            txtOrderId.setStyle("-fx-border-color: red;");
            hasError = true;
        } else {
            txtOrderId.setStyle("");

        }
        if (txtOrderQty.getText().isEmpty()) {
            txtOrderQty.setStyle("-fx-border-color: red;");
            hasError = true;
        } else {
            txtOrderQty.setStyle("");

        }

        if (txtAmount.getText().isEmpty()) {
            txtAmount.setStyle("-fx-border-color: red;");
            hasError = true;
        } else {
            txtAmount.setStyle("");

        }

        if (txtDescription.getText().isEmpty()) {
            txtDescription.setStyle("-fx-border-color: red;");
            hasError = true;
        } else {
            txtDescription.setStyle("");

        }


        if(hasError) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.show();
        }
        CustomerDTo customer = new CustomerDTo(orderId, orderQty, amount, description);

       /* try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
           }

           txtOrderId.setText("");
            txtOrderQty.setText("");
            txtAmount.setText("");
            txtDescription.setText("");


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/

    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String orderId = txtOrderId.getText();
        String orderQty = txtOrderQty.getText();
        String amount =  txtAmount.getText();
        String description = txtDescription.getText();
        String customerId = cmbCustomer.getValue();

        OrdersDTo orders = new OrdersDTo(orderId,orderQty,amount,description,customerId);
        try {
            boolean isUpdated = OrdersRepo.update(orders);
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
    void orderIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(O[0-9]{3})$");
        if (!idPattern.matcher(txtOrderId.getText()).matches()) {
            addError(txtOrderId);

        }else{
            removeError(txtOrderId);
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


    public void orderIdOnAction(ActionEvent actionEvent) throws SQLException {
        String  orderId = txtOrderId.getText();

        OrdersDTo orders = OrdersRepo.searchById( orderId);

        if (orders != null) {
           txtOrderId.setText(orders.getOrderId());
            txtOrderQty.setText(orders.getOrderQty());

            txtAmount.setText(orders.getAmount());
            txtDescription.setText(orders.getDescription());

        }
    }

}