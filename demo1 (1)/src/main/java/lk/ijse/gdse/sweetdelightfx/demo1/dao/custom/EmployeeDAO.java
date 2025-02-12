package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;
import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {
    // boolean insertEmployee(EmployeeDto employeeDto) throws SQLException ;

    // boolean updateEmployee(EmployeeDto employeeDto) throws SQLException ;

   //  boolean deleteEmployee(EmployeeDto employeeDto) throws SQLException ;

    // ArrayList<EmployeeDto> loadTblEmp() throws SQLException ;


     Boolean checkId(EmployeeDto dto) throws SQLException ;

    // String loadNextId() throws SQLException ;
}
