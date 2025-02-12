package lk.ijse.gdse.sweetdelightfx.demo1.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.*;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.AllOrdersTM;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.CartTM;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.DeliveryTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.*;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.*;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.OrderDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private TextField txtStock;

    @FXML
    private TextField qtyOnHand;

    @FXML
    private Label qtyHand;
    @FXML
    private ComboBox txtcustId;

    @FXML
    private TableColumn<CartTM, String> productId;

    @FXML
    private TableColumn<?,?> removebtn;


    @FXML
    private TableColumn<CartTM, Double> total;

    @FXML
    private TableView<CartTM> tblAddCart;

    @FXML
    private TableColumn<CartTM, Double> unitPrice;

    @FXML
    private TableColumn<CartTM, Integer> count;



    @FXML
    private TableColumn<AllOrdersTM, String> customerId;

    @FXML
    private TableColumn<DeliveryTM, String> deliveryId;

    @FXML
    private TableColumn<AllOrdersTM, String> orderDate;

    @FXML
    private TableColumn<AllOrdersTM, String> orderId;

    @FXML
    private TableColumn<AllOrdersTM, Integer>amount;

    @FXML
    private TableColumn<AllOrdersTM, Double>price;

    @FXML
    private TableColumn<AllOrdersTM, String>paymentId;

    @FXML
    private TextField txtCount;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDeliveryId;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPayementId;

    @FXML
    private TextField txtPrice;



    @FXML
    private TableView<AllOrdersTM> tblOrder;

    @FXML
    private TextField txtdate;

//    @FXML
//    private TextField txtProductId;
    @FXML
    private ComboBox<String> txtProductId;

    private OrderDAOImpl ordModel;

    @FXML
    private TextField txtDestination;

    @FXML
    private TextField txtDelId;

    @FXML
    private TextField txtDelCharge;
    @FXML
    private TextField cUprice;

    @FXML
    private TextField cartCount;

    @FXML
    private ComboBox<String> cproId;

    @FXML
    private Label txtTotal1;

    @FXML
    private TextField txtPrice1;

    @FXML
    private ComboBox pMethod;

//    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);
   // CustomerDAO customerDao = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
   // PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
   // DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DELIVERY);
   // OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);

    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    PaymentBO paymentBO =  (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getInstance().getBO(BOFactory.BOType.DELIVERY);
    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);

    public OrderController() {
        ordModel = new OrderDAOImpl();

    }
    private final ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();
    @FXML
    void addCartOnAction(ActionEvent event) {
        String selectId = txtProductId.getValue();
        double prc = 0;

        try {
            prc = productBO.getPrice(selectId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (selectId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select product id", ButtonType.OK).show();
            return;
        }

        int cartCount = Integer.parseInt(qtyHand.getText());
        int count = Integer.parseInt(txtCount.getText());

        if (cartCount < count) {
            new Alert(Alert.AlertType.ERROR, "Not enough items..!").show();
            return;
        }
        txtCount.setStyle("");

        double price = Double.parseDouble(txtPrice.getText());
        double amount = Double.parseDouble(txtPrice.getText());

        for (CartTM cartTM : cartTMS) {
            if (cartTM.getProductId().equals(selectId)) {
                int newQt = cartTM.getCount() + count; // Use count here, not cartCount
                cartTM.setCount(newQt);
                tblAddCart.refresh();
                return;
            }
        }

        Button btn = new Button("Remove");


        CartTM newCart = new CartTM(
                selectId,
                count,
                amount,
                prc,
                btn
        );

        btn.setOnAction(actionEvent -> {

            cartTMS.remove(newCart);

            tblAddCart.refresh();
        });




        cartTMS.add(newCart);
     //   tblAddCart.setItems(cartTMS);
    }


    private void setCellValues(){
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        unitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        total.setCellValueFactory(new PropertyValueFactory<>("amount"));

        removebtn.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));

        tblAddCart.setItems(cartTMS);
    }


    private void loadTableOrd() throws SQLException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String f1 = LocalDate.now().format(dateTimeFormatter);
        txtdate.setText(f1);

