package lk.ijse.Greenery.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Greenery.bo.BOFactory;
import lk.ijse.Greenery.bo.PaymentBO;
import lk.ijse.Greenery.dto.PaymentDTO;
import lk.ijse.Greenery.model.PaymentDTo;
import lk.ijse.Greenery.model.Tm.PaymentTm;
import lk.ijse.Greenery.repository.PaymentRepo;
import java.awt.Button;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PaymentFormController {

        @FXML
        private Button backBtn;
        @FXML
        private JFXButton clearBtn;

        @FXML
        private TableColumn<?, ?> colAmount;
        @FXML
        private TableColumn<?, ?> colCustomerId;
        @FXML
        private TableColumn<?, ?> colDate;

        @FXML
        private TableColumn<?, ?> colPaymentId;

        @FXML
        private TableColumn<?, ?> colPaymentMethod;

        @FXML
        private JFXButton deleteBtn;

        @FXML
        private JFXButton saveBtn;

        @FXML
        private TextField txtAmount;

        @FXML
        private ComboBox<String> cmbCustomer;

        @FXML
        private TextField txtDate;

        @FXML
        private TextField txtPaymentId;

        @FXML
        private TextField txtPaymentMethod;
        @FXML
        private AnchorPane paneHolder;

        @FXML
        private TableView<PaymentTm> PaymentTable;

        @FXML
        private JFXButton updateBtn;

        private List<PaymentDTo> paymentList = new ArrayList<>();
PaymentBO paymentBO= (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
        public void initialize() throws SQLException, ClassNotFoundException {
         //  this.paymentList = getAllPayment();
            setCellDataFactory();
            loadAllPayment();
          //  getCustomerId();
        }

    /*    private void getCustomerId() {

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


        private void loadAllPayment() throws SQLException, ClassNotFoundException {
          PaymentTable.getItems().clear();
          ArrayList<PaymentDTO> allPayment = paymentBO.getAllPayment();

            for (PaymentDTO payment : allPayment) {
                PaymentTable.getItems().add( new PaymentTm(

                        payment.getPaymentId(),
                        payment.getPaymentMethod(),
                        payment.getAmount(),
                        payment.getDate(),
                        payment.getCustomerId()


                ));

            }

            }

        private void setCellDataFactory() {
            colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
            colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        }


      /*  private List<Pay> getAllPayment() {
            List<PaymentDTo> paymentList  = null;
            try {
                paymentList  = PaymentRepo.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return paymentList;

        }


*/
        @FXML
        void btnDeleteOnAction(ActionEvent event) {
            String customerId = cmbCustomer.getValue();

            try {
                boolean isDeleted = paymentBO.deletePayment(customerId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        @FXML
        void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
            String paymentId = txtPaymentId.getText();
            String paymentMethod = txtPaymentMethod.getText();
            String amount =  txtAmount.getText();
            String date = txtDate.getText();
            String customerId = cmbCustomer.getValue();


            PaymentDTO payment = new PaymentDTO(paymentId,paymentMethod,amount,date, customerId);
paymentBO.savePayment(payment);
PaymentTable.getItems().add(new PaymentTm(paymentId,paymentMethod,amount,date,customerId));
          /*  try {
                boolean isSaved = PaymentRepo.save(payment);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
                }
                txtPaymentId.setText("");
                txtPaymentMethod.setText("");
                txtAmount.setText("");
                txtDate.setText("");
                cmbCustomer.setValue(null);

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }*/
        }

        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String paymentId = txtPaymentId.getText();
            String paymentMethod = txtPaymentMethod.getText();
            String amount =  txtAmount.getText();
            String date = txtDate.getText();
            String customerId = cmbCustomer.getValue();


            PaymentDTO payment = new PaymentDTO(paymentId,paymentMethod,amount,date,customerId);

            try {
                boolean isUpdated =paymentBO.updatePayment(payment);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

     /*   @FXML
        void CustomerPaySearchOnAction(ActionEvent event) {
            String  customerId = cmbCustomer.getValue();

            try {
                PaymentDTo payment = PaymentRepo.searchById( customerId);

                if (payment != null) {
                    txtPaymentId.setText(payment.getPaymentId());
                    txtPaymentMethod.setText(payment.getPaymentMethod());
                    txtAmount.setText(payment.getAmount());
                    txtDate.setText(payment.getDate());
                    cmbCustomer.setValue(payment.getCustomerId());

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
*/
        public void searchById(ActionEvent actionEvent) {
        }
    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    @FXML
    void paymentIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(PY[0-9]{3})$");
        if (!idPattern.matcher(txtPaymentId.getText()).matches()) {
            addError(txtPaymentId);

        }else{
            removeError(txtPaymentId);
        }


    }
    /*public void paymentIdOnAction(ActionEvent actionEvent) throws SQLException {
        String  paymentId = txtPaymentId.getText();

        PaymentDTO payment = paymentBO..searchById( paymentId);

        if (payment != null) {
            txtPaymentId.setText(payment.getPaymentId());
            txtPaymentMethod.setText(payment.getPaymentMethod());
            txtAmount.setText(payment.getAmount());
            txtDate.setText(payment.getDate());

        }

    }*/
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboardmain_form.fxml"));
        Stage stage = (Stage) paneHolder.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }


}

