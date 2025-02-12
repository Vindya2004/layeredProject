package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {
    // boolean insertProduct(ProductDto productDto) throws SQLException ;

   //  boolean updateProduct(ProductDto productDto) throws SQLException ;

   //  boolean deleteProduct(ProductDto productDto) throws SQLException ;

   //  ArrayList<ProductDto> loadTablePro() throws SQLException ;

    // String loadNExtId() throws SQLException ;
    public Double getpriceValues(String id) throws SQLException ;    //Product

    public int getCount(String productId) throws SQLException ;//product

    public List<String> getAllProductId() throws SQLException ;

    public double getPrice(String productId) throws SQLException ;


    int selectQTY(String productId) throws SQLException ;

    boolean updateStock(int Qty,String productId)throws SQLException ;
}
