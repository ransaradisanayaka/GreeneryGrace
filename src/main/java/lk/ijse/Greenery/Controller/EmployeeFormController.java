package lk.ijse.Greenery.Controller;

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
import lk.ijse.Greenery.bo.EmployeeBo;
import lk.ijse.Greenery.model.EmployeeDTo;
import lk.ijse.Greenery.model.Tm.EmployeeTm;

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
    private List<EmployeeDTo> employeeList = new ArrayList<EmployeeDTo>();

EmployeeBo employeeBo= (EmployeeBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    public void initialize() {
      //  this.employeeList = getAllEmployee();
        setCellDataFactory();
        loadAllEmployee();
    }

    private void loadAllEmployee() {
     empTable.getItems().clear();
     try {
         ArrayList<lk.ijse.Greenery.dto.EmployeeDTO> allEmployee = employeeBo.getAllEmployee();
        for (lk.ijse.Greenery.dto.EmployeeDTO employee : allEmployee) {
           empTable.getItems().add(new EmployeeTm(

                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getEmployeeAddress(),
                    employee.getEmployeeContact(),
                    employee.getDescription(),
                    employee.getEmployeeNIC()


            ));}
         //   tmList.add(employeeTm);

        } catch (SQLException e) {
         new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }
    }



  /*  private java.util.List<EmployeeDTO> getAllEmployee() {
        java.util.List<EmployeeDTO> employeeList  = null;
        try {
            employeeList  = EmployeeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

   */
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
            boolean isDeleted = employeeBo.deleteEmployee(employeeId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeAddress =  txtEmployeeAddress.getText();
        String employeeContact = txtEmployeeContact.getText();
        String  description= txtDescription.getText();
        String employeeNIC = txtEmployeeNIC.getText();


      //  Employee employee = new Employee(employeeId,employeeName,employeeAddress,employeeContact,description,employeeNIC);

       employeeBo.saveEmployee(new lk.ijse.Greenery.dto.EmployeeDTO(employeeId,employeeName,employeeAddress,employeeContact,description,employeeNIC));
       empTable.getItems().add(new EmployeeTm(employeeId,employeeName,employeeAddress,employeeContact,description,employeeNIC));
       /*try {
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
        }*/


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeAddress =  txtEmployeeAddress.getText();
        String employeeContact = txtEmployeeContact.getText();
        String description = txtDescription.getText();
        String employeeNIC = txtEmployeeNIC.getText();


        EmployeeDTo employee = new EmployeeDTo(employeeId,employeeName,employeeAddress,employeeContact,description,employeeNIC);
        try {
            boolean isUpdated = employeeBo.updateEmployee(new lk.ijse.Greenery.dto.EmployeeDTO( employeeId,employeeName,employeeAddress,employeeContact,description,employeeNIC));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

    }

    /*public void employeeSearchOnAction(ActionEvent actionEvent) {

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

    }*/
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




