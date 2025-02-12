package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.UserDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<UserDto> {
     boolean insert(UserDto userDto) throws SQLException ;

     boolean update(UserDto userDto) throws SQLException ;

     boolean delete(UserDto userDto) throws SQLException ;

     ArrayList<UserDto> loadTbl() throws SQLException ;
}
