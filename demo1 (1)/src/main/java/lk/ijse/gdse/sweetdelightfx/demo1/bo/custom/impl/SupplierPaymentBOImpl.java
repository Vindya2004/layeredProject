package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.DeliveryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupPaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.SupplierPaymentBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.SupplierPaymentDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Delivery;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.SupPayment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierPaymentBOImpl implements SupplierPaymentBO {
    SupplierPaymentDAO supplierPaymentDAO = (SupplierPaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIERPAYMENT);

    @Override
    public boolean insert(SupPaymentDto customerDto) throws SQLException {
        return supplierPaymentDAO.insert(new SupPayment(customerDto.getPaymentId(),customerDto.getPayment(),customerDto.getPaymentDate(),customerDto.getSupplierId()));
    }

    @Override
    public boolean update(SupPaymentDto customerDto) throws SQLException {
        return supplierPaymentDAO.update(new SupPayment(customerDto.getPaymentId(),customerDto.getPayment(),customerDto.getPaymentDate(),customerDto.getSupplierId()));
    }

    @Override
    public boolean delete(SupPayment customerDto) throws SQLException {
        return supplierPaymentDAO.delete(customerDto);
    }

    @Override
    public ArrayList<SupPaymentDto> loadTbl() throws SQLException {
        ArrayList<SupPayment> load = supplierPaymentDAO.loadTbl();
        ArrayList<SupPaymentDto> supPaymentDtos = new ArrayList<>();
        for (SupPayment supPayment : load) {
            supPaymentDtos.add(new SupPaymentDto(supPayment.getPaymentId(),supPayment.getPayment(),supPayment.getPaymentDate(),supPayment.getSupplierId()));
        }
        return supPaymentDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return supplierPaymentDAO.loadNExtID();
    }

    @Override
    public List<SupPaymentDto> getAllSup() throws SQLException {
        return List.of();
    }
}
