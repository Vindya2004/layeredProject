package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Delivery;
import java.sql.SQLException;

public interface DeliveryDAO extends CrudDAO<Delivery> {
//    public boolean insertDelivery(DeliveryDto deliveryDto) throws SQLException ;
//
//    public boolean updateDelivery(DeliveryDto deliveryDto) throws SQLException ;
//
//    public boolean deleteDelivery(DeliveryDto deliveryDto) throws SQLException ;
//
//    public ArrayList<DeliveryDto> loadTableDel() throws SQLException ;
//
//    public String loadNextId() throws SQLException ;

    public String getNewDelId() throws SQLException ;

    boolean insertDelivery(String deliveryId, String deleveryDate, String destination, double txtDelCharge)throws SQLException;
}
