package lk.ijse.Greenery.repository;

import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Supplier>  supplierList = new ArrayList<>();
        while (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String supplierName = resultSet.getString(2);
            String supplierContact = resultSet.getString(3);
            String  supplierAddress= resultSet.getString(4);
            String supplierNIC = resultSet.getString(5);
            String description= resultSet.getString(6);



            Supplier supplier = new Supplier(supplierId, supplierName, supplierContact, supplierAddress, supplierNIC,description);
            supplierList.add(supplier);
        }
        return supplierList;
    }            public static boolean delete(String supplierId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplierId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplierId);

        return pstm.executeUpdate() > 0;

    }

    public static Supplier searchById (String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplierId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplierId);
        ResultSet resultSet = pstm.executeQuery();

        Supplier supplier = null;


        if (resultSet.next()) {
            String Id = resultSet.getString(1);
            String supplierName = resultSet.getString(2);
            String supplierContact= resultSet.getString(3);
            String  supplierAddress = resultSet.getString(4);
            String supplierNIC = resultSet.getString(5);
            String description = resultSet.getString(6);



            supplier = new Supplier (Id, supplierName,  supplierContact, supplierAddress,supplierNIC,description);
        }
        return  supplier;
    }

    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getSupplierName());
        pstm.setObject(3, supplier.getSupplerContact());
        pstm.setObject(4, supplier.getSupplierAddress());
        pstm.setObject(5, supplier.getSupplierNIC());
        pstm.setObject(6, supplier.getDescription());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supplierName=?,supplierContact,supplierAddress=?, supplierNIC=?, description=? WHERE supplierId=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierName());
        pstm.setObject(2, supplier.getSupplerContact());
        pstm.setObject(3, supplier.getSupplierAddress());
        pstm.setObject(4, supplier.getSupplierNIC());
        pstm.setObject(5, supplier.getDescription());
        pstm.setObject(6, supplier.getSupplierId());

        return pstm.executeUpdate() > 0;
    }
}
