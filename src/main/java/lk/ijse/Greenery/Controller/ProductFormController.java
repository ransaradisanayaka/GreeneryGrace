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
import lk.ijse.Greenery.bo.BOFactory;
import lk.ijse.Greenery.bo.ProductBO;
import lk.ijse.Greenery.dto.ProductDTO;
import lk.ijse.Greenery.model.ProductDTo;

import lk.ijse.Greenery.model.Tm.ProductTm;
import lk.ijse.Greenery.repository.ProductRepo;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProductFormController {
    @FXML
    private TableView<ProductTm> ProductTable;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQty;


    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TextField txtProductName;
    @FXML
    private AnchorPane root;



    private List<ProductDTo> productList = new ArrayList<>();
  ProductBO productBO= (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);

    public void initialize() throws SQLException, ClassNotFoundException {
    //    this.productList = getAllProduct();
        setCellDataFactory();
        loadAllProduct();
    }

    private void setCellDataFactory() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private void loadAllProduct() throws SQLException, ClassNotFoundException {
     ProductTable.getItems().clear();
     try {
         ArrayList<ProductDTO> allProduct = productBO.getAllProduct();

        for (ProductDTO product :allProduct) {
            ProductTable.getItems().add(new ProductTm(

                    product.getProductId(),
                    product.getProductName(),
                    product.getQty(),
                    product.getDescription(),
                    product.getUnitPrice()



            ) );
        }
          }catch (SQLException e) {
         new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

     }

        }




  /*  private List<ProductDTo> getAllProduct() {
        List<ProductDTo> productList  = null;
        try {
            productList  = ProductRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;

    }



*/
    /*
    @FXML
    void productSearchOnAction(ActionEvent event) {
     String  productId = txtProductId.getText();

        try {
            ProductDTo product= ProductRepo.searchById(productId);

            if (product != null) {
                txtProductId.setText(product.getProductId());
                txtProductName.setText(product.getProductName());
                txtQty.setText(product.getQty());
                txtDescription.setText(product.getDescription());
                txtUnitPrice.setText(product.getDescription());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
  }



    }
*/

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String productId = txtProductId.getText();

        try {
            boolean isDeleted = productBO.deleteProduct(productId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String productId = txtProductId.getText();
        String productName = txtProductName.getText();
        String qty =  txtQty.getText();
        String description = txtDescription.getText();
        String unitPrice= txtUnitPrice.getText();


        ProductDTO product = new ProductDTO(productId,productName,qty, description, unitPrice);
productBO.saveProduct(product);
ProductTable.getItems().add(new ProductTm(productId,productName,qty,description,unitPrice));
       /*try {
            boolean isSaved = ProductRepo.save(product);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
            }
            txtProductId.setText("");
            txtProductName.setText("");
            txtQty.setText("");
            txtDescription.setText("");
            txtUnitPrice.setText("");


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
*/
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String productId = txtProductId.getText();
        String productName = txtProductName.getText();
        String qty =  txtQty.getText();
        String description= txtDescription.getText();
        String unitPrice=txtUnitPrice.getText();

        ProductDTO product= new ProductDTO(productId,productName,qty, description, unitPrice);

        try {
            boolean isUpdated = productBO.updateProduct(product);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    @FXML
    void productIdOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(P[0-9]{3})$");
        if (!idPattern.matcher(txtProductId.getText()).matches()) {
            addError(txtProductId);

        }else{
            removeError(txtProductId);
        }


    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboardmain_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }


}
