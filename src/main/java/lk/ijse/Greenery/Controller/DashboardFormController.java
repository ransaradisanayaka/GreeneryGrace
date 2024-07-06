package lk.ijse.Greenery.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Greenery.dao.custom.CustomerDAO;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.repository.CustomerRepo;
import lk.ijse.Greenery.repository.OrdersRepo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class DashboardFormController {
    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private BarChart<String, Number> barChart;

    public void initialize() throws SQLException {
        getDailyOrderCount();
        getDailyCustomerCount();
      populateChart(barChart);
    //    lowStockLevel();

    }


    private void getDailyCustomerCount() throws SQLException {
       // CustomerDAO customerDAO = new CustomerDAO();
        //int count = customerDAO.countDailyCustomer();
       // lblCustomerCount.setText(String.valueOf("0" + count));

    }


    private void getDailyOrderCount() throws SQLException {
        OrdersRepo orderRepo = new OrdersRepo();
        int count = orderRepo.countDailyOrder();
        lblOrderCount.setText(String.valueOf("0" + count));

    }

/*
    private void lowStockLevel() {
        List<Stock.getName()>lowItem= StockRepo.getLowStockName();

        try {
            List<Stock> stock = StockRepo.getAll();
            boolean lowStockLevel=false;
            for (Stock stocks : stock) {
                if (Integer.parseInt(stocks.getQtyOnHand())<=20) {
                    lowStockLevel = true;
                    break;
                }
                }
            if (lowStockLevel){
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Low Stock Level Alert");
                        alert.setHeaderText("Low level stock found!");
                        alert.setContentText("Stock has low stock level. Quantity on hand: " );
                        alert.showAndWait();
                    });
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
private void populateChart(BarChart<String, Number> barChart) throws SQLException {
    Connection connection = DbConnection.getInstance().getConnection();
    String sql = "SELECT name, SUM(qtyOnHand) AS total_qty FROM stock GROUP BY name";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Current Stock Quantity ");

    while (resultSet.next()) {
        String name= resultSet.getString("name");
        int total_qty = resultSet.getInt("total_qty");
        series.getData().add(new XYChart.Data<>(name, total_qty));
    }

    barChart.getData().add(series);
    for (Node n : barChart.lookupAll(".default-color0.chart-bar")) {
        n.setStyle("-fx-bar-fill: #e6f26c;");
    }

}

}






