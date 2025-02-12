package lk.ijse.gdse.sweetdelightfx.demo1.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupplierDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.SupplierTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.SupplierBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.SupplierDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Supplier;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SuplierController implements Initializable {
    @FXML
    private Button btnEmpDelete;

    @FXML
    private Button btnEmpSave;

    @FXML
    private Button btnEmpUpdate;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtSupplierPhonenumber;


    @FXML
    private TableColumn<SupplierTM, Integer> phoneNumber;

    @FXML
    private TableColumn<SupplierTM, String> supplierId;

    @FXML
    private TableColumn<SupplierTM, String> supplierName;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML

    private SupplierDAOImpl supModel;

    public SuplierController() {
        supModel = new SupplierDAOImpl();
    }

    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
   // SupplierDAO supplierDao = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @FXML
    void OnActionSave(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String phonenumber = txtSupplierPhonenumber.getText();

        int i;
        try {
            i = Integer.parseInt(phonenumber);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }


        SupplierDto supplierDto = new SupplierDto(id,name,i);

        try{
            boolean rasp = supplierBO.insert(supplierDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier saved...!").show();
                loadTableSup();
                loadNextSup();
                clearDetailsSup();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save supplier...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadNextSup() throws SQLException {
        String id = supplierBO.loadNExtID();
        txtSupplierId.setText(id);
    }

    private void clearDetailsSup() throws SQLException {
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtSupplierPhonenumber.setText("");
        loadNextSup();
    }

    private void loadTableSup() throws SQLException {
        ArrayList<SupplierDto> supplierDTOS = supplierBO.loadTbl();

        ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();


        for (SupplierDto supplierDTO : supplierDTOS) {
            SupplierTM supplierTM = new SupplierTM(
                  supplierDTO.getSupplierId(),
                    supplierDTO.getSupplierName(),
                    supplierDTO.getPhoneNumber()
            );
            supplierTMS.add(supplierTM);
        }

        tblSupplier.setItems(supplierTMS);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        int i=0;

        Supplier supplier = new Supplier(id,"",i);

        try{
            boolean rasp = supplierBO.delete(supplier);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier delete Sucsess...!").show();
                loadTableSup();
                clearDetailsSup();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete supllier...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String phonenumber = txtSupplierPhonenumber.getText();

        int i;
        try{
            i = Integer.parseInt(phonenumber);
        }catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        SupplierDto supplierDto = new SupplierDto(id,name,i);;

        try{
            boolean rasp = supplierBO.update(supplierDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier Update Sucsess...!").show();
                loadTableSup();
                clearDetailsSup();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update supplier...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        btnEmpDelete.setDisable(true);
        btnEmpUpdate.setDisable(true);
        try {
            loadTableSup();
            loadNextSup();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void tblSupOnClick(MouseEvent mouseEvent) {
        btnEmpSave.setDisable(true);
        btnEmpUpdate.setDisable(false);
        btnEmpDelete.setDisable(false);
        txtSupplierId.setText(tblSupplier.getSelectionModel().getSelectedItem().getSupplierId());
        txtSupplierName.setText(tblSupplier.getSelectionModel().getSelectedItem().getSupplierName());
        txtSupplierPhonenumber.setText(String.valueOf(tblSupplier.getSelectionModel().getSelectedItem().getPhoneNumber()));
    }

    public void resetOnAction(ActionEvent actionEvent) throws SQLException {
        btnEmpSave.setDisable(false);
        btnEmpUpdate.setDisable(true);
        btnEmpDelete.setDisable(true);
        clearDetailsSup();
    }
}
