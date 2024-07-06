package lk.ijse.Greenery.bo.impl;

import lk.ijse.Greenery.bo.PaymentBO;
import lk.ijse.Greenery.dao.DAOFactory;
import lk.ijse.Greenery.dao.SQLUtil;
import lk.ijse.Greenery.dao.custom.PaymentDAO;
import lk.ijse.Greenery.dto.PaymentDTO;
import lk.ijse.Greenery.entity.Payment;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPaymentData = paymentDAO.getAll();

        ArrayList<PaymentDTO> allDTOData = new ArrayList<>();
        for (Payment p : allPaymentData) {
            allDTOData.add(new PaymentDTO(p.getPaymentId(), p.getPaymentMethod(), p.getAmount(), p.getDate(), p.getCustomerId()));
        }
        return allDTOData;

    }

    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?,?)", dto.getPaymentId(), dto.getPaymentMethod(), dto.getAmount(), dto.getDate(), dto.getCustomerId());


    }

    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET paymentId=?, paymentMethod=?, amount=?, date=?WHERE customerId=?", dto.getPaymentMethod(), dto.getAmount(), dto.getDate(), dto.getPaymentId());


    }



    public boolean deletePayment(String paymentId) throws SQLException, ClassNotFoundException {
return paymentDAO.delete(paymentId);
    }
}