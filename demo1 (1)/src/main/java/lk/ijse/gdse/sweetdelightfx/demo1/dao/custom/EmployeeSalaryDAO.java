package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.EmpSalary;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeSalaryDAO extends CrudDAO<EmpSalary> {
//    public boolean insert(EmpSalaryDto empSalaryDto) throws SQLException ;
//
//    public ArrayList<EmpSalaryDto> loadTbl() throws SQLException ;
//
//    public boolean Update(EmpSalaryDto empSalaryDto) throws SQLException ;
//
//    public boolean delete(EmpSalaryDto empSalaryDto) throws SQLException ;

    public List<String> getAllEmpId() throws SQLException ;

}
