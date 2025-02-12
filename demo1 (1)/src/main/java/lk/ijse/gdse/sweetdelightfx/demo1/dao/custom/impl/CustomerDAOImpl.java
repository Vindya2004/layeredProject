package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.CustomerDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean insert(Customer customerDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Customer values(?,?,?,?,?)",
                customerDto.getCustomerId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getPhone()
        );
    }

    public boolean update(Customer customerDto) throws SQLException {
        return SQLUtil.execute(
                "Update Customer set Name=?,Email=?,Address=?,PhoneNo=? where Customer_Id=?",
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getAddress(),
                customerDto.getPhone(),
                customerDto.getCustomerId()
        );
    }

    public boolean delete(Customer customerDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Customer where Customer_Id=?",
                customerDto.getCustomerId()
        );
    }
//----------------------------------------------------
    public ArrayList<Customer> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<Customer> customerDtos = new ArrayList<>();

        while (rst.next()) {
            Customer customer = new Customer(
                   rst.getString(1),
                   rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            customerDtos.add(customer);
        }
        return customerDtos;
    }

    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Customer_Id from Customer order by Customer_Id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001";
    }

    public List<String> getAllCustId() throws SQLException {//get all customer id for order
        List<String> ssss = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(
                "select Customer_Id from Customer"
        );
        while (rst.next()) {
            ssss.add(rst.getString(1));
        }
        return ssss;
    }
}

