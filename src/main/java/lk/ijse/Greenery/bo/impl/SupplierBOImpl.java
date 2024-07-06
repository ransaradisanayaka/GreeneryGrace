package lk.ijse.Greenery.bo.impl;

import lk.ijse.Greenery.bo.SupplierBO;
import lk.ijse.Greenery.dao.DAOFactory;
import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.custom.SupplierDAO;
import lk.ijse.Greenery.dto.SupplierDTO;
import lk.ijse.Greenery.entity.Supplier;
import lk.ijse.Greenery.model.SupplierDTo;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
  SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSupData = supplierDAO.getAll();
        ArrayList<SupplierDTO> allSupplier = new ArrayList<>();
        for (Supplier s:allSupData) {
            allSupplier.add(new SupplierDTO(s.getSupplierId(), s.getSupplierName(), s.getSupplierContact(), s.getSupplierAddress(), s.getSupplierNIC(), s.getDescription()));
        }
        return allSupplier;
    }

    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE supplierId=?",supplierId);

    }



    public  boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?)",dto.getSupplierId(),dto.getSupplierName(),dto.getSupplerContact(),dto.getSupplierAddress(),dto.getSupplierNIC(),dto.getDescription());

    }

    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE supplier SET supplierName=?,supplierContact,supplierAddress=?, supplierNIC=?, description=? WHERE supplierId=?", dto.getSupplierName(), dto.getSupplerContact(), dto.getSupplierAddress(), dto.getSupplierNIC(), dto.getDescription(), dto.getSupplierId());
    }
}
