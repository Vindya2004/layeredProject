package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO <Customer>{

    public List<String> getAllCustId() throws SQLException ;
}
