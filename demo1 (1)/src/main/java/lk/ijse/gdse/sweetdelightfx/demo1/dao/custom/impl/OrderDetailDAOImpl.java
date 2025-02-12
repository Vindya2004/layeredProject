package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.OrderDetailDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.OrderDetailDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.OrderDetail;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public boolean insertOrderDetails(OrderDetailDto orderDetailDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Order_detail values(?,?,?)",
                orderDetailDto.getProductId(),
                orderDetailDto.getOrderId(),
                orderDetailDto.getQuantity()
        );
    }

    public boolean updateOrderDetails(OrderDetailDto orderDetailDto) throws SQLException {
        return SQLUtil.execute(
                "update from Order_detail set Pro_Id=?,Qty=? where Order_Id=?",
                orderDetailDto.getProductId(),
                orderDetailDto.getQuantity(),
                orderDetailDto.getOrderId()
        );
    }

    public boolean deletetOrderDetails(OrderDetailDto orderDetailDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Order_detail where Order_Id=?",
                orderDetailDto.getOrderId()
        );
    }

    public ArrayList<OrderDetailDto> loadTblOrderDetails() throws SQLException {

    ArrayList<OrderDetailDto> orderDetailDtos = new ArrayList<>();

   return orderDetailDtos;
    }

    @Override
    public boolean insert(OrderDetail customerDto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetail customerDto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(OrderDetail customerDto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrderDetail> loadTbl() throws SQLException {
        return null;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return "";
    }

    @Override
    public boolean saveOrderDetail(String orderId, String productId, int quantity, double price) throws SQLException {
        return SQLUtil.execute("INSERT INTO Order_detail (Order_Id, Pro_Id, Qty, Price) VALUES (?, ?, ?, ?)", orderId, productId, quantity, price);

    }
}
