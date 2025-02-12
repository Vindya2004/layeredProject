package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Payment;
import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    public String getPaymentId() throws SQLException ;

    public List<String> getAllpayId() throws SQLException ;

    boolean savePay(String paymentId, String paymentMethod, String paymentDate, double cost, String ordId)throws SQLException ;
}
