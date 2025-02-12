package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.BatchDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchDAOImpl implements BatchDAO {
    public boolean insert(Batch batchDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Batch values(?,?,?)",
                batchDto.getBatchId(),
                batchDto.getBatchPrice(),
                batchDto.getProductId()
        );
    }

    public boolean update(Batch batchDto) throws SQLException {
        return SQLUtil.execute(
                "Update Batch set Price=?,Pro_Id=? where Batch_Id=?",
                batchDto.getBatchPrice(),
                batchDto.getProductId(),
                batchDto.getBatchId()
        );
    }

    public boolean delete(Batch batchDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Batch where Batch_Id=?",
                batchDto.getBatchId()
        );
    }

    public ArrayList<Batch> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Batch");

        ArrayList<Batch> batchDtos = new ArrayList<>();

        while (rst.next()) {
            Batch batch = new Batch(
                  rst.getString(1),
                    rst.getDouble(2),
                    rst.getString(3)
            );
            batchDtos.add(batch);
        }
        return batchDtos;
    }

    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT Batch_Id from Batch order by Batch_Id desc limit 1");//

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("b%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "B001";
    }
}
