package lk.ijse.Greenery.Controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.Greenery.model.Customer;
import lk.ijse.Greenery.model.Tm.CustomerTm;
import lk.ijse.Greenery.repository.CustomerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFormController {


    @FXML
    private TableColumn<?, ?> customerAddressColmun;

    @FXML
    private TableColumn<?, ?> customerContactColmun;

    @FXML
    private TableColumn<?, ?> customerIdColumn;

    @FXML
    private TableColumn<?, ?> customerNameColmun;

    @FXML
    private TableView<CustomerTm> customerTable;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerContact;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private JFXButton updateBtn;

    private List<Customer> customerList = new ArrayList<>();

    public void initialize() throws SQLException {
        this.customerList = getAllCustomer();
        setCellDataFactory();
        loadAllCustomer();

    }

    private void loadAllCustomer() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        for (Customer customer : customerList) {
            CustomerTm customerTm = new CustomerTm(

                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerContact()

            );
            tmList.add(customerTm);


        }

        customerTable.setItems(tmList);
        CustomerTm selectedItem = customerTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);


    }

    private void setCellDataFactory() {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColmun.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColmun.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerContactColmun.setCellValueFactory(new PropertyValueFactory<>("customerContact"));


    }

    private List<Customer> getAllCustomer() throws SQLException {
        List<lk.ijse.Greenery.model.Customer> customerList = null;
        customerList = CustomerRepo.getAll();
        return customerList;


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String customerAddress = txtCustomerAddress.getText();
        String customerContact = txtCustomerContact.getText();

        boolean hasError = false;
        // Set border color of empty text fields to red
        if (txtCustomerId.getText().isEmpty()) {
            txtCustomerId.setStyle("-fx-border-color: red;");
            hasError = true;
        } else {
            txtCustomerId.setStyle("");

        }
        if (txtCustomerName.getText().isEmpty()) {
            txtCustomerName.setStyle("-fx-border-color: red;");
            hasError = true;
        } else {
            txtCustomerName.setStyle("");

        }

        if (txtCustomerAddress.getText().isEmpty()) {
            txtCustomerAddress.setStyle("-fx-border-color: red;");
            hasError = true;
        } else {
            txtCustomerAddress.setStyle("");

        }

        if (txtCustomerContact.getText().isEmpty()) {
            txtCustomerContact.setStyle("-fx-border-color: red;");
            hasError = true;
        } else {
            txtCustomerContact.setStyle("");

        }



        if(hasError) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.show();
        }
        Customer customer = new Customer(customerId, customerName, customerAddress, customerContact);

        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
            }

                txtCustomerId.setText("");
                txtCustomerName.setText("");
                txtCustomerAddress.setText("");
                txtCustomerContact.setText("");


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



}



    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {

            String customerId =  txtCustomerId.getText();
            String customerName = txtCustomerName.getText();
            String customerAddress = txtCustomerAddress.getText();
            String customerContact  = txtCustomerContact.getText();

            Customer customer = new Customer(customerId, customerName, customerAddress, customerContact );

            try {
                boolean isUpdated = CustomerRepo.update(customer);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }






    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String customerId = txtCustomerId.getText();

        try {
            boolean isDeleted = CustomerRepo.delete(customerId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }



    public void customerIdOnAction(ActionEvent actionEvent) throws SQLException {
        String  customerId = txtCustomerId.getText();

        Customer customer = CustomerRepo.searchById( customerId);

        if (customer != null) {
            txtCustomerId.setText(customer.getCustomerId());
            txtCustomerName.setText(customer.getCustomerName());

            txtCustomerAddress.setText(customer.getCustomerAddress());
            txtCustomerContact.setText(customer.getCustomerContact());

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

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;   -fx-border-width: 5");
    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-border-width: 5");
    }

    @FXML
    void customerIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(C[0-9]{3})$");
        if (!idPattern.matcher(txtCustomerId.getText()).matches()) {
            addError(txtCustomerId);

        } else {
            removeError(txtCustomerId);
        }
    }



}
