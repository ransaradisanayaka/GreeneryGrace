package lk.ijse.Greenery.bo;

import lk.ijse.Greenery.dto.CustomerDTO;
import lk.ijse.Greenery.dto.OrdersDTO;
import lk.ijse.Greenery.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO{
    public CustomerDTO searchCustomer(String customerId ) throws SQLException, ClassNotFoundException ;

    public ProductDTO searchIProduct(String productId) throws SQLException, ClassNotFoundException ;

    public boolean existProduct(String productId) throws SQLException, ClassNotFoundException;

    public boolean existCustomer(String customerId) throws SQLException, ClassNotFoundException ;

    public String generateOrderID() throws SQLException, ClassNotFoundException ;

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;

    public boolean placeOrder(OrdersDTO dto)throws SQLException, ClassNotFoundException;

    public ProductDTO findProduct(String productId)throws SQLException, ClassNotFoundException;

}
