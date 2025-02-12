package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.ReturnDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Return;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReturnBO extends SuperBO {
    boolean insert(ReturnDto customerDto) throws SQLException;


    boolean update(ReturnDto customerDto) throws SQLException;

    boolean delete(Return customerDto) throws SQLException ;

    ArrayList<ReturnDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;
}
