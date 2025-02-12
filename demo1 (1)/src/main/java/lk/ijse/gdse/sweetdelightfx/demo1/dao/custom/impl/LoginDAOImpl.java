package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.LoginDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl {
    public boolean saveLogin(LoginDto loginDto) throws SQLException {
        List<String>login = new ArrayList<>();
        String query = "select * from Admin where Email=? and Password=?";

        ResultSet rs= SQLUtil.execute(query,
                loginDto.getEmail(),
                loginDto.getPassword()
        );
        if(rs.next()){
            return true;
        }else {
            return false;
        }
    }
}
