package lk.ijse.gdse.sweetdelightfx.demo1.dao;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> {
    boolean insert(T customerDto) throws SQLException;


    boolean update(T customerDto) throws SQLException;

    boolean delete(T customerDto) throws SQLException ;

    ArrayList<T> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;
}
