package lk.ijse.Greenery.bo;

import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.dto.ProductDTO;
import lk.ijse.Greenery.entity.Product;
import lk.ijse.Greenery.model.ProductDTo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductBO extends SuperBO {
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
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

    public boolean saveProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;



    public boolean deleteProduct(String productId) throws SQLException, ClassNotFoundException;
    }