package lk.ijse.Greenery.dao.custom.impl;


import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.SuperDAO;
import lk.ijse.Greenery.entity.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements SuperDAO {
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        while (rst.next()) {

            Customer customer = new Customer(rst.getString("customerId"), rst.getString("customerName"), rst.getString("customerAddress"), rst.getString("customerContact"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }
/*
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
*/
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer (customerId,customerName, customerAddress,customerContact) VALUES (?,?,?,?)", entity.getCustomerId(), entity.getCustomerName(), entity.getCustomerAddress(), entity.getCustomerContact());

    }

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE customer SET customerName = ?, customerAddress = ?, customerContact = ? WHERE customerId = ?", entity.getCustomerName(), entity.getCustomerAddress(), entity.getCustomerContact(), entity.getCustomerId());
    }

    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE customerId=?", customerId);

    }
    public  Customer search(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE customerId=?",customerId);

        rst.next();

        return new Customer();

    }


    public boolean exist(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT customerId FROM customer WHERE customerId=?", customerId);
        return rst.next();
    }


    public static List<String> getCustomerId() {
        return null;
    }

    public int countDailyCustomer() throws SQLException, ClassNotFoundException {

        ResultSet rst= SQLUtil.execute("SELECT COUNT(CustomerId) FROM customer");

        if (rst.next()) {
            int idd = Integer.parseInt(rst.getString(1));
            return idd;
        }
        return Integer.parseInt(null);
    }

}