package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.UserDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl {
    public boolean insertUser(UserDto userDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into User values(?,?,?)",
                userDto.getUserId(),
                userDto.getEmail(),
                userDto.getPassword()
        );
    }

    public boolean updatetUser(UserDto userDto) throws SQLException {
        return SQLUtil.execute(
                "Update User set Password=?,Email=? where User_Id=?",
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getUserId()
        );
    }

    public boolean deletetUser(UserDto userDto) throws SQLException {
        return SQLUtil.execute(
                "delete from User where User_Id=?",
                userDto.getUserId()
        );
    }

    public ArrayList<UserDto> loadTblUser() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM User");

        ArrayList<UserDto> userDtos = new ArrayList<>();

        while (rst.next()) {
            UserDto userDto = new UserDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
