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

                if (qty==-1) {
                    connection.rollback();
                    return "Product Not Found";
                }


                if (qty < oDto.getQuantity()) {
                    connection.rollback();
                    return "Insufficient Stock";
                }

                boolean b1 = productDAO.updateStock(oDto.getQuantity(),oDto.getProductId());
                System.out.println("b1: " + b1);

                    if (!b1) {
                        connection.rollback();
                        return "Failed to Update Product Stock";
                    }

            boolean b2 = orderDAO.orderSave(orderDto.getOrderId(),orderDto.getOrderDate(),orderDto.getCustomerId());
                    System.out.println("b2: " + b2);

                if (!b2) {
                    connection.rollback();
                    return "Failed to Save Order";
                }


            boolean b3 =orderDetailDAO.saveOrderDetail(oDto.getOrderId(),oDto.getProductId(),oDto.getQuantity(),oDto.getPrice());
                System.out.println("b3: " + b3);

                if (!b3) {
                    connection.rollback();
                    return "Failed to Save Order Details";
                }


            boolean b4 = paymentDAO.savePay(pDto.getPaymentId(),pDto.getPaymentMethod(),pDto.getPaymentDate(),pDto.getCost(),pDto.getOrdId());
                System.out.println("b4: " + b4);

                if (!b4) {
                    connection.rollback();
                    return "Failed to Save Payment Details";
                }

            // Insert into Delivery table
            boolean b5 = deliveryDAO.insertDelivery(dDto.getDeliveryId(),dDto.getDeleveryDate(),dDto.getDestination(),dDto.getTxtDelCharge());
                System.out.println("b5: " + b5);

                if (!b5) {
                    connection.rollback();
                    return "Failed to Save Delivery Details";
                }


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
