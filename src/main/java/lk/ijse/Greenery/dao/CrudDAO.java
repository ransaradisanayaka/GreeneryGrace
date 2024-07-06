package lk.ijse.Greenery.dao;

import lk.ijse.Greenery.model.StockDTo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(T  entity) throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;

    public boolean delete(String customerId) throws SQLException, ClassNotFoundException;
    public  T search(String customerId) throws SQLException, ClassNotFoundException;
}
