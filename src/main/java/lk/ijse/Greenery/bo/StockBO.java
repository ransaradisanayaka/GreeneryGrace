package lk.ijse.Greenery.bo;

import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.dto.StockDTO;
import lk.ijse.Greenery.entity.Stock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StockBO extends SuperBO{
    public ArrayList<StockDTO> getAllStock() throws SQLException, ClassNotFoundException;

    public List<String> getLowStockName() throws SQLException;
    public boolean saveStock(StockDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateStock(StockDTO dto) throws SQLException, ClassNotFoundException;




    public boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException;
}