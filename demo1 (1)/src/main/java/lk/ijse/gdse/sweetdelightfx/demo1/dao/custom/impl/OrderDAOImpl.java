package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.*;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.AllOrdersTM;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.OrderDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDAOImpl implements OrderDAO {


    public boolean deleteOrders(OrderDto orderDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Orders where Order_Id=?",
                orderDto.getOrderId()
        );

    }


    public ArrayList<AllOrdersTM> load() throws SQLException {
        ResultSet rst = SQLUtil.execute("select o.Order_Id, o.Ord_date,o.Customer_Id, s.Pro_Id ,s.Qty, s.price  from Orders o JOIN Order_detail s ON o.Order_Id = s.Order_Id");
        ArrayList<AllOrdersTM> allOrders = new ArrayList<>();
        while (rst.next()) {
            AllOrdersTM allOrder = new AllOrdersTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getDouble(6)
            );
           // System.out.println(allOrder);
            allOrders.add(allOrder);
        }
        return allOrders;
    }

    public String getAllPID() throws SQLException {
        ResultSet rst = SQLUtil.execute("select  Pay_Id  from Payment order by Pay_Id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001";
    }

    public String getOID() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Order_Id from Orders order by Order_Id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("O%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "O001";
    }

    public String getPId() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Pay_Id  from Payment order by Pay_Id  desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001";
    }

    public String getDID() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Deli_Id from Delivery order by Deli_Id  desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("D%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "D001";
    }


    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Order_Id from Orders order by Order_Id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("O%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "O001";
    }


    @Override
    public boolean insert(Order customerDto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Order customerDto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Order customerDto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Order> loadTbl() throws SQLException {
        return null;
    }

    @Override
    public boolean orderSave(String orderId, String orderDate, String customerId) throws SQLException {
        return SQLUtil.execute("INSERT INTO Orders (Order_Id, Ord_date, Customer_Id) VALUES (?, ?, ?)", orderId, orderDate, customerId);

    }

}


