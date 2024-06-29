package lk.ijse.Greenery.repository;


import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static Object customerId;

    public static List<String> getId() throws SQLException {
        String sql = "SELECT customerId FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            String customerId = resultSet.getString(1);
            String customerName = resultSet.getString(2);
            String customerAddress = resultSet.getString(3);
            String customerContact = resultSet.getString(4);

            Customer customer = new Customer(customerId, customerName, customerAddress, customerContact);
            customerList.add(customer);
        }
        return customerList;
    }

    public static boolean btnDeleteOnAction(String customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE customerId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, customerId);

        return pstm.executeUpdate() > 0;

    }


    public static Customer searchById(String customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customerId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, customerId);
        ResultSet resultSet = pstm.executeQuery();

        Customer customer = null;


        if (resultSet.next()) {
            String Id = resultSet.getString(1);
            String customerName = resultSet.getString(2);
            String customerAddress = resultSet.getString(3);
            String customerContact = resultSet.getString(4);


            customer = new Customer(customerId, customerName, customerAddress, customerContact);
        }
        return customer;
    }

    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, customer.getCustomerId());
        pstm.setObject(2, customer.getCustomerName());
        pstm.setObject(3, customer.getCustomerAddress());
        pstm.setObject(4, customer.getCustomerContact());


        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET customerName = ?, customerAddress = ?, customerContact = ? WHERE customerId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, customer.getCustomerName());
        pstm.setObject(2, customer.getCustomerAddress());
        pstm.setObject(3, customer.getCustomerContact());
        pstm.setObject(4, customer.getCustomerId());

        return pstm.executeUpdate() > 0;



    }


    public static boolean delete(String customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE customerId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, customerId);

        return pstm.executeUpdate() > 0;

    }

    public static List<String> getCustomerId() {
        return null;
    }

    public int countDailyCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(CustomerId) FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            int idd = Integer.parseInt(resultSet.getString(1));
            return idd;
        }
        return Integer.parseInt(null);
    }
    }


