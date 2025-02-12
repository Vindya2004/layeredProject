package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupPaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.SupplierPaymentDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.db.DBConnection;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.SupPayment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierPaymentDAOImpl implements SupplierPaymentDAO {
    public List<String> getAllpayId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select Sup_Id from Supplier";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rst = statement.executeQuery();
        List<String> payIds = new ArrayList<>();
        while (rst.next()) {
            payIds.add(rst.getString(1));
        }
        return payIds;
    }
    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("select sup_paymentId from Supplier_Payment order by sup_paymentId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("E%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "E001";
    }

    public boolean insert(SupPayment supPaymentDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Supplier_Payment values(?,?,?,?)",
                supPaymentDto.getPaymentId(),
                supPaymentDto.getPayment(),
                supPaymentDto.getPaymentDate(),
                supPaymentDto.getSupplierId()
        );
    }

    public List<SupPaymentDto> getAllSup() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier_Payment");

        ArrayList<SupPaymentDto> empSalaryDtos = new ArrayList<>();

        while (rst.next()) {
            SupPaymentDto empSalaryDto = new SupPaymentDto(
                    rst.getString(1),
                    rst.getDouble(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            empSalaryDtos.add(empSalaryDto);
        }
        return empSalaryDtos;
    }

    public boolean delete(SupPayment supPaymentDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Supplier_Payment where sup_paymentId=?",

                supPaymentDto.getPaymentId()
        );
    }

    @Override
    public ArrayList<SupPayment> loadTbl() throws SQLException {
        return null;
    }

    public boolean update(SupPayment supPaymentDto) throws SQLException {
        return SQLUtil.execute(
                "update Supplier_Payment SET payment = ?,date = ?,Sup_Id= ? where sup_paymentId=?",
                supPaymentDto.getPayment(),
                supPaymentDto.getPaymentDate(),
                supPaymentDto.getSupplierId(),
                supPaymentDto.getPaymentId()
        );
    }
}
