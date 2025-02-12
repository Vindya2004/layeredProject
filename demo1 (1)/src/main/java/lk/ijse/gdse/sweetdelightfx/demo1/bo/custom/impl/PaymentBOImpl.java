package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.PaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.PaymentBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.PaymentDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public boolean insert(PaymentDto customerDto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(PaymentDto customerDto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(PaymentDto customerDto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<PaymentDto> loadTbl() throws SQLException {
        return null;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return "";
    }

    @Override
    public String getPaymentId() throws SQLException {
        return paymentDAO.getPaymentId();
    }

    @Override
    public List<String> getAllpayId() throws SQLException {
        return List.of();
    }
}
