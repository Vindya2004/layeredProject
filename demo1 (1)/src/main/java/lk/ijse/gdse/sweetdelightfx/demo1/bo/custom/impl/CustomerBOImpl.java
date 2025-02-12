package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.CustomerDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.CustomerBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.CustomerDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDao = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean insert(CustomerDto customerDto) throws SQLException {
        return customerDao.insert(new Customer(customerDto.getCustomerId(),customerDto.getName(),customerDto.getAddress(),customerDto.getPhone(),customerDto.getEmail()));
    }

    @Override
    public boolean update(CustomerDto customerDto) throws SQLException {
        return customerDao.update(new Customer(customerDto.getCustomerId(),customerDto.getName(),customerDto.getAddress(),customerDto.getPhone(),customerDto.getEmail()));
    }

//    @Override
//    public boolean delete(CustomerDto customerDto) throws SQLException {
//        return false;
//    }

    @Override
    public boolean delete(Customer customerDto) throws SQLException {
        return customerDao.delete(customerDto);
    }

    @Override
    public ArrayList<CustomerDto> loadTbl() throws SQLException {
       ArrayList<Customer> load = customerDao.loadTbl();
       ArrayList<CustomerDto> customerDtos = new ArrayList<>();
       for (Customer customer : load) {
           customerDtos.add(new CustomerDto(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getPhone(),customer.getEmail()));
       }
       return customerDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return customerDao.loadNExtID();
    }

    @Override
    public List<String> getAllCustId() throws SQLException {
        return customerDao.getAllCustId();
    }
}
