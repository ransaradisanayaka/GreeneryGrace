package lk.ijse.Greenery.repository;

import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.OrderDetailDTo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailRepo{
    public static boolean save(List<OrderDetailDTo> odList) throws SQLException {
        for (OrderDetailDTo od : odList) {
            if(!save(od)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(OrderDetailDTo od) throws SQLException {
       String sql = "INSERT INTO orderDetail  VALUES(?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
     //  pstm.setString(1, od.getOrderId());
      //  pstm.setString(2, od.getProductId());


        return pstm.executeUpdate() > 0;
    }
}
