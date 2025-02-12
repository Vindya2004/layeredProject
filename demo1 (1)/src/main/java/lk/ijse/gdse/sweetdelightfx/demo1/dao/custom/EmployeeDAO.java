package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;
import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {

     Boolean checkId(EmployeeDto dto) throws SQLException ;

}
