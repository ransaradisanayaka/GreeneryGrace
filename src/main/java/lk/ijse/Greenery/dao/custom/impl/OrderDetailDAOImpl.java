package lk.ijse.Greenery.dao.custom.impl;

import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.custom.OrderDetailDAO;
import lk.ijse.Greenery.entity.OrderDetail;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public   boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
     return SQLUtil.execute("INSERT INTO orderDetail  VALUES(?, ?)");
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }


    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }


    public boolean exist(String c) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }


    public String generateNewID() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public OrderDetail search(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }}
