package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean insert(EmployeeDto customerDto) throws SQLException;


    boolean update(EmployeeDto customerDto) throws SQLException;

    boolean delete(Employee customerDto) throws SQLException ;

    ArrayList<EmployeeDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;
}
