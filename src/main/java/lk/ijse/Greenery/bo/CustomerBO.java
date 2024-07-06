package lk.ijse.Greenery.bo;

import lk.ijse.Greenery.dto.CustomerDTO;
import lk.ijse.Greenery.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {


    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;



    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;
}