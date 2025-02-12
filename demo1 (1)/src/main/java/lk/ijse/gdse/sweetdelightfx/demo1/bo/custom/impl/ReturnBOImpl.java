package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.ReturnDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupplierDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.ReturnBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.ReturnDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Return;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBOImpl implements ReturnBO {
    ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RETURN);
    @Override
    public boolean insert(ReturnDto customerDto) throws SQLException {
        return returnDAO.insert(new Return(customerDto.getRetutnId(),customerDto.getReturnDate()));
    }

    @Override
    public boolean update(ReturnDto customerDto) throws SQLException {
        return returnDAO.update(new Return(customerDto.getRetutnId(),customerDto.getReturnDate()));
    }

    @Override
    public boolean delete(Return customerDto) throws SQLException {
        return returnDAO.delete(customerDto);
    }

    @Override
    public ArrayList<ReturnDto> loadTbl() throws SQLException {
        ArrayList<Return> load = returnDAO.loadTbl();
        ArrayList<ReturnDto> returnDtos = new ArrayList<>();
        for (Return returnDetail : load) {
            returnDtos.add(new ReturnDto(returnDetail.getRetutnId(),returnDetail.getReturnDate()));
        }
        return returnDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return returnDAO.loadNExtID();
    }
}
