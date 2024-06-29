
package lk.ijse.Greenery.repository;

import lk.ijse.Greenery.db.DbConnection;
import lk.ijse.Greenery.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    public static boolean setUser(User user) throws SQLException {
        String sql = "INSERT INTO user VALUES(?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, user.getUserId());
        pstm.setObject(2, user.getUserName());
        pstm.setObject(3, user.getPassWord());
        return pstm.executeUpdate() > 0;

    }

    public static User setLoginOnDetail(String userName) throws SQLException {
        String sql = "SELECT userId,userName,passWord FROM user WHERE userName = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getString("userId"));
            user.setUserName(resultSet.getString("userName"));
            user.setPassWord(resultSet.getString("passWord"));
            return user;
        } else {
            return null;
        }
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT userId FROM user";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }




}