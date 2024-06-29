package lk.ijse.Greenery.repository;


import lk.ijse.Greenery.db.DbConnection;

import lk.ijse.Greenery.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);


        ResultSet resultSet = pstm.executeQuery();

        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String paymentMethod = resultSet.getString(2);
            String amount = resultSet.getString(3);
            String date = resultSet.getString(4);
            String customerId = resultSet.getString(5);


            Payment  payment = new Payment(paymentId,paymentMethod, amount, date,customerId);
            paymentList.add(payment);
        }
        return paymentList;


    }

    public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?,?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  payment.getPaymentId());
        pstm.setObject(2,  payment.getPaymentMethod());
        pstm.setObject(3,  payment.getAmount());
        pstm.setObject(4,  payment.getDate());
        pstm.setObject(5,  payment.getCustomerId());

        return pstm.executeUpdate() > 0;



    }

    public static boolean update(Payment payment) throws SQLException {
        String sql = "UPDATE payment SET paymentId=?, paymentMethod=?, amount=?, date=?WHERE customerId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);


        pstm.setObject(1,  payment.getPaymentMethod());
        pstm.setObject(2,  payment.getAmount());
        pstm.setObject(3,  payment.getDate());
        pstm.setObject(4,  payment.getCustomerId());
        pstm.setObject(5,  payment.getPaymentId());


        return pstm.executeUpdate() > 0;


    }

    public static Payment searchById(String customerId) throws SQLException {
        String sql = "SELECT * FROM payment WHERE customerId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,customerId);
        ResultSet resultSet = pstm.executeQuery();

        Payment payment = null;




        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String amount= resultSet.getString(2);
            String  paymentMethod= resultSet.getString(3);
            String date = resultSet.getString(4);



            payment = new Payment( paymentId,  paymentMethod,amount, date, customerId);
        }
        return payment;
    }



    public static boolean delete(String paymentId) throws SQLException {
        String sql = "DELETE FROM payment WHERE customerId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, paymentId);

        return pstm.executeUpdate() > 0;

    }
}
