package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.DeliveryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.DeliveryTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.DeliveryBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.DeliveryDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.DeliveryDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Delivery;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DeliveryController implements Initializable {

    @FXML
    private Button delDelete;

    @FXML
    private Button delReset;

    @FXML
    private Button delSave;

    @FXML
    private Button delUpdate;

    @FXML
    private TableColumn<DeliveryTM, String> deliveryDate;

    @FXML
    private TableColumn<DeliveryTM, String> deliveryId;

    @FXML
    private TableColumn<DeliveryTM, String> destination;
    @FXML
    private TableView<DeliveryTM> tableDel;

    @FXML
    private TextField txtDelDate;

    @FXML
    private TextField txtDelId;

    @FXML
    private TextField txtDestination;

    @FXML

    private DeliveryDAOImpl delmodel;

//    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DELIVERY);

    DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getInstance().getBO(BOFactory.BOType.DELIVERY);

    public DeliveryController() {
        delmodel = new DeliveryDAOImpl();
    }


    private void loadTableDelivery() throws SQLException {

        ArrayList<DeliveryDto> deliveryDtos = deliveryBO.loadTbl();

        ObservableList<DeliveryTM> deliveryTMS = FXCollections.observableArrayList();


        for (DeliveryDto deliveryDto : deliveryDtos) {
            DeliveryTM deliveryTM = new DeliveryTM(
                    deliveryDto.getDeliveryId(),
                    deliveryDto.getDeleveryDate(),
                    deliveryDto.getDestination()
            );
            deliveryTMS.add(deliveryTM);
        }

        tableDel.setItems(deliveryTMS);
    }


    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException {
        String id = txtDelId.getText();

        Delivery delivery = new Delivery(id,"","",0);

        boolean rasp = deliveryBO.delete(delivery);
        if (rasp) {
            new Alert(Alert.AlertType.INFORMATION, "Delivery delete Sucsess...!").show();
            loadTableDelivery();
            clearDetailsDel();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to delete delivery...!").show();
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException {
        String id = txtDelId.getText();
        String date = txtDelDate.getText();
        String destination = txtDestination.getText();

        DeliveryDto deliveryDto = new DeliveryDto(id,date,destination,0);

        boolean rasp = deliveryBO.insert(deliveryDto);
        if (rasp) {
            new Alert(Alert.AlertType.INFORMATION, "Delivery saved...!").show();
            loadTableDelivery();
            loadNExtDelId();
            clearDetailsDel();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Delivery...!").show();
        }
    }

    private void loadNExtDelId() throws SQLException {
        String id = delmodel.loadNExtID();
        txtDelId.setText(id);
    }


    private void clearDetailsDel() {
        txtDelDate.setText("");
        txtDelId.setText("");
        txtDestination.setText("");
    }


    @FXML
    void updateOnAction(ActionEvent event) throws SQLException {
        String id = txtDelId.getText();
        String date = txtDelDate.getText();
        String destination = txtDestination.getText();

        DeliveryDto deliveryDto = new DeliveryDto(id,date,destination,0);

        boolean rasp = deliveryBO.update(deliveryDto);
        if (rasp) {
            new Alert(Alert.AlertType.INFORMATION, "Delivery Update Sucsess...!").show();
            loadTableDelivery();
            clearDetailsDel();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to Update delivery...!").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deliveryId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));

        try {
            loadTableDelivery();
            //loadNExtDelId();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void resetOnAction(ActionEvent actionEvent) {
        delSave.setDisable(false);
        delUpdate.setDisable(true);
        delDelete.setDisable(true);
        clearDetailsDel();
    }

    public void mouseClickDelTbl(MouseEvent mouseEvent) {
        delSave.setDisable(true);
        delUpdate.setDisable(false);
        delDelete.setDisable(false);
        txtDelId.setText(tableDel.getSelectionModel().getSelectedItem().getDeliveryId());
        txtDelDate.setText(tableDel.getSelectionModel().getSelectedItem().getDeliveryDate());
        txtDestination.setText(tableDel.getSelectionModel().getSelectedItem().getDestination());

    }
}
