package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.DeliveryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.PaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeliveryBO extends SuperBO {
    boolean insert(DeliveryDto customerDto) throws SQLException;


    boolean update(DeliveryDto customerDto) throws SQLException;

    boolean delete(Delivery customerDto) throws SQLException ;

    ArrayList<DeliveryDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;

    public String getNewDelId() throws SQLException ;
}
