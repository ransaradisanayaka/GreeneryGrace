package lk.ijse.Greenery.bo;

import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dto.PaymentDTO;
import lk.ijse.Greenery.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO{
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;

    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;


    public boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException;
}
