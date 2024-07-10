package lk.ijse.Greenery.bo.impl;

import lk.ijse.Greenery.bo.StockBO;
import lk.ijse.Greenery.bo.SuperBO;
import lk.ijse.Greenery.dao.DAOFactory;
import lk.ijse.Greenery.dao.custom.StockDAO;
import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.dto.StockDTO;
import lk.ijse.Greenery.entity.Stock;
import lk.ijse.Greenery.model.StockDTo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements SuperBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

    public ArrayList<StockDTO> getAllStock() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> allStockData = stockDAO.getAll();
        ArrayList<StockDTO> allDTOData = new ArrayList<>();
        for (Stock stock : allStockData) {
            allDTOData.add(new lk.ijse.Greenery.dto.StockDTO(stock.getStockId(), stock.getName(), stock.getQtyOnHand(), stock.getType()));
        }
        return allDTOData;

    }

    public List<String> getLowStockName() throws SQLException, ClassNotFoundException {
        String SQL = "select name from stock where qtyOnHand<=20";
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(SQL).executeQuery();
        List<String> lowStockName = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            lowStockName.add(name);

        }
        return lowStockName;
    }

    public boolean saveStock(StockDTO dto) throws SQLException, ClassNotFoundException {

        return stockDAO.save((new Stock(dto.getStockId(), dto.getName(), dto.getQtyOnHand(), dto.getType())));


    }

    public boolean updateStock(StockDTO dto) throws SQLException, ClassNotFoundException {


        return stockDAO.update(new Stock(dto.getStockId(), dto.getName(), dto.getQtyOnHand(), dto.getType()));


    }




    public boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException {

        return stockDAO.delete(stockId);

    }
}