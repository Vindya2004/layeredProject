package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.EmployeeSalaryDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.db.DBConnection;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.EmpSalary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSalaryDAOImpl implements EmployeeSalaryDAO {
    public boolean insert(EmpSalary empSalaryDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Employee_Payment values(?,?,?,?)",
                empSalaryDto.getPayId(),
                empSalaryDto.getSalary(),
                empSalaryDto.getPayDate(),
                empSalaryDto.getEmpId()

        );
    }



    public ArrayList<EmpSalary> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee_Payment");

        ArrayList<EmpSalary> empSalaryDtos = new ArrayList<>();

        while (rst.next()) {
            EmpSalary empSalary = new EmpSalary(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            empSalaryDtos.add(empSalary);
        }
        return empSalaryDtos;
    }
    public boolean update(EmpSalary empSalaryDto) throws SQLException {
        return SQLUtil.execute(
                "update Employee_Payment SET salary = ? , date = ? , Emp_Id = ?  where em_paymentId = ?",
                empSalaryDto.getSalary(),
                empSalaryDto.getPayDate(),
                empSalaryDto.getEmpId(),
                empSalaryDto.getPayId()
        );
    }
    public boolean delete(EmpSalary empSalaryDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Employee_Payment where em_paymentId=?",
                empSalaryDto.getPayId()

        );
    }
    public String loadNExtID() throws SQLException {
        System.out.println("loadNExtID");
        ResultSet rst = SQLUtil.execute("select em_paymentId from Employee_Payment order by em_paymentId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("E%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "E001";
    }
    public List<String> getAllEmpId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select emp_id from Employee";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rst = statement.executeQuery();
        List<String> empIds = new ArrayList<>();
        while (rst.next()) {
            empIds.add(rst.getString(1));
        }
        return empIds;
    }

}
