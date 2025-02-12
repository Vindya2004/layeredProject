package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.ProductDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.SuperBO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductBO extends SuperBO {
    boolean insert(ProductDto customerDto) throws SQLException;


    boolean update(ProductDto customerDto) throws SQLException;

    boolean delete(Product customerDto) throws SQLException ;

    ArrayList<ProductDto> loadTbl() throws SQLException ;

    String loadNExtID() throws SQLException ;

    public Double getpriceValues(String id) throws SQLException ;    //Product

    public int getCount(String productId) throws SQLException ;//product

    public List<String> getAllProductId() throws SQLException ;

    public double getPrice(String productId) throws SQLException ;
}
