package lk.ijse.Greenery.repository;

import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockRepo {
    public static List<Stock> getAll() throws SQLException {
        String sql = "SELECT * FROM stock";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);


        ResultSet resultSet = pstm.executeQuery();

        List<Stock> stockList = new ArrayList<>();
        while (resultSet.next()) {
            String stockId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String qtyOnHand = resultSet.getString(3);
            String type = resultSet.getString(4);


            Stock stock = new Stock(stockId, name, qtyOnHand, type);
            stockList.add(stock);
        }
        return stockList;


    }

   public static List<String> getLowStockName() throws  SQLException{
        String SQL="select name from stock where qtyOnHand<=20";
        Connection connection= DbConnection.getInstance().getConnection();
        ResultSet resultSet=connection.prepareStatement(SQL).executeQuery();
        List<String>lowStockName =new ArrayList<>();
        while (resultSet.next()){
            String name=resultSet.getString(1);
            lowStockName.add(name);

        }
        return lowStockName;
    }

    public static boolean save(Stock stock) throws SQLException {
        String sql = "INSERT INTO stock VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  stock.getStockId());
        pstm.setObject(2,  stock.getName());
        pstm.setObject(3,  stock.getQtyOnHand());
        pstm.setObject(4,  stock.getType());


        return pstm.executeUpdate() > 0;



    }

    public static boolean update(Stock stock ) throws SQLException {
        String sql = "UPDATE stock SET name=?,qtyOnHand=? type=? WHERE stockId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,  stock.getName());
        pstm.setObject(2,  stock.getQtyOnHand());
        pstm.setObject(3,  stock.getType());
        pstm.setObject(4, stock.getStockId());



        return pstm.executeUpdate() > 0;


    }

    public Object searchById(String stockId) throws SQLException {
        String sql = "SELECT * FROM stock WHERE stockId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1,stockId);
        ResultSet resultSet = pstm.executeQuery();

        Stock stock = null;




        if (resultSet.next()) {
            stockId = resultSet.getString(1);
            String name= resultSet.getString(2);
            String  qtyOnHand= resultSet.getString(3);
            String type= resultSet.getString(4);



           stock = new Stock( stockId, name,qtyOnHand,type);
        }
        return stock;
    }



    public static boolean delete(String stockId) throws SQLException {
        String sql = "DELETE FROM stock WHERE stockId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, stockId);

        return pstm.executeUpdate() > 0;

    }
}

