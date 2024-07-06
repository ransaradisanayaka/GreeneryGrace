package lk.ijse.Greenery.dao.custom.impl;

import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.custom.OrderDAO;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.entity.Order;
import lk.ijse.Greenery.model.OrdersDTo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    public ArrayList<Order> getAll() throws SQLException, UnsupportedLookAndFeelException {
       /* String sql = "SELECT * FROM orders";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<OrdersDTo> orderList = new ArrayList<>();
        while (resultSet.next()) {
            String orderId = resultSet.getString(1);
            String orderQty = resultSet.getString(2);
            String amount = resultSet.getString(3);
            String description= resultSet.getString(4);
            String customerId= resultSet.getString(5);


            OrdersDTo orders = new OrdersDTo(orderId, orderQty, amount, description,customerId);
            orderList.add(orders);
        }
        return orderList;*/
        throw new UnsupportedLookAndFeelException("This Feature is not implemented yet");
    }




    public static OrdersDTo search (String orderId) throws SQLException, UnsupportedLookAndFeelException {
       /* String sql = "SELECT * FROM orders WHERE orderId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, orderId);
        ResultSet resultSet = pstm.executeQuery();

        OrdersDTo orders = null;


        if (resultSet.next()) {
            String Id = resultSet.getString(1);
            String orderQty = resultSet.getString(2);
            String amount = resultSet.getString(3);
            String description = resultSet.getString(4);
            String customerId = resultSet.getString(5);



            orders= new OrdersDTo(Id, orderQty,amount,description,customerId );
        }
        return orders;*/
        throw new UnsupportedLookAndFeelException("This Feature is not implemented yet");
    }

    public  boolean save(Order entity) throws SQLException, ClassNotFoundException {


        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?, ?,?)",entity.getOrderId(),entity.getOrderQty(),entity.getAmount(),entity.getDescription(),entity.getCustomerId());

    }

    public  boolean update(Order entity) throws SQLException, ClassNotFoundException, UnsupportedLookAndFeelException {
        throw new UnsupportedLookAndFeelException("This Feature is not implemented yet");
    }
    public static boolean saveOrder(OrdersDTo orders) throws SQLException {
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
