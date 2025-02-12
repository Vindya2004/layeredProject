package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.BatchDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Batch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BatchBO extends SuperBO {
    boolean insert(BatchDto customerDto) throws SQLException;


    boolean update(BatchDto customerDto) throws SQLException;

    boolean delete(Batch customerDto) throws SQLException ;

    ArrayList<BatchDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;


}
