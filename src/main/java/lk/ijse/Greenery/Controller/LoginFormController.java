package lk.ijse.Greenery.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.User;
import lk.ijse.Greenery.repository.UserRepo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;


   @FXML
   void btnLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException {
       String userName = txtUserName.getText();
       String password = txtPassword.getText();
       if (userName.isEmpty() || password.isEmpty()) {
           // Set border color to red if username or password is empty
           if (userName.isEmpty()) {
               txtUserName.setStyle("-fx-border-color: red;");
           } else {
               txtUserName.setStyle("-fx-border-color: green;");
           }

           if (password.isEmpty()) {
               txtPassword.setStyle("-fx-border-color: red;");
           } else {
               txtPassword.setStyle("-fx-border-color: green;");
           }

           new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
       } else {
           // Reset border color if both fields are filled
           txtUserName.setStyle("-fx-border-color: green;");
           txtPassword.setStyle("-fx-border-color: green;");
           User user = UserRepo.setLoginOnDetail(userName);
           if (user != null) {
               if (user.getPassWord().equals(password)) {
                   navigateDashboard();
               } else {
                   new Alert(Alert.AlertType.ERROR, "Password is incorrect").show();
               }
           } else {
               new Alert(Alert.AlertType.ERROR, "User name not found").show();
           }
       }
   }
    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;   -fx-border-width: 5");
    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-border-width: 5");
    }

    @FXML
    void passWordOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[0-9]{1,}$");
        if (!idPattern.matcher(txtPassword.getText()).matches()) {
            addError(txtPassword);

        } else {
            removeError(txtPassword);
        }
    }

    private void checkCredential(String username, String pw) throws SQLException, IOException {
        String sql = "SELECT userName, passWord FROM User WHERE userName = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, username);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String dbPw = resultSet.getString(2);

            if (dbPw.equals(pw)) {
                navigateDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "userName is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "user name  not found!").show();
        }
    }

    private void navigateDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Dashboardmain_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("DashboardMain Form");

    }

    @FXML
    void btnSignOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Register_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("SingUp Form");
    }


}


