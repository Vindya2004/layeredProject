package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.BatchDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.BatchBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.BatchDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.EmployeeDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Batch;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class BatchBOImpl implements BatchBO {
    BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BATCH);
    @Override
    public boolean insert(BatchDto customerDto) throws SQLException {
        return batchDAO.insert(new Batch(customerDto.getBatchId(),customerDto.getBatchPrice(),customerDto.getProductId()));
    }

    @Override
    public boolean update(BatchDto customerDto) throws SQLException {
        return batchDAO.update(new Batch(customerDto.getBatchId(),customerDto.getBatchPrice(),customerDto.getProductId()));
    }

    @Override
    public boolean delete(Batch customerDto) throws SQLException {
        return batchDAO.delete(customerDto);
    }

    @Override
    public ArrayList<BatchDto> loadTbl() throws SQLException {
        ArrayList<Batch> load = batchDAO.loadTbl();
        ArrayList<BatchDto> batchDtos = new ArrayList<>();
        for (Batch batch : load) {
            batchDtos.add(new BatchDto(batch.getBatchId(),batch.getBatchPrice(),batch.getProductId()));
        }
        return batchDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return batchDAO.loadNExtID();
    }
}
