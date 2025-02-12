package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.DeliveryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.OrderDetailDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.OrderDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.PaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean insert(OrderDto customerDto) throws SQLException;


    boolean update(OrderDto customerDto) throws SQLException;

    boolean delete(OrderDto customerDto) throws SQLException ;

    ArrayList<OrderDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;

    String insertOrders(OrderDto orderDto, OrderDetailDto oDto, DeliveryDto deliveryDto, PaymentDto paymentDto) throws SQLException;
}
