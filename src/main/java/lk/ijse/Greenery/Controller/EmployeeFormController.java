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
import lk.ijse.Greenery.model.Employee;
import lk.ijse.Greenery.model.Tm.EmployeeTm;
import lk.ijse.Greenery.repository.EmployeeRepo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeFormController {
    @FXML
    private TableColumn<?, ?> DescriptionColumn;

    @FXML
    private TableColumn<?, ?> EmployeeAddressColumn;

    @FXML
    private TableColumn<?, ?> EmployeeContactColumn;

    @FXML
    private TableColumn<?, ?> EmployeeIdColumn;

    @FXML
    private TableColumn<?, ?> EmployeeNICColumn;

    @FXML
    private TableColumn<?, ?> EmployeeNameColumn;

    @FXML
    private TableView<EmployeeTm> empTable;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TextField txtEmployeeAddress;

    @FXML
    private TextField txtEmployeeContact;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeNIC;
    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtEmployeeName;
    private List<Employee> employeeList = new ArrayList<Employee>();


    public void initialize() {
        this.employeeList = getAllEmployee();
        setCellDataFactory();
        loadAllCustomer();
    }

    private void loadAllCustomer() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (Employee employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(

                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getEmployeeAddress(),
                    employee.getEmployeeContact(),
                    employee.getDescription(),
                    employee.getEmployeeNIC()


            );
            tmList.add(employeeTm);

        }

        empTable.setItems(tmList);
        EmployeeTm selectedItem = empTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);


    }



    private java.util.List<Employee> getAllEmployee() {
        java.util.List<Employee> employeeList  = null;
        try {
            employeeList  = EmployeeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private void setCellDataFactory() {
        EmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        EmployeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        EmployeeAddressColumn.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        EmployeeContactColumn.setCellValueFactory(new PropertyValueFactory<>("employeeContact"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        EmployeeNICColumn.setCellValueFactory(new PropertyValueFactory<>("employeeNIC"));



    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboardmain_form.fxml"));
        Stage stage = (Stage) paneHolder.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }


    @FXML
    public void btnDeleteOnAction(javafx.event.ActionEvent actionEvent) {


        String employeeId = txtEmployeeId.getText();

        try {
            boolean isDeleted = EmployeeRepo.delete(employeeId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeAddress =  txtEmployeeAddress.getText();
        String employeeContact = txtEmployeeContact.getText();
        String  description= txtDescription.getText();
        String employeeNIC = txtEmployeeNIC.getText();


        Employee employee = new Employee(employeeId,employeeName,employeeAddress,employeeContact,description,employeeNIC);

        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
            }
            txtEmployeeId.setText("");
            txtEmployeeName.setText("");
            txtEmployeeAddress.setText("");
            txtEmployeeContact.setText("");
            txtDescription.setText("");
            txtEmployeeNIC.setText("");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeAddress =  txtEmployeeAddress.getText();
        String employeeContact = txtEmployeeContact.getText();
        String description = txtDescription.getText();
        String employeeNIC = txtEmployeeNIC.getText();


        Employee employee = new Employee(employeeId,employeeName,employeeAddress,employeeContact,description,employeeNIC);
        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void employeeSearchOnAction(ActionEvent actionEvent) {

            String  employeeId = txtEmployeeId.getText();

            try {
                Employee employee = EmployeeRepo.searchById( employeeId);

                if (employee != null) {
                    txtEmployeeId.setText(employee.getEmployeeId());
                    txtEmployeeName.setText(employee.getEmployeeName());
                    txtEmployeeAddress.setText(employee.getEmployeeAddress());
                    txtEmployeeContact.setText(employee.getEmployeeContact());
                    txtDescription.setText(employee.getDescription());
                    txtEmployeeNIC.setText(employee.getEmployeeNIC());


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
    void employeeIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(E[0-9]{3})$");
        if (!idPattern.matcher(txtEmployeeId.getText()).matches()) {
            addError(txtEmployeeId);

        }else{
            removeError(txtEmployeeId);
}


    }

}




