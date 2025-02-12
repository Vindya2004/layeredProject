package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.CustomerDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.EmployeeBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.EmployeeDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Customer;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public boolean insert(EmployeeDto customerDto) throws SQLException {
        return employeeDAO.insert(new Employee(customerDto.getEmployeeId(),customerDto.getEmployeeName(),customerDto.getSalary(),customerDto.getEmployeePhone()));
    }

    @Override
    public boolean update(EmployeeDto customerDto) throws SQLException {
        return employeeDAO.update(new Employee(customerDto.getEmployeeId(),customerDto.getEmployeeName(),customerDto.getSalary(),customerDto.getEmployeePhone()));
    }

    @Override
    public boolean delete(Employee customerDto) throws SQLException {
        return employeeDAO.delete(customerDto);
    }

    @Override
    public ArrayList<EmployeeDto> loadTbl() throws SQLException {
        ArrayList<Employee> load = employeeDAO.loadTbl();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : load) {
            employeeDtos.add(new EmployeeDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getSalary(),employee.getEmployeePhone()));
        }
        return employeeDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return employeeDAO.loadNExtID();
    }
}
