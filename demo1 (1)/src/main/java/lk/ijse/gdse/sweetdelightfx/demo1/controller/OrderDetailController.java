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


    }

    private void clearDetailsOrderDetail() {
        ord_delOrderId.setText("");
        ord_delProductId.setText("");
        ord_delQuantity.setText("");
    }

    private void loadTableOrderDetail() throws SQLException {

    }

    @FXML
    void saveOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {


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

