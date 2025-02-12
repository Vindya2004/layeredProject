package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupplierDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.SupplierBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.SupplierDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDao = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public boolean insert(SupplierDto customerDto) throws SQLException {
        return supplierDao.insert(new Supplier(customerDto.getSupplierId(),customerDto.getSupplierName(),customerDto.getPhoneNumber()));
    }

    @Override
    public boolean update(SupplierDto customerDto) throws SQLException {
        return supplierDao.update(new Supplier(customerDto.getSupplierId(),customerDto.getSupplierName(),customerDto.getPhoneNumber()));
    }

    @Override
    public boolean delete(Supplier customerDto) throws SQLException {
        return supplierDao.delete(customerDto);
    }

    @Override
    public ArrayList<SupplierDto> loadTbl() throws SQLException {
        ArrayList<Supplier> load = supplierDao.loadTbl();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        for (Supplier supplier : load) {
            supplierDtos.add(new SupplierDto(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getPhoneNumber()));
        }
        return supplierDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return supplierDao.loadNExtID();
    }
}
