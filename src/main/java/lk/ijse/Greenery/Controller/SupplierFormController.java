package lk.ijse.Greenery.Controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.Greenery.bo.BOFactory;
import lk.ijse.Greenery.bo.SupplierBO;
import lk.ijse.Greenery.dto.SupplierDTO;
import lk.ijse.Greenery.model.SupplierDTo;
import lk.ijse.Greenery.model.Tm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SupplierFormController {

    @FXML
    private JFXButton clearBtn;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colSupAddress;

    @FXML
    private TableColumn<?, ?> colSupContact;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colSupNIC;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private TableView<SupplierTm> tableSupplier;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSupplierAddress;

    @FXML
    private TextField txtSupplierContact;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierNIC;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private AnchorPane paneHolder;


    private List<SupplierDTo> supplierList = new ArrayList<>();
SupplierBO supplierBO= (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    public void initialize() throws SQLException, ClassNotFoundException {
      //  this.supplierBO = getAllSupplier();
        setCellDataFactory();
        loadAllSupplier();
    }

    private void loadAllSupplier() throws SQLException, ClassNotFoundException {
        tableSupplier.getItems().clear();
        /*Get all items*/
        ArrayList<SupplierDTO> allSupplier = supplierBO.getAllSupplier();
        for (SupplierDTO s : allSupplier) {
            tableSupplier.getItems().add(new SupplierTm(s.getSupplierId(), s.getSupplierName(),s.getSupplerContact(),s.getSupplierAddress(),s.getDescription(),s.getSupplierNIC()));
        }
    }
       /* ObservableList<SupplierTm> tmList = FXCollections.observableArrayList();

        for (Supplier supplier : supplierList) {
            SupplierTm supplierTm = new SupplierTm(

                    supplier.getSupplierId(),
                    supplier.getSupplierName(),
                    supplier.getSupplerContact(),
                    supplier.getSupplierAddress(),
                    supplier.getSupplierNIC(),
                    supplier.getDescription()
            );
            tmList.add(supplierTm);

        }

        tableSupplier.setItems(tmList);
        SupplierTm selectedItem = (SupplierTm) tableSupplier.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }
*/
    private void setCellDataFactory() {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupContact.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        colSupNIC.setCellValueFactory(new PropertyValueFactory<>("supplierNIC"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

 /*   private List getAllSupplier() {

        List<Supplier> supplierList = null;
        try {
            supplierList = SupplierRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierList;


    }*/


    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplierId   = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String supplierContact = txtSupplierContact.getText();
        String supplierAddress = txtSupplierAddress.getText();
        String supplierNIC = txtSupplierNIC.getText();
        String description = txtDescription.getText();

        SupplierDTO supplier = new SupplierDTO(supplierId, supplierName, supplierContact, supplierAddress, supplierNIC, description);
supplierBO.saveSupplier(supplier);
tableSupplier.getItems().add(new SupplierTm(supplierId,supplierName,supplierContact,supplierAddress,supplierNIC,description));
      /*  try {
            boolean isSaved = SupplierRepo.saveSupplier(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
            }
            txtSupplierId.setText("");
            txtSupplierName.setText("");
            txtSupplierContact.setText("");
            txtSupplierAddress.setText("");
            txtSupplierNIC.setText("");
            txtDescription.setText("");

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String supplierId = txtSupplierId.getText();

        try {
            boolean isDeleted = supplierBO.deleteSupplier(supplierId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String supplierId= txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String supplierContact = txtSupplierContact.getText();
        String supplierAddress = txtSupplierAddress.getText();
        String supplierNIC = txtSupplierNIC.getText();
        String description = txtDescription.getText();


        SupplierDTO supplier = new SupplierDTO(supplierId, supplierName, supplierContact, supplierAddress, supplierNIC, description);

        try {
            boolean isUpdated = supplierBO.updateSupplier(supplier);
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
    void supplierIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(SP[0-9]{3})$");
        if (!idPattern.matcher(txtSupplierId.getText()).matches()) {
            addError(txtSupplierId);

        } else {
            removeError(txtSupplierId);
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

   /* public void supplierIdOnAction(ActionEvent actionEvent) throws SQLException {
        String supplierId = txtSupplierId.getText();

       // Supplier supplier = SupplierRepo.searchById(supplierId);

        if (supplier != null) {
            txtSupplierId.setText(supplier.getSupplierId());
            txtSupplierName.setText(supplier.getSupplierName());
            txtSupplierContact.setText(supplier.getSupplerContact());
            txtSupplierAddress.setText(supplier.getSupplierAddress());
            txtSupplierNIC.setText(supplier.getSupplierNIC());
            txtDescription.setText(supplier.getDescription());
        }


    }*/
}