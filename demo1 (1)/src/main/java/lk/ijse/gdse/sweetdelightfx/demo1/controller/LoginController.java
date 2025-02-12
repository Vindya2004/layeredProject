package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.LoginDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.LoginDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public AnchorPane ancLogin;
    public TextField email;
    public TextField password;
    public Button btnLogin;
    public Button btnSign;

    LoginDAOImpl loginModel = new LoginDAOImpl();

    public void loginOnAction(ActionEvent actionEvent) throws IOException, SQLException {
        String Email = email.getText();
        String Password = password.getText();

        LoginDto loginDto = new LoginDto(Email, Password);

        boolean isSaved = loginModel.saveLogin(loginDto);

        if (isSaved) {
            ancLogin.getChildren().clear();
            AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashBoard.fxml")));
            ancLogin.getChildren().add(load);
        }else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }

//        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashBoard.fxml")));
//                 ancLogin.getChildren().clear();
//           ancLogin.getChildren().add(load);
//
//        if(Email.equals("sweetdelight@gmail.com") && Password.equals("123456")){
//
//
//        }else{
//            new Alert(Alert.AlertType.ERROR, "Invalid Email or Password").show();
//        }
    }

    public void signOnAction(ActionEvent actionEvent) throws IOException {
        ancLogin.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SignPage.fxml"));
        ancLogin.getChildren().add(load);
    }

    public void EmailOnAction(ActionEvent actionEvent) {
        password.requestFocus();
    }

    public void PAsswordOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        loginOnAction(actionEvent);
    }
}
