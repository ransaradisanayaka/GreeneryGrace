package lk.ijse.Greenery.bo.impl;

import lk.ijse.Greenery.bo.CustomerBO;
import lk.ijse.Greenery.dao.DAOFactory;
import lk.ijse.Greenery.dao.custom.CustomerDAO;
import lk.ijse.Greenery.dto.CustomerDTO;
import lk.ijse.Greenery.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl  implements CustomerBO{
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
 public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customer> all =customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCustomerId(),c.getCustomerName(),c.getCustomerAddress(),c.getCustomerContact()));
        }
        return allCustomers;
    }



    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCustomerId(), dto.getCustomerName(), dto.getCustomerAddress(),dto.getCustomerContact()));
    }

   public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

return customerDAO.update(new Customer(dto.getCustomerId(), dto.getCustomerName(), dto.getCustomerAddress(), dto.getCustomerContact()));    }

   public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
return customerDAO.delete(customerId);
    }
    public boolean exitCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(customerId);
    }

}