package lk.ijse.Greenery.dao.custom.impl;

import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.SuperDAO;
import lk.ijse.Greenery.dao.custom.ProductDAO;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.dto.PaymentDTO;
import lk.ijse.Greenery.entity.Product;
import lk.ijse.Greenery.model.ProductDTo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements SuperDAO {
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<Product> allProduct=new ArrayList<>();
       ResultSet rst= SQLUtil.execute( "SELECT productId FROM product");

        while(rst.next()) {
            allProduct.add(new Product(rst.getString("productId"),rst.getString("productName"),rst.getString("qty"),rst.getString("description"),rst.getString("unitPrice")));
        }
        return allProduct;
    }

    public static List<String> getProductId() throws SQLException {
        String sql = "SELECT productId FROM product";

        PreparedStatement pstm = null;
        pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> codeList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while(resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }
    public  boolean save(Product entity) throws SQLException, ClassNotFoundException {
          return SQLUtil.execute("INSERT INTO product VALUES(?, ?, ?, ?,?)",entity.getProductId(),entity.getProductName(),entity.getQty(),entity.getDescription(),entity.getUnitPrice());



    }

    public static boolean update( Product entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE product SET productName=?,qty=? , description=? unitprice=? WHERE productId=?",entity.getProductName(),entity.getQty(),entity.getDescription(),entity.getUnitPrice(),entity.getProductId());



    }

    public Product searchById(String productId) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM product WHERE productId=?",productId+"");


        return new Product(productId + "", rst.getString("productName"), rst.getString("qty"),rst.getString("description"),rst.getString("unitPrice"));
    }



    public  boolean delete(String productId) throws SQLException, ClassNotFoundException {
          return SQLUtil.execute("DELETE FROM product WHERE productId=?",productId);

    }
}
