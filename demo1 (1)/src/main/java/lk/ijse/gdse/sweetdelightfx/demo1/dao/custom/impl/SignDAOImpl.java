package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SignDto;
import lk.ijse.gdse.sweetdelightfx.demo1.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignDAOImpl {
    public boolean saveSign(SignDto signDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "insert into Admin values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setObject(1,signDto.getBtnUserName());
        ps.setObject(2,signDto.getBtnEmail());
        ps.setObject(3,signDto.getBtnPassword());
        ps.setObject(4,signDto.getBtnConfirmPassword());

        int result = ps.executeUpdate();
        boolean isSaved = result > 0;
        return isSaved;


    }
}
