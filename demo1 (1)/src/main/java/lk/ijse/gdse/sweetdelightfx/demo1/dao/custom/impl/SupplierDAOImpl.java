package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.SupplierDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    public boolean update(Supplier supplierDto) throws SQLException {
        return SQLUtil.execute(
          "Update Supplier set Sup_Name=?,PhoneNo=? where Sup_Id=?",
                supplierDto.getSupplierName(),
                supplierDto.getPhoneNumber(),
                supplierDto.getSupplierId()
        );
    }

    public boolean delete(Supplier supplierDto) throws SQLException {
        return SQLUtil.execute(
          "delete from Supplier where Sup_Id=?",
          supplierDto.getSupplierId()
        );
    }

    public ArrayList<Supplier> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier");

        ArrayList<Supplier> supplierDTOS = new ArrayList<>();

        while (rst.next()) {
            Supplier supplier = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            supplierDTOS.add(supplier);
        }
        return supplierDTOS;
    }

    public boolean insert(Supplier supplierDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Supplier values(?,?,?)",
                supplierDto.getSupplierId(),
                supplierDto.getSupplierName(),
                supplierDto.getPhoneNumber()
        );
    }

    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Sup_Id from Supplier order by Sup_Id desc limit 1");  //

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("s%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "s001";
    }
}
