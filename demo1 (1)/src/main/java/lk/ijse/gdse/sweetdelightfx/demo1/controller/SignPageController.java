package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SignDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.SignDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class SignPageController {
    @FXML
    private AnchorPane ancMain;

    @FXML
    private TextField btnConfirmPassword;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private TextField btnEmail;

    @FXML
    private TextField btnPassword;

    @FXML
    private TextField btnUserName;

    SignDAOImpl signModel = new SignDAOImpl();

@FXML
    public void createOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        String userName = btnUserName.getText();
        String email = btnEmail.getText();
        String password = btnPassword.getText();
        String confirmPassword = btnConfirmPassword.getText();

        SignDto signDto = new SignDto(userName, email, password, confirmPassword);

        boolean isSaved = signModel.saveSign(signDto);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Account Created", ButtonType.OK).show();
            ancMain.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            ancMain.getChildren().add(load);
            refreshPage();
        }else{
            new Alert(Alert.AlertType.ERROR, "Account Creation Failed", ButtonType.OK).show();
        }
    }

    private void refreshPage() {
        btnUserName.clear();
        btnEmail.clear();
        btnPassword.clear();
        btnConfirmPassword.clear();


    }



//
//    void btnAddToCartOnAction(ActionEvent event) {
//        String selectedItemId = cmbItemId.getValue();
//
//        if (selectedItemId == null) {
//            new Alert(Alert.AlertType.ERROR, "Please select item..!").show();
//            return;
//        }
//
//        String itemName = lblItemName.getText();
//        int cartQty = Integer.parseInt(txtAddToCartQty.getText());
//        int qtyOnHand = Integer.parseInt(lblItemQty.getText());
//
//
//        if (qtyOnHand < cartQty) {
//            new Alert(Alert.AlertType.ERROR, "Not enough items..!").show();
//            return;
//        }
//
//        txtAddToCartQty.setText("");
//
//        double unitPrice = Double.parseDouble(lblItemPrice.getText());
//        double total = unitPrice * cartQty;
//
//        // Loop through each item in cart's observable list.
//        for (CartTM cartTM : cartTMS) {
//
//            // Check if the item is already in the cart
//            if (cartTM.getItemId().equals(selectedItemId)) {
//                // Update the existing CartTM object in the cart's observable list with the new quantity and total.
//                int newQty = cartTM.getCartQuantity() + cartQty;
//                cartTM.setCartQuantity(newQty); // Add the new quantity to the existing quantity in the cart.
//                cartTM.setTotal(unitPrice * newQty); // Recalculate the total price based on the updated quantity
//
//                // Refresh the table to display the updated information.
//                tblCart.refresh();
//                return; // Exit the method as the cart item has been updated.
//            }
//        }
//
//
//        // Create a "Remove" button for the item to allow it to be removed from the cart later.
//        Button btn = new Button("Remove");
//
//        // If the item does not already exist in the cart, create a new CartTM object to represent it.
//        CartTM newCartTM = new CartTM(
//                selectedItemId,
//                itemName,
//                cartQty,
//                unitPrice,
//                total,
//                btn
//        );
//
//        // Set an action for the "Remove" button, which removes the item from the cart when clicked.
//        btn.setOnAction(actionEvent -> {
//
//            // Remove the item from the cart's observable list (cartTMS).
//            cartTMS.remove(newCartTM);
//
//            // Refresh the table to reflect the removal of the item.
//            tblCart.refresh();
//        });
//
//        // Add the newly created CartTM object to the cart's observable list.
//        cartTMS.add(newCartTM);
//    }




    public void onActionBack(ActionEvent actionEvent) {

    }

    public void SlogOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        ancMain.getChildren().clear();
        ancMain.getChildren().add(load);
    }
}
