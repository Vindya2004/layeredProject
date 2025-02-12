package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupPaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.SupPayment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierPaymentBO extends SuperBO {
    boolean insert(SupPaymentDto customerDto) throws SQLException;


    boolean update(SupPaymentDto customerDto) throws SQLException;

    boolean delete(SupPayment customerDto) throws SQLException ;

    ArrayList<SupPaymentDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;

    public List<SupPaymentDto> getAllSup() throws SQLException ;
}
