package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.DeliveryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.OrderDetailDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.OrderDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.PaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.OrderBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.*;
import lk.ijse.gdse.sweetdelightfx.demo1.db.DBConnection;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Product;
import org.ietf.jgss.Oid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    CustomerDAO customerDao = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DELIVERY);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAIL);



    @Override
    public boolean insert(OrderDto customerDto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDto customerDto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(OrderDto customerDto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrderDto> loadTbl() throws SQLException {
        return null;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return orderDAO.loadNExtID();
    }

    public String insertOrders(OrderDto orderDto, OrderDetailDto oDto, DeliveryDto dDto, PaymentDto pDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false); // Begin transaction

            int qty =productDAO.selectQTY( oDto.getProductId());
            System.out.println("qty: " + qty);

//            String checkStockSql = "SELECT Qty FROM Product WHERE Pro_Id = ?";
//            try (PreparedStatement stockCheckStmt = connection.prepareStatement(checkStockSql)) {
//                stockCheckStmt.setString(1, oDto.getProductId());
//                ResultSet stockResult = stockCheckStmt.executeQuery();

                if (qty==-1) {
                    connection.rollback();
                    return "Product Not Found";
                }

//                int availableQty = stockResult.getInt("Qty");

                if (qty < oDto.getQuantity()) {
                    connection.rollback();
                    return "Insufficient Stock";
                }

                boolean b1 = productDAO.updateStock(oDto.getQuantity(),oDto.getProductId());
                System.out.println("b1: " + b1);
//                String updateProductSql = "UPDATE Product SET Qty = Qty - ? WHERE Pro_Id = ?";
//                try (PreparedStatement updateProductStmt = connection.prepareStatement(updateProductSql)) {
//                    updateProductStmt.setInt(1, oDto.getQuantity());
//                    updateProductStmt.setString(2, oDto.getProductId());

                    if (!b1) {
                        connection.rollback();
                        return "Failed to Update Product Stock";
                    }

            boolean b2 = orderDAO.orderSave(orderDto.getOrderId(),orderDto.getOrderDate(),orderDto.getCustomerId());
                    System.out.println("b2: " + b2);
//            String insertOrderSql = "INSERT INTO Orders (Order_Id, Ord_date, Customer_Id) VALUES (?, ?, ?)";
//            try (PreparedStatement insertOrderStmt = connection.prepareStatement(insertOrderSql)) {
//                insertOrderStmt.setString(1, orderDto.getOrderId());
//                insertOrderStmt.setString(2, orderDto.getOrderDate());
//                insertOrderStmt.setString(3, orderDto.getCustomerId());

                if (!b2) {
                    connection.rollback();
                    return "Failed to Save Order";
                }
//            }

            boolean b3 =orderDetailDAO.saveOrderDetail(oDto.getOrderId(),oDto.getProductId(),oDto.getQuantity(),oDto.getPrice());
                System.out.println("b3: " + b3);
//            String insertOrderDetailSql = "INSERT INTO Order_detail (Order_Id, Pro_Id, Qty, Price) VALUES (?, ?, ?, ?)";
//            try (PreparedStatement insertOrderDetailStmt = connection.prepareStatement(insertOrderDetailSql)) {
//                insertOrderDetailStmt.setString(1, oDto.getOrderId());
//                insertOrderDetailStmt.setString(2, oDto.getProductId());
//                insertOrderDetailStmt.setInt(3, oDto.getQuantity());
//                insertOrderDetailStmt.setDouble(4, oDto.getPrice());

                if (!b3) {
                    connection.rollback();
                    return "Failed to Save Order Details";
                }
//            }

            boolean b4 = paymentDAO.savePay(pDto.getPaymentId(),pDto.getPaymentMethod(),pDto.getPaymentDate(),pDto.getCost(),pDto.getOrdId());
                System.out.println("b4: " + b4);
//            String insertPaymentSql = "INSERT INTO Payment (Pay_Id , Pay_method, Pay_date, Cost, Order_Id) VALUES (?, ?, ?, ?, ?)";
//            try (PreparedStatement insertPaymentStmt = connection.prepareStatement(insertPaymentSql)) {
//                insertPaymentStmt.setString(1, pDto.getPaymentId());
//                insertPaymentStmt.setString(2, pDto.getPaymentMethod());
//                insertPaymentStmt.setString(3, pDto.getPaymentDate());
//                insertPaymentStmt.setDouble(4, pDto.getCost());
//                insertPaymentStmt.setString(5, pDto.getOrdId());

                if (!b4) {
                    connection.rollback();
                    return "Failed to Save Payment Details";
                }
//            }

            // Insert into Delivery table
            boolean b5 = deliveryDAO.insertDelivery(dDto.getDeliveryId(),dDto.getDeleveryDate(),dDto.getDestination(),dDto.getTxtDelCharge());
                System.out.println("b5: " + b5);
//            String insertDeliverySql = "INSERT INTO Delivery (Deli_Id, Deli_date, Destination, Del_charge) VALUES (?, ?, ?, ?)";
//            try (PreparedStatement insertDeliveryStmt = connection.prepareStatement(insertDeliverySql)) {
//                insertDeliveryStmt.setString(1, dDto.getDeliveryId());
//                insertDeliveryStmt.setString(2, dDto.getDeleveryDate());
//                insertDeliveryStmt.setString(3, dDto.getDestination());
//                insertDeliveryStmt.setDouble(4, dDto.getTxtDelCharge());

                if (!b5) {
                    connection.rollback();
                    return "Failed to Save Delivery Details";
                }
//            }

            connection.commit(); // Commit transaction
            return "Place Order";
        } catch (Exception e) {
            connection.rollback(); // Rollback transaction on error
            e.printStackTrace();
            return "Order Save Error: " + e.getMessage();
        } finally {
            connection.setAutoCommit(true); // Reset auto-commit
        }
    }


}
