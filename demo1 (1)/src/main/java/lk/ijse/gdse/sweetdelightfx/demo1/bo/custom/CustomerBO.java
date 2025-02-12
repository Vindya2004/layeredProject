package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.CustomerDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean insert(CustomerDto customerDto) throws SQLException;


    boolean update(CustomerDto customerDto) throws SQLException;

   // boolean delete(CustomerDto customerDto) throws SQLException ;

   // boolean delete(Customer customerDto) throws SQLException;

    boolean delete(Customer customerDto) throws SQLException;

    ArrayList<CustomerDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;

    public List<String> getAllCustId() throws SQLException ;
}
