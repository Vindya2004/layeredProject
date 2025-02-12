package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmpSalaryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.EmpSalary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeSalaryBO extends SuperBO {
    boolean insert(EmpSalaryDto customerDto) throws SQLException;


    boolean update(EmpSalaryDto customerDto) throws SQLException;

    boolean delete(EmpSalary customerDto) throws SQLException ;

    ArrayList<EmpSalaryDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;

    public List<String> getAllEmpId() throws SQLException ;
}
