package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Delivery;
import java.sql.SQLException;

public interface DeliveryDAO extends CrudDAO<Delivery> {

    public String getNewDelId() throws SQLException ;

    boolean insertDelivery(String deliveryId, String deleveryDate, String destination, double txtDelCharge)throws SQLException;
}
