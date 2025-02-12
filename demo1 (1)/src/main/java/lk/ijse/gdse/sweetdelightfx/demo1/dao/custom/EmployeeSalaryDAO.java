package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.EmpSalary;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeSalaryDAO extends CrudDAO<EmpSalary> {

    public List<String> getAllEmpId() throws SQLException ;

}
