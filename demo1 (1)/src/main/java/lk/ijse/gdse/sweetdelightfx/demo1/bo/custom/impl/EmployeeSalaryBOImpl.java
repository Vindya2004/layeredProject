package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.BatchDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmpSalaryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.EmployeeSalaryBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.EmployeeSalaryDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Batch;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.EmpSalary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSalaryBOImpl implements EmployeeSalaryBO {
    EmployeeSalaryDAO employeeSalaryDAO = (EmployeeSalaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEESALARY);

    @Override
    public boolean insert(EmpSalaryDto customerDto) throws SQLException {
        return employeeSalaryDAO.insert(new EmpSalary(customerDto.getPayId(),customerDto.getSalary(),customerDto.getPayDate(),customerDto.getEmpId()));
    }

    @Override
    public boolean update(EmpSalaryDto customerDto) throws SQLException {
        return employeeSalaryDAO.update(new EmpSalary(customerDto.getPayId(),customerDto.getSalary(),customerDto.getPayDate(),customerDto.getEmpId()));
    }

    @Override
    public boolean delete(EmpSalary customerDto) throws SQLException {
        return employeeSalaryDAO.delete(customerDto);
    }

    @Override
    public ArrayList<EmpSalaryDto> loadTbl() throws SQLException {
        ArrayList<EmpSalary> load = employeeSalaryDAO.loadTbl();
        ArrayList<EmpSalaryDto> empSalaryDtos = new ArrayList<>();
        for (EmpSalary empSalary : load) {
            empSalaryDtos.add(new EmpSalaryDto(empSalary.getPayId(),empSalary.getSalary(),empSalary.getPayDate(),empSalary.getEmpId()));
        }
        return empSalaryDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return employeeSalaryDAO.loadNExtID();
    }

    @Override
    public List<String> getAllEmpId() throws SQLException {
        return employeeSalaryDAO.getAllEmpId();
    }
}
