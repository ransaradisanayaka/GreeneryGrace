package lk.ijse.Greenery.dao.custom.impl;

import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.SupplierDTo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl {
    public ArrayList<SupplierDTo> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTo> allSupplier = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
            allSupplier.add(new SupplierDTo(rst.getString("supplierId"), rst.getString("supplierName"), rst.getString("supplierContact"), rst.getString("supplierAddress"), rst.getString("description"), rst.getString("supplierNIC")));
        }
        return allSupplier;
    }

    public static boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("DELETE FROM supplier WHERE supplierId=?",supplierId);

    }

    public static SupplierDTo searchById(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplierId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplierId);
        ResultSet resultSet = pstm.executeQuery();

        SupplierDTo supplier = null;


        if (resultSet.next()) {
            String Id = resultSet.getString(1);
            String supplierName = resultSet.getString(2);
            String supplierContact = resultSet.getString(3);
            String supplierAddress = resultSet.getString(4);
            String supplierNIC = resultSet.getString(5);
            String description = resultSet.getString(6);


            supplier = new SupplierDTo(Id, supplierName, supplierContact, supplierAddress, supplierNIC, description);
        }
        return supplier;
    }

    public  boolean save(SupplierDTo entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?)",entity.getSupplierId(),entity.getSupplierName(),entity.getSupplerContact(),entity.getSupplierAddress(),entity.getDescription(),entity.getSupplierNIC());

    }

    public boolean update(SupplierDTo entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE supplier SET supplierName=?,supplierContact,supplierAddress=?, supplierNIC=?, description=? WHERE supplierId=?", entity.getSupplierName(), entity.getSupplerContact(), entity.getSupplierAddress(), entity.getSupplierNIC(), entity.getDescription(), entity.getSupplierId());
    }
}