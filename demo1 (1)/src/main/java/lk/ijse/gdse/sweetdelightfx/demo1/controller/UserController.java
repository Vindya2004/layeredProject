package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.UserDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.UserTM;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.UserDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableColumn<UserTM, String> password;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtUserEmail;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserPassword;

    @FXML
    private TableColumn<UserTM, String> userId;
    @FXML
    private UserDAOImpl userModel;

    public UserController() {
        userModel = new UserDAOImpl();
    }

    @FXML
    void OnActionSave(ActionEvent event) {
       String id = txtUserId.getText();
       String email = txtUserEmail.getText();
       String password = txtUserPassword.getText();

        UserDto userDto = new UserDto(id, email, password);

        try{
            boolean rasp = userModel.insertUser(userDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "User saved...!").show();
                loadTableUser();
                clearDetailsUser();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save User...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearDetailsUser() {
        txtUserId.setText("");
        txtUserEmail.setText("");
        txtUserPassword.setText("");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtUserId.getText();

        UserDto userDto = new UserDto(id,"","");

        try{
            boolean rasp = userModel.deletetUser(userDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "User delete...!").show();
                loadTableUser();
                clearDetailsUser();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete User...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTableUser() throws SQLException {
        ArrayList<UserDto> userDtos = userModel.loadTblUser();

        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();


        for (UserDto userDto : userDtos) {
            UserTM userTM = new UserTM(
                    userDto.getUserId(),
                    userDto.getEmail(),
                    userDto.getPassword()
            );


            userTMS.add(userTM);
        }

        tblUser.setItems(userTMS);
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtUserId.getText();
        String email = txtUserEmail.getText();
        String password = txtUserPassword.getText();

        UserDto userDto = new UserDto(id, email, password);

        try{
            boolean rasp = userModel.updatetUser(userDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "User update...!").show();
                loadTableUser();
                clearDetailsUser();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update User...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        try {
            loadTableUser();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
