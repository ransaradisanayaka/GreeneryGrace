package lk.ijse.Greenery.dao.custom.impl;


import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.SuperDAO;
import lk.ijse.Greenery.dao.custom.StockDAO;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.dto.StockDTO;
import lk.ijse.Greenery.entity.Stock;
import lk.ijse.Greenery.model.StockDTo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements SuperDAO {
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> allStock = new ArrayList<>();

        ResultSet rst =SQLUtil.execute("SELECT * FROM stock");

        while (rst.next()) {
         Stock stock=new Stock(rst.getString("stockId"),rst.getString("name"),rst.getString("qtyOnHand"),rst.getString("type") );
        allStock.add(stock);
        }
        return allStock;


    }

    public static List<String> getLowStockName() throws SQLException, ClassNotFoundException {
     /*   String SQL="select name from stock where qtyOnHand<=20";
        Connection connection= DbConnection.getDbConnection().getConnection();
        ResultSet resultSet=connection.prepareStatement(SQL).executeQuery();
        List<String>lowStockName =new ArrayList<>();
        while (resultSet.next()){
            String name=resultSet.getString(1);
            lowStockName.add(name);

        }
        return lowStockName;*/
        return SQLUtil.execute("select name from stock where qtyOnHand<=20");
    }

    public  boolean save(Stock entity) throws SQLException, ClassNotFoundException {

        return  SQLUtil.execute( "INSERT INTO stock VALUES(?, ?, ?, ?)",entity.getStockId(),entity.getName(),entity.getQtyOnHand(),entity.getType());




    }

    public  boolean update(Stock entity ) throws SQLException, ClassNotFoundException {


        return SQLUtil.execute("UPDATE stock SET name=?,qtyOnHand=? type=? WHERE stockId=?",entity.getName(),entity.getQtyOnHand(),entity.getType(),entity.getStockId());



    }

    public Stock search(String stockId) throws SQLException, ClassNotFoundException {

        ResultSet rst=SQLUtil.execute( "SELECT * FROM stock WHERE stockId=?",stockId+"");
   rst.next();
    return new Stock(stockId+"",rst.getString("name"),rst.getString("qtyOnHand"),rst.getString("type"));
    }



    public  boolean delete(String stockId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM stock WHERE stockId=?",stockId);

    }


}
