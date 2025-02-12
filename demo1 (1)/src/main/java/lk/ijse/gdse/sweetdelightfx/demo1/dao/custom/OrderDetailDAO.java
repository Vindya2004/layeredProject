package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
    boolean saveOrderDetail(String orderId, String productId, int quantity, double price)throws SQLException;
}
