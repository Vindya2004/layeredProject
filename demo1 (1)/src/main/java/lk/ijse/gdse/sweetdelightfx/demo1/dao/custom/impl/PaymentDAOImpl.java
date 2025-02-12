package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.PaymentDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.db.DBConnection;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    public boolean insert(Payment paymentDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Payment values(?,?,?,?)",
                paymentDto.getPaymentId(),
                paymentDto.getPaymentMethod(),
                paymentDto.getPaymentDate(),
                paymentDto.getOrdId()
        );
    }

    public boolean update(Payment paymentDto) throws SQLException {
        return SQLUtil.execute(
                "Update Payment set Pay_method=?,Pay_date where Pay_Id=?",
                paymentDto.getPaymentMethod(),
                paymentDto.getPaymentDate(),
                paymentDto.getPaymentId()

        );
    }

    @Override
    public boolean delete(Payment customerDto) throws SQLException {
        return false;
    }

    public ArrayList<Payment> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Payment");

        ArrayList<Payment> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            Payment payment = new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)
            );
            paymentDtos.add(payment);
        }
        return paymentDtos;
    }

    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Pay_Id from Payment order by Pay_Id desc limit 1");  //

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("p%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "Pa001";
    }


    public List<String> getAllpayId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT Sup_Id from Supplier";   ///
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rst = statement.executeQuery();
        List<String> payIds = new ArrayList<>();
        while (rst.next()) {
            payIds.add(rst.getString(1));
        }
        return payIds;
    }

    @Override
    public boolean savePay(String paymentId, String paymentMethod, String paymentDate, double cost, String ordId) throws SQLException {
        return SQLUtil.execute("INSERT INTO Payment (Pay_Id , Pay_method, Pay_date, Cost, Order_Id) VALUES (?, ?, ?, ?, ?)", paymentId, paymentMethod, paymentDate, cost, ordId);

    }


    public String getPaymentId() throws SQLException { // get payment id for order table
        ResultSet rst = SQLUtil.execute(
                "SELECT Pay_Id from Payment order by Pay_Id desc limit 1"  //
        );
        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001";
    }
}


