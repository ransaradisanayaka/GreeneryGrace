package lk.ijse.Greenery.dao.custom;

import lk.ijse.Greenery.dao.SuperDAO;
import lk.ijse.Greenery.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> searchOrder(String oid) throws SQLException,ClassCastException;
}
