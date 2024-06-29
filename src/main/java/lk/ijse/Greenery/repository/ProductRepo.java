package lk.ijse.Greenery.repository;

import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



    public class ProductRepo {
        public static List<String> getCodes() throws SQLException {
            String sql = "SELECT productId FROM product";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            List<String> codeList = new ArrayList<>();

            ResultSet resultSet = pstm.executeQuery();

            while(resultSet.next()) {
                codeList.add(resultSet.getString(1));
            }
            return codeList;
        }
        public static List<Product> getAll() throws SQLException {
            String sql = "SELECT * FROM product";

            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);


            ResultSet resultSet = pstm.executeQuery();

            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                String productId = resultSet.getString(1);
                String productName = resultSet.getString(2);
                String qty = resultSet.getString(3);
                String description = resultSet.getString(4);
                String unitPrice = resultSet.getString(5);

                Product product = new Product(productId, productName, qty, description,unitPrice);
                productList.add(product);
            }
            return productList;


        }


        public static boolean save(Product product) throws SQLException {
            String sql = "INSERT INTO product VALUES(?, ?, ?, ?,?)";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            pstm.setObject(1,  product.getProductId());
            pstm.setObject(2,  product.getProductName());
            pstm.setObject(3,  product.getQty());
            pstm.setObject(4,  product.getDescription());
            pstm.setObject(5,  product.getUnitPrice());

            return pstm.executeUpdate() > 0;



        }

        public static boolean update( Product product ) throws SQLException {
            String sql = "UPDATE product SET productName=?,qty=? , description=? unitprice=? WHERE productId=?";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            pstm.setObject(1,  product.getProductName());
            pstm.setObject(2,  product.getQty());
            pstm.setObject(3,  product.getDescription());
            pstm.setObject(4,  product.getUnitPrice());
            pstm.setObject(5, product.getProductId());



            return pstm.executeUpdate() > 0;


        }

        public static Product searchById(String productId) throws SQLException {
            String sql = "SELECT * FROM product WHERE productId=?";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            pstm.setObject(1,productId);
            ResultSet resultSet = pstm.executeQuery();

            Product product = null;




            if (resultSet.next()) {
               String Id= resultSet.getString(1);
                String productName= resultSet.getString(2);
                String  qty= resultSet.getString(3);
                String description= resultSet.getString(4);
                String unitPrice = resultSet.getString(5);


                product= new Product( Id, productName,qty,description,unitPrice);
            }
            return product;
        }



        public static boolean delete(String productId) throws SQLException {
            String sql = "DELETE FROM product WHERE productId=?";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setObject(1, productId);

            return pstm.executeUpdate() > 0;

        }
    }



