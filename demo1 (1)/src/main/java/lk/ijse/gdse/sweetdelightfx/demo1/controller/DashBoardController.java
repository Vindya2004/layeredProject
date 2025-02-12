package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController {
    public AnchorPane acn2;
    @FXML
    private AnchorPane acnDashboard;
    public Label lbl1;
    public AnchorPane acn1;
    public Button btanEmployee;
    public Button btnCustomer;
    public Button btnPayment;
    public Button btnDelivery;
    public Button btnPCatogary;
    public Button btnOrderDetail;
    public Button btnInventory;
    public Button btnReturn;
    public Button btnProduct;
    public Button btnOrders;
    public Button btnSupplier;
    public Button btnBatch;
    public Button btnInventorySupplier;
    public Button btnContain;
    public Button btnOrderBatch;
    public Button btnUser;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTO("/view/Customer.fxml");
    }
    public void customerOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Customer.fxml");
    }


    public void employeeOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Employee.fxml");
    }

    public void paymentOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Payment.fxml");
    }

    public void DeliveryOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Delivery.fxml");
    }

    public void pCatogaryOnAction(ActionEvent actionEvent) {
        navigateTO("/view/ProductCategory.fxml");

    }

    public void orderDetailsOnAction(ActionEvent actionEvent) {
        navigateTO("/view/OrderDetail.fxml");
    }

    public void inventoryOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Inventory.fxml");
    }

    public void returnOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Return.fxml");
    }

    public void productOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Product.fxml");
    }

    public void ordersOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Order.fxml");
    }

    public void supplierOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Supplier.fxml");
    }

    public void batchOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Batch.fxml");
    }

    public void inventorySupplierOnAction(ActionEvent actionEvent) {
        navigateTO("/view/InventorySupplier.fxml");
    }

    public void containOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Contain.fxml");
    }
    private void navigateTO(String fxmlPath) {
        try {
            acn1.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            acn1.getChildren().add(load);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Fail to load ui").show();
        }
    }

    public void OrderBatchOnAction(ActionEvent actionEvent) {
        navigateTO("/view/Order_batch.fxml");
    }

    public void userOnAction(ActionEvent actionEvent) {
        navigateTO("/view/User.fxml");
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        acnDashboard.getChildren().clear();
        acnDashboard.getChildren().add(load);
    }
}
