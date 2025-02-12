package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO <Customer>{
    // boolean insertCustomer(CustomerDto customerDto) throws SQLException;

   //  boolean updateCustomer(CustomerDto customerDto) throws SQLException;

   //  boolean deleteCustomer(CustomerDto customerDto) throws SQLException ;

  //   ArrayList<CustomerDto> loadTblcustomer() throws SQLException ;

     // String loadNExtID() throws SQLException ;

    public List<String> getAllCustId() throws SQLException ;
}
