package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupplierDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    boolean insert(SupplierDto customerDto) throws SQLException;

    boolean update(SupplierDto customerDto) throws SQLException;

    boolean delete(Supplier customerDto) throws SQLException ;

    ArrayList<SupplierDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;
}
