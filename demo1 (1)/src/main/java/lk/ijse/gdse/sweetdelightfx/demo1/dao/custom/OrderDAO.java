package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Order;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    boolean orderSave(String orderId, String orderDate, String customerId)throws SQLException;
    // public String loadNextId() throws SQLException ;
}
