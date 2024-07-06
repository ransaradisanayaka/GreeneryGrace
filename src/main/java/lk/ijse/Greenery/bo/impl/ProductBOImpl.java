package lk.ijse.Greenery.bo.impl;

import lk.ijse.Greenery.bo.ProductBO;
import lk.ijse.Greenery.dao.DAOFactory;
import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.custom.ProductDAO;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.dto.ProductDTO;
import lk.ijse.Greenery.entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> allProductData = productDAO.getAll();
        ArrayList<ProductDTO> allProduct = new ArrayList<>();ResultSet rst = SQLUtil.execute("SELECT productId FROM product");

        for (Product product:allProductData) {
            allProduct.add(new ProductDTO(product.getProductId(), product.getProductName(), product.getQty(), product.getDescription(),product.getUnitPrice()));
        }return allProduct;
    }

    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT productId FROM product";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> codeList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    @Override
    public boolean saveProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO product VALUES(?, ?, ?, ?,?)", dto.getProductId(), dto.getProductName(), dto.getQty(), dto.getDescription(), dto.getUnitPrice());


    }

    public  boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE product SET productName=?,qty=? , description=? unitprice=? WHERE productId=?", dto.getProductName(), dto.getQty(), dto.getDescription(), dto.getUnitPrice(), dto.getProductId());


    }




    public boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM product WHERE productId=?", productId);
    }
}