package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.ReturnDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Return;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnDAOImpl implements ReturnDAO {
    public boolean insert(Return returnDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into return_detail values(?,?)",
                returnDto.getRetutnId(),
                returnDto.getReturnDate()
        );
    }

    public boolean update(Return returnDto) throws SQLException {
        return SQLUtil.execute(
                "Update return_detail set retu_date=? where return_id=?",
                returnDto.getReturnDate(),
                returnDto.getRetutnId()
        );
    }

    public boolean delete(Return returnDto) throws SQLException {
        return SQLUtil.execute(
                "delete from return_detail where return_id=?",
                returnDto.getRetutnId()
        );
    }

    public ArrayList<Return> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM return_detail");

        ArrayList<Return> returnDtos = new ArrayList<>();

        while (rst.next()) {
            Return returnDetail = new Return(
                  rst.getString(1),
                    rst.getDate(2).toLocalDate()
            );
            returnDtos.add(returnDetail);
        }
        return returnDtos;
    }

    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT return_id from return_detail order by return_id desc limit 1");  //

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("r%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "R001";
    }
}

