package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupPaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.SupPayment;
import java.sql.SQLException;
import java.util.List;

public interface SupplierPaymentDAO extends CrudDAO<SupPayment> {
    public List<String> getAllpayId() throws SQLException ;

//    public String loadNExtSupID() throws SQLException ;
//
//    public boolean insertPaymentSupplier(SupPaymentDto supPaymentDto) throws SQLException ;

    public List<SupPaymentDto> getAllSup() throws SQLException ;

//    public boolean deleteSupPay(SupPaymentDto supPaymentDto) throws SQLException ;
//
//    public boolean updateSupPay(SupPaymentDto supPaymentDto) throws SQLException ;
}
