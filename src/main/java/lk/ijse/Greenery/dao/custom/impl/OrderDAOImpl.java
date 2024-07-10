package lk.ijse.Greenery.dao.custom.impl;

import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.SuperDAO;
import lk.ijse.Greenery.dao.custom.OrderDAO;
import lk.ijse.Greenery.entity.Order;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDAOImpl implements SuperDAO {
    public ArrayList<Order> getAll() throws SQLException,ClassNotFoundException  {

        try {
            throw new UnsupportedLookAndFeelException("This Feature is not implemented yet");
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This Feature is not implemented yet");
    }

    public Order search(String orderId) throws SQLException, ClassNotFoundException, UnsupportedLookAndFeelException {
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

    public static String currentId() throws SQLException, ClassNotFoundException {
      ResultSet rst =SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId desc LIMIT 1");

            return rst.next()? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";

    }
    public int countDailyOrder() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(orderId) FROM orders");
        if (resultSet.next()) {
            int idd = Integer.parseInt(resultSet.getString(1));
            return idd;
        }
        return Integer.parseInt(null);
    }
}
