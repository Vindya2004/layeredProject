package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.ProductDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.ProductDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.db.DBConnection;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    public boolean insert(Product productDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Product values(?,?,?,?,?,?)",
                productDto.getTxtProId(),
                productDto.getTxtProName(),
                productDto.getTxtPrice(),
                productDto.getTxtQty(),
                productDto.getTxtInventory(),
                productDto.getTxtSupplierId()


        );
    }

    public boolean update(Product productDto) throws SQLException {
        return SQLUtil.execute(
            "Update Product set Pro_Name=?,Price=?,Qty=?,Inventory_Id=?,Sup_Id=?Where Pro_Id=?",

                productDto.getTxtProName(),
                productDto.getTxtPrice(),
                productDto.getTxtQty(),
                productDto.getTxtInventory(),
                productDto.getTxtSupplierId(),
                productDto.getTxtProId()


        );
    }

    public boolean delete(Product productDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Product where Pro_Id=?",
                productDto.getTxtProId()
        );
    }

    public ArrayList<Product> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Product");


        ArrayList<Product> productDTOS = new ArrayList<>();

        while (rst.next()) {
            Product product = new Product(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getDouble(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6)

            );
            productDTOS.add(product);
        }
        return productDTOS;
    }

    @Override
    public String loadNExtID() throws SQLException {
        ResultSet rst = SQLUtil.execute("select Pro_Id from Product order by Pro_Id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("p%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001";
    }

    public Double getpriceValues(String id) throws SQLException {    //get price of product for order table
        ProductDto d = new ProductDto();
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select Price from Product where Pro_Id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();

        double price = 0;
        if(rs.next()) {
            price = rs.getDouble("Price");
        }

        return price;

    }

    public int getCount(String productId) throws SQLException {  //get count of product for order table
        ResultSet resultSet = SQLUtil.execute(
                "SELECT Qty FROM Product WHERE Pro_Id = ?",
                productId
        );

        if (resultSet.next()) {
            return resultSet.getInt("Qty");
        } else {
            throw new SQLException("Product not found for ID: " + productId);
        }
    }

    public List<String> getAllProductId() throws SQLException {
        List<String> ssss = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(
                "select Pro_Id from Product"

        );
        while (rst.next()) {
            ssss.add(rst.getString(1));
        }
        return ssss;
    }

    public double getPrice(String productId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute(
                "select Price from Product where Pro_Id=?",
                productId
        );
        if (resultSet.next()) {
            return resultSet.getDouble("Price");
        }else {
            throw new SQLException("Product not found for ID:" + productId);
        }
    }
   public int selectQTY(String productId) throws SQLException {
       ResultSet rst= SQLUtil.execute("SELECT Qty FROM Product WHERE Pro_Id = ?", productId);
       return rst.next() ? (rst.getInt("Qty")) : -1;
   }

    @Override
    public boolean updateStock(int Qty,String productId) throws SQLException {
        return SQLUtil.execute("UPDATE Product SET Qty = Qty - ? WHERE Pro_Id = ?", Qty, productId);

    }

}
