package lk.ijse.Greenery.bo;

import lk.ijse.Greenery.dto.SupplierDTO;
import lk.ijse.Greenery.model.SupplierDTo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException;


    public  boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;
}
