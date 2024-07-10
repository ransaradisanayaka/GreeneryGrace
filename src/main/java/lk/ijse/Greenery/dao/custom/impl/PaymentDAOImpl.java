package lk.ijse.Greenery.dao.custom.impl;

import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.SuperDAO;
import lk.ijse.Greenery.dao.custom.PaymentDAO;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.dto.PaymentDTO;
import lk.ijse.Greenery.dto.ProductDTO;
import lk.ijse.Greenery.entity.Payment;
import lk.ijse.Greenery.model.PaymentDTo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements SuperDAO {
    public  ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<Payment> allPayment=new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM payment");

        while (resultSet.next()) {

            allPayment.add(new Payment(resultSet.getString("paymentId"),resultSet.getString("paymentMethod"),resultSet.getString("amount") ,resultSet.getString("date") ,resultSet.getString("customerId")));

        }
        return allPayment;


    }

    public  boolean save(Payment entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?,?)",entity.getPaymentId(),entity.getPaymentMethod(),entity.getAmount(),entity.getDate(),entity.getCustomerId());



    }

    public  boolean update(Payment entity) throws SQLException, ClassNotFoundException {
         return SQLUtil.execute("UPDATE payment SET paymentId=?, paymentMethod=?, amount=?, date=?WHERE customerId=?",entity.getPaymentMethod(),entity.getAmount(),entity.getDate(),entity.getPaymentId());


    }

    public  Payment search(String customerId) throws SQLException, ClassNotFoundException {

        ResultSet rst=SQLUtil.execute("SELECT * FROM payment WHERE customerId=?",customerId+"");
    rst.next();
    return new Payment(customerId+"",rst.getString("paymentId"),rst.getString("amount"),rst.getString("paymentMethod"),rst.getString("date"));
    }



    public  boolean delete(String paymentId) throws SQLException, ClassNotFoundException {


        return SQLUtil.execute( "DELETE FROM payment WHERE customerId=?",paymentId);


    }
}
