package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.ReturnDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.ReturnTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.ReturnBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.ReturnDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.ReturnDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Return;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReturnDetailController implements Initializable {

    @FXML
    private Button btnEmpDelete;
    @FXML
    private Button btnSave;

    @FXML
    private Button btnEmpUpdate;

    @FXML
    private TableColumn<ReturnTM, LocalDate> returnDate;

    @FXML
    private TableColumn<ReturnTM, String> returnId;

    @FXML
    private TableView<ReturnTM> tblReturn;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtReturnId;

    @FXML

    private ReturnDAOImpl returnModel;

    public ReturnDetailController() {
        returnModel = new ReturnDAOImpl();
    }

    ReturnBO returnBO = (ReturnBO) BOFactory.getInstance().getBO(BOFactory.BOType.RETURN);

    @FXML
    void OnActionSave(ActionEvent event) {
        String id = txtReturnId.getText();
        String date = txtReturnDate.getText();

        LocalDate i;
        try {

            i = LocalDate.parse(date);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        ReturnDto returnDto = new ReturnDto(id,i);

        try{
            boolean rasp = returnBO.insert(returnDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Return saved...!").show();
                loadNextId();
                loadTableReturn();
                clearDetailsReturn();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save return...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtReturnId.getText();

        Return returnDetail = new Return(id,null);

        try{
            boolean rasp = returnBO.delete(returnDetail);
            loadNextId();

            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Return delete Sucsess...!").show();
                loadTableReturn();
                clearDetailsReturn();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete return...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }

    }

    private void loadTableReturn() throws SQLException {
        ArrayList<ReturnDto> returnDtos = returnBO.loadTbl();

        ObservableList<ReturnTM> returnTMS = FXCollections.observableArrayList();


        for (ReturnDto returnDto : returnDtos) {
            ReturnTM returnTM = new ReturnTM(
                    returnDto.getRetutnId(),
                    returnDto.getReturnDate()
            );
            returnTMS.add(returnTM);
        }

        tblReturn.setItems(returnTMS);
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtReturnId.getText();
        String date = txtReturnDate.getText();

        LocalDate i;
        try {
            i = LocalDate.parse(date);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        ReturnDto returnDto = new ReturnDto(id,i);

        try{
            boolean rasp = returnBO.update(returnDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Return Update Sucsess...!").show();
                loadNextId();
                loadDate();
                loadTableReturn();
                clearDetailsReturn();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update return...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }




    }

    private void clearDetailsReturn() throws SQLException {
        txtReturnId.setText("");
        txtReturnDate.setText("");
        loadNextId();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnId.setCellValueFactory(new PropertyValueFactory<>("returnId"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        btnEmpDelete.setDisable(true);
        btnEmpUpdate.setDisable(true);

        try{
            loadDate();
            loadTableReturn();
            loadNextId();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void loadNextId() throws SQLException {
        String id = returnBO.loadNExtID();
        txtReturnId.setText(id);
    }

    private void loadDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String f1 = LocalDate.now().format(dateTimeFormatter);
        txtReturnDate.setText(f1);
    }

    public void resetOnAction(ActionEvent actionEvent) throws SQLException {
        btnEmpDelete.setDisable(true);
        btnEmpUpdate.setDisable(true);
        btnSave.setDisable(false);
        clearDetailsReturn();
    }

    public void onClickReturn(MouseEvent mouseEvent) {
        btnEmpDelete.setDisable(false);
        btnEmpUpdate.setDisable(false);
        btnSave.setDisable(true);
        txtReturnId.setText(tblReturn.getSelectionModel().getSelectedItem().getReturnId());
        txtReturnDate.setText(String.valueOf(tblReturn.getSelectionModel().getSelectedItem().getReturnDate()));

    }
}
