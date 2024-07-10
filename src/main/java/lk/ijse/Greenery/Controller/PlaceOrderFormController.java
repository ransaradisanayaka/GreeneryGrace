package lk.ijse.Greenery.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Greenery.bo.BOFactory;
import lk.ijse.Greenery.bo.PlaceOrderBO;
import lk.ijse.Greenery.dto.CustomerDTO;
import lk.ijse.Greenery.dto.OrderDetailDTO;
import lk.ijse.Greenery.dto.OrdersDTO;
import lk.ijse.Greenery.dto.ProductDTO;
import lk.ijse.Greenery.entity.OrderDetail;
import lk.ijse.Greenery.model.Tm.OrderDetailTm;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PlaceOrderFormController {
    public AnchorPane root;
    public JFXButton btnPlaceOrder;
    public TextField txtCustomerName;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public JFXButton btnSave;
    public TableView<OrderDetailTm> tblOrderDetails;
    public TextField txtUnitPrice;
    public JFXComboBox<String> cmbCustomerId;
    public JFXComboBox<String> cmbProductId;
    public TextField txtQty;
    public Label lblId;
    public Label lblDate;
    public Label lblTotal;
    private String orderId;

    PlaceOrderBO placeOrderBO  = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PO);
    public void initialize() throws SQLException, ClassNotFoundException {

        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<OrderDetailTm, Button> lastCol = (TableColumn<OrderDetailTm, Button>) tblOrderDetails.getColumns().get(5);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(event -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisablePlaceOrderButton();
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        orderId = generateNewOrderId();
        lblId.setText("Order ID: " + orderId);
        lblDate.setText(LocalDate.now().toString());
        btnPlaceOrder.setDisable(true);
        txtCustomerName.setFocusTraversable(false);
        txtCustomerName.setEditable(false);
        txtDescription.setFocusTraversable(false);
        txtDescription.setEditable(false);
        txtUnitPrice.setFocusTraversable(false);
        txtUnitPrice.setEditable(false);
        txtQtyOnHand.setFocusTraversable(false);
        txtQtyOnHand.setEditable(false);
        txtQty.setOnAction(event -> btnSave.fire());
        txtQty.setEditable(false);
        btnSave.setDisable(true);

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisablePlaceOrderButton();
            if (newValue != null) {
                try {
                    try {
                        if (!existCustomer(newValue + "")) {
                            //"There is no such customer associated with the customerId " + customerId
                            new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the customerId " + newValue + "").show();
                        }
                        //Search Customer

                        CustomerDTO customerDTO = placeOrderBO.searchCustomer(newValue + "");
                        txtCustomerName.setText(customerDTO.getCustomerName());

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to find the customer " + newValue + "" + e).show();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                txtCustomerName.clear();
            }
        });


        cmbProductId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newProductId) -> {
            txtQty.setEditable(newProductId != null);
            btnSave.setDisable(newProductId == null);

            if (newProductId != null) {

                /*Find Product*/
                try {
                    if (!existProduct(newProductId + "")) {
//                        throw new NotFoundException("There is no such item associated with the id " + code);
                    }

                    //Search Product
                    ProductDTO product = placeOrderBO.searchIProduct(newProductId + "");

                    txtDescription.setText(product.getDescription());
                    txtUnitPrice.setText(product.getUnitPrice());

//                    txtQtyOnHand.setText(tblOrderDetails.getItems().stream().filter(detail-> detail.getCode().equals(item.getCode())).<Integer>map(detail-> item.getQtyOnHand() - detail.getQty()).findFirst().orElse(item.getQtyOnHand()) + "");
                    Optional<OrderDetailTm> optOrderDetail = tblOrderDetails.getItems().stream().filter(detail -> detail.getProductId().equals(newProductId)).findFirst();
              //      txtQtyOnHand.setText((optOrderDetail.isPresent() ? product.getQty() - optOrderDetail.get().getQty() : product.getQty()) + "");

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                txtDescription.clear();
                txtQty.clear();
                txtQtyOnHand.clear();
                txtUnitPrice.clear();
            }
        });

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {

            if (selectedOrderDetail != null) {
                cmbProductId.setDisable(true);
                cmbProductId.setValue(selectedOrderDetail.getProductId());
                btnSave.setText("Update");
                txtQtyOnHand.setText(Integer.parseInt(txtQtyOnHand.getText()) + selectedOrderDetail.getQty() + "");
                txtQty.setText(selectedOrderDetail.getQty() + "");
            } else {
                btnSave.setText("Add");
                cmbProductId.setDisable(false);
                cmbProductId.getSelectionModel().clearSelection();
                txtQty.clear();
            }

        });

        loadAllCustomerIds();
        loadAllProductId();
    }

    private boolean existProduct(String productId) throws SQLException, ClassNotFoundException {
        return placeOrderBO.existProduct(productId);
    }

    boolean existCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return placeOrderBO.existCustomer(customerId);
    }

    public String generateNewOrderId() {
        try {
            return placeOrderBO.generateOrderID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "OID-001";
    }

    private void loadAllCustomerIds() {
        try {
            ArrayList<CustomerDTO> allCustomers = placeOrderBO.getAllCustomers();
            for (CustomerDTO c : allCustomers) {
                cmbCustomerId.getItems().add(c.getCustomerId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllProductId() {
        try {
            /*Get all product*/
            ArrayList<ProductDTO> allProduct = placeOrderBO.getAllProduct();
            for (ProductDTO p : allProduct) {
                cmbProductId.getItems().add(p.getProductId());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/pos/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0 ||
                Integer.parseInt(txtQty.getText()) > Integer.parseInt(txtQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            txtQty.selectAll();
            return;
        }

        String productId = cmbProductId.getSelectionModel().getSelectedItem();
        String description = txtDescription.getText();
        BigDecimal unitPrice =new BigDecimal( txtUnitPrice.getText()).setScale(2);
        String qty = txtQty.getText();
        BigDecimal total = unitPrice.multiply(new BigDecimal(qty)).setScale(2);

        boolean exists = tblOrderDetails.getItems().stream().anyMatch(detail -> detail.getProductId().equals(productId));

        if (exists) {
            OrderDetailTm orderDetailTm = tblOrderDetails.getItems().stream().filter(detail -> detail.getProductId().equals(productId)).findFirst().get();

            if (btnSave.getText().equalsIgnoreCase("Update")) {
                orderDetailTm.setQty(qty);
                orderDetailTm.setTotal(total);
                tblOrderDetails.getSelectionModel().clearSelection();
            } else {
                orderDetailTm.setQty(orderDetailTm.getQty() + qty);
                total = new BigDecimal(orderDetailTm.getQty()).setScale(2);
                orderDetailTm.setTotal(total);
            }
            tblOrderDetails.refresh();
        } else {
            tblOrderDetails.getItems().add(new OrderDetailTm(productId, qty, description ,unitPrice, total));
        }
        cmbProductId.getSelectionModel().clearSelection();
        cmbProductId.requestFocus();
        calculateTotal();
        enableOrDisablePlaceOrderButton();
    }

    private void calculateTotal() {
        BigDecimal total = new BigDecimal(0);

        for (OrderDetailTm detail : tblOrderDetails.getItems()) {
            total = total.add(detail.getTotal());
        }
        lblTotal.setText("Total: " + total);
    }

    private void enableOrDisablePlaceOrderButton() {
        btnPlaceOrder.setDisable(!(cmbCustomerId.getSelectionModel().getSelectedItem() != null && !tblOrderDetails.getItems().isEmpty()));
    }

    public void txtQty_OnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {
        try {
            boolean b = saveOrder(orderId, LocalDate.now(), cmbCustomerId.getValue(),
                    tblOrderDetails.getItems().stream().map(tm -> new OrderDetailDTO(orderId, tm.getProductId(),tm.getQty(),tm.getUnitPrice())).collect(Collectors.toList()));

            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



        orderId = generateNewOrderId();
        lblId.setText("Order Id: " + orderId);
        cmbCustomerId.getSelectionModel().clearSelection();
        cmbProductId.getSelectionModel().clearSelection();
        tblOrderDetails.getItems().clear();
        txtQty.clear();
        calculateTotal();
    }

    public boolean saveOrder(String orderId,LocalDate orderDate, String customerId ,List<OrderDetailDTO>OrderDetail) throws SQLException, ClassNotFoundException {
        OrdersDTO orderDTO = new OrdersDTO(orderId, orderDate,customerId);
        return placeOrderBO.placeOrder(orderDTO);
    }


    /*

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXComboBox<?> cmbCusId;

    @FXML
    private JFXComboBox<?> cmbOrderId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private TableView<OrderDetailTm> tablePlaceOrder;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;









public void initialize() throws SQLException, ClassNotFoundException {

    colCustomerId.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
    tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("productId"));
    tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
    tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
    TableColumn<OrderDetailTm, Button> lastCol = (TableColumn<OrderDetailTm, Button>) tblOrderDetails.getColumns().get(5);

    lastCol.setCellValueFactory(param -> {
        Button btnDelete = new Button("Delete");

        btnDelete.setOnAction(event -> {
            tblOrderDetails.getItems().remove(param.getValue());
            tblOrderDetails.getSelectionModel().clearSelection();
            calculateTotal();
            enableOrDisablePlaceOrderButton();
        });

        return new ReadOnlyObjectWrapper<>(btnDelete);
    });

    orderId = generateNewOrderId();
    lblId.setText("Order ID: " + orderId);
    lblDate.setText(LocalDate.now().toString());
    btnPlaceOrder.setDisable(true);
    txtCustomerName.setFocusTraversable(false);
    txtCustomerName.setEditable(false);
    txtDescription.setFocusTraversable(false);
    txtDescription.setEditable(false);
    txtUnitPrice.setFocusTraversable(false);
    txtUnitPrice.setEditable(false);
    txtQty.setFocusTraversable(false);
    txtQty.setEditable(false);
    txtQty.setOnAction(event -> b.fire());
    txtQty.setEditable(false);
    b.setDisable(true);

    .getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        enableOrDisablePlaceOrderButton();

        if (newValue != null) {
            try {

                try {
                    if (!existCustomer(newValue + "")) {
                        //"There is no such customer associated with the id " + id
                        new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + newValue + "").show();
                    }
                    //Search CustomerCustomerDTO customerDTO = customerDAO.searchCustomer(newValue + "");
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Failed to find the customer " + newValue + "" + e).show();
                }
            }  catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
          txtCusname.clear();
        }
    });


    colProductId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
        txtQty.setEditable(newItemCode != null);
        btnSave.setDisable(newItemCode == null);

        if (newItemCode != null) {

            /*Find Item*/
           /* try {
                if (!existItem(newItemCode + "")) {
//                        throw new NotFoundException("There is no such item associated with the id " + code);
                }
                //Search Item
               // ItemDTO item = itemDAO.searchItem(newItemCode + "");

               // txtDescription.setText(item.getDescription());
            //    txtUnitPrice.setText(item.getUnitPrice().setScale(2).toString());

//                    txtQtyOnHand.setText(tblOrderDetails.getItems().stream().filter(detail-> detail.getCode().equals(item.getCode())).<Integer>map(detail-> item.getQtyOnHand() - detail.getQty()).findFirst().orElse(item.getQtyOnHand()) + "");
                Optional<OrderDetailTm> optOrderDetail = tblOrderDetails.getItems().stream().filter(detail -> detail.getCode().equals(newItemCode)).findFirst();
                txtQty.setText((optOrderDetail.isPresent() ? item.getQtyOnHand() - optOrderDetail.get().getQty() : item.getQtyOnHand()) + "");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            txtDescription.clear();
            txtQty.clear();
            txtQty.clear();
            txtUnitPrice.clear();
        }
    });

    tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {

        if (selectedOrderDetail != null) {
            colProductId.setDisable(true);
            colProductId.setValue(selectedOrderDetail.getCode());
            btnSave.setText("Update");
            txtQty.setText(Integer.parseInt(txtQty.getText()) + selectedOrderDetail.getQty() + "");
            txtQty.setText(selectedOrderDetail.getQty() + "");
        } else {
            btnSave.setText("Add");
            txtProductId.setDisable(false);
            txtProductId.getSelectionModel().clearSelection();
            txtQty.clear();
        }

    });

    loadAllCustomerId();
    loadAllItemCodes();
}

private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));


    }

        private void getProductId() {
            ObservableList<String> obList = FXCollections.observableArrayList();
            try {
                List<String> codeList = ProductRepo.getCodes();
                for (String code : codeList) {
                    obList.add(code);
                }

                setItems(obList);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

       private void getCustomerId() {

            ObservableList<String> obList = FXCollections.observableArrayList();

           try {
               List<String> idList = CustomerRepo.getCustomerId();

                for (String id : idList) {
                    obList.add(id);
              }
                colCustomerId.setItems(obList);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    private void loadNextOrderId() throws SQLException {
        String currentId = OrdersRepo.currentId();
        String nextId = nextId(currentId);

       cmbOrderId.setText(nextId);
    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("O");
            System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
            int id = Integer.parseInt(split[1]);    //2
            return "O" + ++id;

        }
        return "O1";
    }



        @FXML
        void btnAddToCartOnAction(ActionEvent event) {
            String  customerId= cmbCusId.getText();
            String  productId= txtProductId.getText();

            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double amount = qty * unitPrice;
            String desc = txtDescription.getText();
            JFXButton btnRemove = new JFXButton("remove");
            btnRemove.setCursor(Cursor.HAND);

            btnRemove.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if(type.orElse(no) == yes) {
                    int selectedIndex = tablePlaceOrder.getSelectionModel().getSelectedIndex();
                    cartList.remove(selectedIndex);

                    tablePlaceOrder.refresh();
                    calculateNetTotal();
                }
            });

            for (int i = 0; i < tablePlaceOrder.getItems().size(); i++) {
                if (productId.equals(txtProductId.getCellData(i))) {
                    qty += cartList.get(i).getQty();
                    amount= unitPrice * qty;

                    cartList.get(i).setQty(orderQty);
                    cartList.get(i).setAmount(amount);

                    tablePlaceOrder.refresh();
                    calculateNetTotal();
                    txtQty.setText("");
                    return;
                }
            }

            CartTm cartTm = new CartTm(customerId,productId,unitPrice,qty,amount,description);

            cartList.add(cartTm);

            tablePlaceOrder.setItems(cartList);
            txtQty.setText("");
            calculateNetTotal();
        }

        private void calculateNetTotal() {
            netTotal = 0;
            for (int i = 0; i < tablePlaceOrder.getItems().size(); i++) {
                netTotal += (double) colAmount.getCellData(i);
            }
            lblNetTotal.setText(String.valueOf(netTotal));
        }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException, SQLException, IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Dashboard_form.fxml"));
        Stage stage = (Stage) paneHolder.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();


        void btnPlaceOrderOnAction(ActionEvent ) {
            String orderId = txtOrderId.getText();
            String orderQty = txtQty.getText();
            String amount = txtAmount.getText();
            String description = txtDescription.getText();
            String customerId = colCustomerId.getText();





           Orders orders = new Orders(orderId,orderQty,amount,description, customerId);

            List<OrderDetail> odList = new ArrayList<>();
            for (int i = 0; i < tablePlaceOrder.getItems().size(); i++) {
                CartTm tm = cartList.get(i);

                OrderDetail od = new OrderDetail(
                        orderId,
                        tm.getProductId(),
                        tm.getQty(),
                        tm.getUnitPrice()
                );
                odList.add(od);
            }

            PlaceOrder po = new PlaceOrder(order, odList);
            try {
                boolean isPlaced = PlaceOrderRepo.orderPlace();
                if(isPlaced) {
                    new Alert(Alert.AlertType.CONFIRMATION, "order placed!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "order not placed!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


        void txtCustomerIdOnAction(ActionEvent event) {
            String cusId = cmbCusId.getText();

            try {
                Customer customer = CustomerRepo.searchById(cusId);

                colCustomerId.setText(customer.getCustomerName());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void txtProductOnAction(ActionEvent event){
            String code = txtProductId.getText();
                Product product = ProductRepo.searchById(code);
                if (product != null) {
                    txtProductId.setText(String.valueOf(product.getProductId()));
                   // txtproductName.setText(String.valueOf(product.getProductName()));
                    txtQty.setText(String.valueOf(product.getQty()));
                    txtDescription.setText(product.getDescription());
                    txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            txtQty.requestFocus();
        }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);

    }


*/
    }