//        String pID = ordModel.getAllPID();
//        txtPrice1.setText(pID);

        ObservableList<String> oLis = FXCollections.observableArrayList(
                "Card","Cash"
        );
        pMethod.setItems(oLis);
        ObservableList<AllOrdersTM> allOrdersTMS = FXCollections.observableArrayList();
        ObservableList<DeliveryTM> allO = FXCollections.observableArrayList();
        try {
            ArrayList<AllOrdersTM> all = ordModel.load();


            allOrdersTMS.addAll(all);

            tblOrder.setItems(allOrdersTMS);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        //  ArrayList<OrderDto> orderDTOS = ordModel.loadTableOrd();
//
//        ObservableList<OrderTM> orderTMS = FXCollections.observableArrayList();
//
//
//        for (OrderDto orderDTO : orderDTOS) {
//            OrderTM orderTM = new OrderTM(
//                    orderDTO.getOrderId(),
//                    orderDTO.getOrderDate(),
//                    orderDTO.getAmount(),
//                    orderDTO.getCustomerId(),
//                    orderDTO.getPaymentId(),
//                    orderDTO.getDeliveryId()
//            );
//            orderTMS.add(orderTM);
//        }

       // tblOrder.setItems(orderTMS);
    }

    private void loadNextPId() {
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String id = txtOrderId.getText();

        Double i = 0.0;


        // OrderDto orderDto = new OrderDto(id,"",i,"","","");

        //  boolean rasp = ordModel.deleteOrders(orderDto);
//        if (rasp) {
//            new Alert(Alert.AlertType.INFORMATION, "Orders delete Sucsess...!").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Fail to delete order...!").show();
//        }


    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException {
        String id = txtOrderId.getText();
        String date = txtdate.getText();
        // String amount = txtAmount.getText();
        String custId = (String) txtcustId.getValue();
        int count = Integer.parseInt(txtCount.getText());
        String pId = txtProductId.getValue();
        double price = Double.parseDouble(txtPrice.getText());
        String method = pMethod.getSelectionModel().getSelectedItem().toString();
        String delId = txtDelId.getText();
        String destination = txtDestination.getText();
        double deliveryCharge = Double.parseDouble(txtDelCharge.getText());
        String payId = txtPrice1.getText();

        DeliveryDto deliveryDto = new DeliveryDto(delId,date,destination,deliveryCharge);
        PaymentDto paymentDto = new PaymentDto(payId,method,date,price,id);
        OrderDto orderDto = new OrderDto(id, date, custId);
        OrderDetailDto oDto = new OrderDetailDto(id, pId, count, price);


        try {
            String rasp = orderBO.insertOrders(orderDto, oDto,deliveryDto,paymentDto);
           // loadallId();
            loadTableOrd();
            JOptionPane.showConfirmDialog(null, rasp, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            refreshs();
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Sss", "Sdd", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showConfirmDialog(null, "Sss", "Sdd", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void refreshs() {
        txtPrice.setText("");
        txtCount.setText("");
        qtyHand.setText("0");
        txtDestination.setText("");
        txtDelCharge.setText("0");
        txtcustId.setItems(null);
        txtProductId.setItems(null);
        pMethod.setItems(null);



        try {
            setCellValues();
            // loadallId();
            loadTableOrd();
            getAllProductId();
            getOrderId();
            getCustId();
            loadPayId();
            loadDelId();


        } catch (Exception e) {
            System.out.println(e);
        }
        txtProductId.setStyle("-fx-border-color: null");


    }

    private void clearDetailsOrd() {
        txtOrderId.setText("");
        //txtdate.setText("");
        txtAmount.setText("");
        txtcustId.setItems(null);
        txtPayementId.setText("");
        txtDeliveryId.setText("");
    }


    @FXML
    void updateOnAction(ActionEvent event) throws SQLException {
        String id = txtOrderId.getText();
        String date = txtdate.getText();
        // String amount = txtAmount.getText();
        String custId = (String) txtcustId.getValue();
        int count = Integer.parseInt(txtCount.getText());
        String pId = txtProductId.getValue();
        double price = Double.parseDouble(txtPrice.getText());
       // String method = pMethod.getSelectionModel().getSelectedItem().toString();
       // String delId = txtDelId.getText();
      //  String destination = txtDestination.getText();
        //double deliveryCharge = Double.parseDouble(txtDelCharge.getText());
       // String payId = txtPrice1.getText();

        //DeliveryDto deliveryDto = new DeliveryDto(delId,date,destination,deliveryCharge);
        //PaymentDto paymentDto = new PaymentDto(payId,method,date,price,id);
        OrderDto orderDto = new OrderDto(id, date, custId);
        OrderDetailDto oDto = new OrderDetailDto(id, pId, count, price);


//        try {
//            String rasp = ordModel.updateOrders(orderDto, oDto);
//            // loadallId();
//            loadTableOrd();
//            JOptionPane.showConfirmDialog(null, rasp, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
//        } catch (SQLException e) {
//            JOptionPane.showConfirmDialog(null, "Sss", "Sdd", JOptionPane.ERROR_MESSAGE);
//        } catch (HeadlessException e) {
//            JOptionPane.showConfirmDialog(null, "Sss", "Sdd", JOptionPane.ERROR_MESSAGE);
//        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        amount.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        paymentId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        //deliveryId.setCellValueFactory(new PropertyValueFactory<>("destination"));


        try {
            setCellValues();
          // loadallId();
           loadTableOrd();
           getAllProductId();
           getOrderId();
           getCustId();
           loadPayId();
           loadDelId();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void loadDelId() throws SQLException {
        String id = deliveryBO.getNewDelId();
        System.out.println(id);
        txtDelId.setText(id);
    }

    private void loadPayId() throws SQLException {
        String id = paymentBO.getPaymentId();
        txtPrice1.setText(id);
    }

    private void getCustId() {
        ObservableList<String> oLis = FXCollections.observableArrayList();
        try {
            List<String> aaa = customerBO.getAllCustId();
            oLis.addAll(aaa);
            txtcustId.setItems(oLis);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getOrderId() throws SQLException {
        String id = orderBO.loadNExtID();
        txtOrderId.setText(id);

    }

    private void getAllProductId() {
        ObservableList<String> sdf = FXCollections.observableArrayList();
        try {
            List<String> sd = productBO.getAllProductId();
            sdf.addAll(sd);
            txtProductId.setItems(sdf);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadallId() throws SQLException {
//        String Oid = ordModel.getOID();
//        String PId = ordModel.getPId();
//        String DId = ordModel.getDID();
//        txtOrderId.setText(Oid);
//        txtPrice1.setText(PId);
//        txtDelId.setText(DId);

    }


    public void countOnAction(ActionEvent actionEvent) {
        int count = Integer.parseInt(txtCount.getText());
        String id = txtProductId.getValue();

        double cc = 0.0;
        try {
            cc = productBO.getpriceValues(id);
            txtPrice.setText("" + count*cc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void AddCardOnAction(ActionEvent actionEvent) {

    }

    public void OnActionDelivary(ActionEvent actionEvent) {
        double price = Double.parseDouble(txtPrice.getText());
        double deliveryCharge = Double.parseDouble(txtDelCharge.getText());

        double total = price+deliveryCharge;
        txtTotal1.setText(""+total);
    }

    public void MOuseClick(MouseEvent mouseEvent) {
        txtOrderId.setText(tblOrder.getSelectionModel().getSelectedItem().getOrderId());
        txtdate.setText(tblOrder.getSelectionModel().getSelectedItem().getOrderDate());
        //txtcustId.setText(tblOrder.getSelectionModel().getSelectedItem().getCustomerId());
       // txtProductId.setText(tblOrder.getSelectionModel().getSelectedItem().getProductId());
        txtCount.setText(""+tblOrder.getSelectionModel().getSelectedItem().getQuantity());
        txtPrice.setText(""+tblOrder.getSelectionModel().getSelectedItem().getPrice());

    }

    public void productOnAction(ActionEvent actionEvent) {
        String productId = txtProductId.getValue();
        try{
            int count = productBO.getCount(productId);
            qtyHand.setText(""+count);
            txtProductId.setStyle("-fx-border-color: null");
        } catch (SQLException e) {
            txtProductId.setStyle("-fx-border-color: red");
        }
    }
}