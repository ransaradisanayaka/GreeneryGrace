package lk.ijse.Greenery.Controller;

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
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegisterFormController {
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPw;

    @FXML
    private TextField txtUserId;




    private void saveUser(String userId, String userName, String passWord) {
        try {
            String sql = "INSERT INTO user VALUES(?, ?, ?)";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, userId);
            pstm.setObject(2, userName);
            pstm.setObject(3, passWord);


            if(pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }
    }
    private void removeError(TextField textfield) {
        textfield.setStyle("-fx-border-color: green; -fx-border-width: 5");
    }

    private void addError(TextField textfield) {
        textfield.setStyle("-fx-border-color: red; -fx-border-width: 5");
    }
    @FXML
    void txtIdOnReleasedOnAction(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(U[0-9]{3})$");
        if (!idPattern.matcher(txtUserId.getText()).matches()) {
            addError(txtUserId);

        }else{
            removeError(txtUserId);
        }
    }

    @FXML
    void txtNameOnReleasedOnAction(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!idPattern.matcher(txtName.getText()).matches()) {
            addError(txtName);

        }else{
            removeError(txtName);
        }
    }

    @FXML
    void txtPasswordOnReleasedOnAction(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        if (!idPattern.matcher(txtPw.getText()).matches()) {
            addError(txtPw);

        }else{
            removeError(txtPw);
        }
    }
    public void btnSignIn(ActionEvent actionEvent) throws IOException, SQLException {
        String userId = txtUserId.getText();
        String userName = txtName.getText();
        String passWord = txtPw.getText();

        if (userName.isEmpty() || passWord.isEmpty()) {
            // Set border color to red if username or password is empty
            if (userName.isEmpty()) {
                txtName.setStyle("-fx-border-color: red;");
            } else {
                txtName.setStyle("-fx-border-color: green;");
            }

            if (passWord.isEmpty()) {
                txtPw.setStyle("-fx-border-color: red;");
            } else {
                txtPw.setStyle("-fx-border-color: green;");
            }

            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
        } else {
            // Reset border color if both fields are filled
            txtName.setStyle("-fx-border-color: green;");
            txtPw.setStyle("-fx-border-color: green;");
            User user = UserRepo.setLoginOnDetail(userName);
            saveUser(userId, userName, passWord);
        }
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Dashboardmain_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

}