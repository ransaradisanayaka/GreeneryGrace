package lk.ijse.Greenery.repository;

import lk.ijse.Greenery.db.DbConnection;

import lk.ijse.Greenery.model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepo {
    public static Object orderId;


    public static List<Orders> getAll() throws SQLException {
        String sql = "SELECT * FROM orders";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Orders> orderList = new ArrayList<>();
        while (resultSet.next()) {
            String orderId = resultSet.getString(1);
            String orderQty = resultSet.getString(2);
            String amount = resultSet.getString(3);
            String description= resultSet.getString(4);
            String customerId= resultSet.getString(5);


            Orders orders = new Orders(orderId, orderQty, amount, description,customerId);
            orderList.add(orders);
        }
        return orderList;
    }
    public static boolean delete(String orderId) throws SQLException {
        String sql = "DELETE FROM orders WHERE orderId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, orderId);

        return pstm.executeUpdate() > 0;

    }





    public static Orders searchById (String orderId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE orderId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, orderId);
        ResultSet resultSet = pstm.executeQuery();

        Orders orders = null;


        if (resultSet.next()) {
            String Id = resultSet.getString(1);
            String orderQty = resultSet.getString(2);
            String amount = resultSet.getString(3);
            String description = resultSet.getString(4);
            String customerId = resultSet.getString(5);



           orders= new Orders(Id, orderQty,amount,description,customerId );
        }
        return orders;
    }

    public static boolean save(Orders orders) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, orders.getOrderId());
        pstm.setObject(2, orders.getOrderQty());
        pstm.setObject(3, orders.getAmount());
        pstm.setObject(4,orders.getDescription());
        pstm.setObject(5, orders.getCustomerId());



        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Orders orders) throws SQLException {
        String sql = "UPDATE orders SET orderQty=?, amount=? ,description=?,customerId=? WHERE orderId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);


        pstm.setObject(1, orders.getOrderQty());
        pstm.setObject(2, orders.getAmount());
        pstm.setObject(3, orders.getDescription());
        pstm.setObject(4, orders.getCustomerId());
        pstm.setObject(5, orders.getOrderId());



        return pstm.executeUpdate() > 0;
    }
    public static boolean saveOrder(Orders orders) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, orders.getOrderId());
        pstm.setString(2, orders.getOrderQty());
        pstm.setString(3, orders.getAmount());
        pstm.setString(4, orders.getDescription());
        pstm.setString(5, orders.getCustomerId());

        return pstm.executeUpdate() > 0;
    }

    public static String currentId() throws SQLException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId desc LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
    public int countDailyOrder() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(orderId) FROM orders";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            int idd = Integer.parseInt(resultSet.getString(1));
            return idd;
        }
        return Integer.parseInt(null);
    }
}
