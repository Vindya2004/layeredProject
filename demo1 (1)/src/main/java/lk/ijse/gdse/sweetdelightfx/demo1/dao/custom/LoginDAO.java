package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.LoginDto;
import java.sql.SQLException;


public interface LoginDAO {
    public boolean saveLogin(LoginDto loginDto) throws SQLException ;
}
