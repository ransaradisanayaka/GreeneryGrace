package lk.ijse.Greenery.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Greenery.Util.DateTimeUtil;

import java.io.IOException;

public class DashboardMainFormController {

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnOrder;
    @FXML
    private JFXButton btnSupplier;


    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnProduct;

    @FXML
    private JFXButton btnStock;

    @FXML
    private Label dateAndTimeLable;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private AnchorPane rootNode;

    public void initialize() throws IOException {
        realTime();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Dashboard_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);
    }

    private void realTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> dateAndTimeLable.setText(DateTimeUtil.timenow())));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        dateAndTimeLable.setText(DateTimeUtil.dateNow());

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Customer_Form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Dashboard_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);


    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Employee_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);


    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Stage stage1 = (Stage)btnLogout.getScene().getWindow();
        stage1.close();
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Login_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Login Form");

        stage.show();

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Order_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Payment_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);

    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Product_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);

    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Stock_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Supplier_form.fxml"));
        Pane registePane = (Pane) fxmlLoader.load();
        paneHolder.getChildren().clear();
        paneHolder.getChildren().add(registePane);

    }


}
