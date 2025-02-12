package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupPaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.SupPayment;
import java.sql.SQLException;
import java.util.List;

public interface SupplierPaymentDAO extends CrudDAO<SupPayment> {
     List<String> getAllpayId() throws SQLException ;


     List<SupPaymentDto> getAllSup() throws SQLException ;


}
