package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.PaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean insert(PaymentDto customerDto) throws SQLException;


    boolean update(PaymentDto customerDto) throws SQLException;

    boolean delete(PaymentDto customerDto) throws SQLException ;

    ArrayList<PaymentDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;

    public String getPaymentId() throws SQLException ;

    public List<String> getAllpayId() throws SQLException ;
}
