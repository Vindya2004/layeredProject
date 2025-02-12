package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.InventoryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.InventoryTM;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.InventoryDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class InventoryController implements Initializable {
    @FXML
    private Button btnInveDelete;

    @FXML
    private Button btnInveSave;

    @FXML
    private Button btnInveUpdate;

    @FXML
    private TableColumn<InventoryTM, String> inventoryId;

    @FXML
    private TableColumn<InventoryTM, Integer> quantity;

    @FXML
    private TableView<InventoryTM> tblInventory;

    @FXML
    private TextField txtInveId;

    @FXML
    private TextField txtInveQuantity;

    private InventoryDAOImpl inveModel;

    public InventoryController(){
        inveModel = new InventoryDAOImpl();
    }


    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtInveId.getText();

        int i=0 ;
       InventoryDto inventoryDto = new InventoryDto(id,i);

        try{
            boolean rasp = inveModel.deleteInventory(inventoryDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Inventory delete Sucsess...!").show();
                loadTableInve();
                clearDetailsInve();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete inventory...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }

    }

    private void loadTableInve() throws SQLException {
        ArrayList<InventoryDto> inventoryDtos = inveModel.loadTblinve();

        ObservableList<InventoryTM> inventoryTMS = FXCollections.observableArrayList();


        for (InventoryDto inventoryDto : inventoryDtos) {
            InventoryTM inventoryTM = new InventoryTM(
                    inventoryDto.getInventoryId(),
                    inventoryDto.getQuantity()
            );

            inventoryTMS.add(inventoryTM);
        }

        tblInventory.setItems(inventoryTMS);
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = txtInveId.getText();
        String quantity = txtInveQuantity.getText();

        int i;
        try {
            i = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        InventoryDto inventoryDto =new InventoryDto(id,i);

        try{
            boolean rasp = inveModel.insertInventory(inventoryDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Inventory saved...!").show();
                loadTableInve();
                clearDetailsInve();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save inventory...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtInveId.getText();
        String quantity = txtInveQuantity.getText();

        int i;
        try {
            i = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
       InventoryDto inventoryDto =new InventoryDto(id,i);

        try{
            boolean rasp = inveModel.updateInventory(inventoryDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Inventory Update Sucsess...!").show();
                 loadTableInve();
                 clearDetailsInve();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update inventory...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }

    }

    private void clearDetailsInve() {
        txtInveId.setText("");
        txtInveQuantity.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventoryId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        try {
            loadTableInve();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
