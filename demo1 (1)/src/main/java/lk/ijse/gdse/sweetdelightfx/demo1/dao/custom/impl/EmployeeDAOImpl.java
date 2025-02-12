package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.EmployeeDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public boolean insert(Employee employeeDto) throws SQLException {
        return SQLUtil.execute(
                "insert into Employee values(?,?,?,?)",
                employeeDto.getEmployeeId(),
                employeeDto.getEmployeeName(),
                employeeDto.getSalary(),
                employeeDto.getEmployeePhone()
        );
    }

    public boolean update(Employee employeeDto) throws SQLException {
        return SQLUtil.execute(
                "update Employee set Emp_name=?,Salary=?,PhoneNO=? where Emp_id=?",
                employeeDto.getEmployeeName(),
                employeeDto.getSalary(),
                employeeDto.getEmployeePhone(),
                employeeDto.getEmployeeId()
        );
    }

    public boolean delete(Employee employeeDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Employee where Emp_id=?",
                employeeDto.getEmployeeId()
        );
    }

    public ArrayList<Employee> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");


        ArrayList<Employee> employeeDTOS = new ArrayList<>();

        while (rst.next()) {
            Employee employee = new Employee(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getDouble(3),
                    rst.getString(4)   // Phone
            );
            employeeDTOS.add(employee);
        }
        return employeeDTOS;
    }


    public Boolean checkId(EmployeeDto dto) throws SQLException {
        return SQLUtil.execute(
                "SELECT Emp_Id FROM Employee WHERE Emp_Id = ?",
                    dto.getEmployeeId()
                );
    }

    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Emp_Id from Employee order by Emp_Id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("e%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "e001";
    }
}
