package lk.ijse.gdse.sweetdelightfx.demo1.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.OrderDetailTM;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.OrderDetailDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.OrderDetailDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderDetailController implements Initializable {

    @FXML
    private TextField ord_delOrderId;

    @FXML
    private TextField ord_delProductId;

    @FXML
    private TextField ord_delQuantity;

    @FXML
    private TableColumn<OrderDetailTM, String> orderId;

    @FXML
    private TableColumn<OrderDetailTM, String> productId;

    @FXML
    private TableColumn<OrderDetailTM, Integer> quantity;

    @FXML
    private TableView<OrderDetailTM> tblOrderDetail;
    @FXML
    private OrderDetailDAOImpl orderDetailModel;

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAIL);

    public OrderDetailController() {
        orderDetailModel = new OrderDetailDAOImpl();
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
//        String id = ord_delOrderId.getText();
//
//        int qty = Integer.parseInt(ord_delQuantity.getText());
//
//        OrderDetailDto orderDetailDto =new  OrderDetailDto(id,"",qty);
//
//        try{
//            boolean rasp = orderDetailModel.deletetOrderDetails(orderDetailDto);
//            if (rasp) {
//                new Alert(Alert.AlertType.INFORMATION, "Order Details delete Sucsess...!").show();
//                loadTableOrderDetail();
//                clearDetailsOrderDetail();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Fail to delete order details...!").show();
//            }
//        } catch (SQLException e) {
//            //throw new RuntimeException(e);
//            System.out.println(e);
//        }

    }

    private void clearDetailsOrderDetail() {
        ord_delOrderId.setText("");
        ord_delProductId.setText("");
        ord_delQuantity.setText("");
    }

    private void loadTableOrderDetail() throws SQLException {
//        ArrayList<OrderDetailDto> orderDetailDtos = orderDetailModel.loadTblOrderDetails();
//
//        ObservableList<OrderDetailTM> orderDetailTMS = FXCollections.observableArrayList();
//
//
//        for (OrderDetailDto orderDetailDto : orderDetailDtos) {
//            OrderDetailTM orderDetailTM = new OrderDetailTM(
//                    orderDetailDto.getOrderId(),
//                    orderDetailDto.getProductId(),
//                    orderDetailDto.getQuantity()
//            );
//
//
//
//            orderDetailTMS.add(orderDetailTM);
//        }
//
//        tblOrderDetail.setItems(orderDetailTMS);
    }

    @FXML
    void saveOnAction(ActionEvent event) {
//      String id = ord_delOrderId.getText();
//      String productId = ord_delProductId.getText();
//      int quantity = Integer.parseInt(ord_delQuantity.getText());
//
//        OrderDetailDto orderDetailDto = new OrderDetailDto(id, productId, quantity);
//
//        try{
//            boolean rasp = orderDetailModel.insertOrderDetails(orderDetailDto);
//            if (rasp) {
//                new Alert(Alert.AlertType.INFORMATION, "Order Details save Sucsess...!").show();
//                loadTableOrderDetail();
//                clearDetailsOrderDetail();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Fail to save order details...!").show();
//            }
//        } catch (SQLException e) {
//            //throw new RuntimeException(e);
//            System.out.println(e);
//        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
//        String id = ord_delOrderId.getText();
//        String productId = ord_delProductId.getText();
//        int quantity = Integer.parseInt(ord_delQuantity.getText());
//
//        OrderDetailDto orderDetailDto = new OrderDetailDto(id, productId, quantity);
//
//        try{
//            boolean rasp = orderDetailModel.updateOrderDetails(orderDetailDto);
//            if (rasp) {
//                new Alert(Alert.AlertType.INFORMATION, "Order Details update Sucsess...!").show();
//                loadTableOrderDetail();
//                clearDetailsOrderDetail();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Fail to update order details...!").show();
//            }
//        } catch (SQLException e) {
//            //throw new RuntimeException(e);
//            System.out.println(e);
//        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        try {
            loadTableOrderDetail();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

