package lk.ijse.Greenery.bo.impl;

import lk.ijse.Greenery.bo.PlaceOrderBO;
import lk.ijse.Greenery.dao.DAOFactory;
import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.custom.CustomerDAO;
import lk.ijse.Greenery.dao.custom.OrderDAO;
import lk.ijse.Greenery.dao.custom.OrderDetailDAO;
import lk.ijse.Greenery.dao.custom.ProductDAO;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.dto.CustomerDTO;
import lk.ijse.Greenery.dto.OrderDetailDTO;
import lk.ijse.Greenery.dto.OrdersDTO;
import lk.ijse.Greenery.dto.ProductDTO;
import lk.ijse.Greenery.entity.Customer;
import lk.ijse.Greenery.entity.Order;
import lk.ijse.Greenery.entity.OrderDetail;
import lk.ijse.Greenery.entity.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
     ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public CustomerDTO searchCustomer(String customerId) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(customerId);
        return new CustomerDTO(c.getCustomerId(),c.getCustomerName(),c.getCustomerAddress(),c.getCustomerContact());
    }



    public ProductDTO searchIProduct(String productId) throws SQLException, ClassNotFoundException {
        Product p = productDAO.search(productId);
        return new ProductDTO(p.getProductId(),p.getProductName(),p.getQty(),p.getDescription(),p.getUnitPrice());
    }

    @Override
    public boolean existProduct(String productId) throws SQLException, ClassNotFoundException {
        return productDAO.exist(productId);
    }

    @Override
    public boolean existCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(customerId);
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerEntityData = customerDAO.getAll();
        ArrayList<CustomerDTO> convertToDto= new ArrayList<>();
        for (Customer c : customerEntityData) {
            convertToDto.add(new CustomerDTO(c.getCustomerId(),c.getCustomerName(),c.getCustomerAddress(),c.getCustomerContact()));
        }
        return convertToDto;
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> entityTypeData = productDAO.getAll();
        ArrayList<ProductDTO> dtoTypeData= new ArrayList<>();
        for (Product p : entityTypeData) {
            dtoTypeData.add(new ProductDTO(p.getProductId(),p.getProductName(),p.getQty(),p.getDescription(),p.getUnitPrice()));
        }
        return dtoTypeData;
    }


    @Override
    public boolean placeOrder(OrdersDTO dto)throws SQLException, ClassNotFoundException{
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            boolean b1 = orderDAO.exist(dto.getOrderId());
            /*if order id already exist*/
            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);
            //Save the Order to the order table
            boolean b2 = orderDAO.save(new Order(dto.getOrderId(),dto.getOrderDate(), dto.getCustomerId(), dto.getCustomerName(),dto.getOrderTotal()));
            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO d : dto.getOrderDetails()) {
                OrderDetail orderDetail = new OrderDetail(d.getProductId(),d.getQty(),d.getOrderId(),d.getUnitPrice());
                boolean b3 = orderDetailDAO.save(orderDetail);
                if (!b3) {
                    connection.rollback();

                    return false;
                }
                //Search & Update Item
                ProductDTO product = findProduct(d.getProductId());
                product.setQty(product.getQty() - d.getQty());

                //update item
                boolean b = productDAO.update(new Product(product.getProductId(),product.getProductName(),product.getQty(),product.getDescription(),product.getUnitPrice()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public ProductDTO findProduct(String code)throws SQLException, ClassNotFoundException {
        try {
            Product p = productDAO.search(code);
            return new ProductDTO(p.getProductId(),p.getProductName(),p.getQty(),p.getDescription(),p.getUnitPrice());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
