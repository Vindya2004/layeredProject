package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {

     Double getpriceValues(String id) throws SQLException ;    //Product

     int getCount(String productId) throws SQLException ;//product

     List<String> getAllProductId() throws SQLException ;

     double getPrice(String productId) throws SQLException ;


    int selectQTY(String productId) throws SQLException ;

    boolean updateStock(int Qty,String productId)throws SQLException ;
}
