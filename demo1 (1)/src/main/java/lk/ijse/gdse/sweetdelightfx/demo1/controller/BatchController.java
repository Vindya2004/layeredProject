package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.BatchDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.BatchTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.BatchBO;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl.BatchBOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.BatchDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.BatchDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Batch;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BatchController implements Initializable {
    @FXML
    private TableColumn<BatchTM, String> batchId;

    @FXML
    private TableColumn<BatchTM, Double> batchPrice;

    @FXML
    private Button btnBatchDelete;

    @FXML
    private Button btnBatchSave;

    @FXML
    private Button btnBatchUpdate;

    @FXML
    private TableColumn<BatchTM, String> productId;


    @FXML
    private TableView<BatchTM> tblbatch;

    @FXML
    private TextField txtBatchId;

    @FXML
    private TextField txtBatchPrice;

    @FXML
    private TextField txtProductId;
    @FXML

    private BatchDAOImpl batchModel;



    BatchBO batchBO = (BatchBO) BOFactory.getInstance().getBO(BOFactory.BOType.BATCH);

    public BatchController() {
        batchModel = new BatchDAOImpl();
    }


    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtBatchId.getText();

        Double d=0.0;

        Batch batch = new Batch(id,d,"");

        try{
            boolean rasp = batchBO.delete(batch);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Batch delete Sucsess...!").show();
                loadTableBatch();
                clearDetailsBatch();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete batch...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = txtBatchId.getText();
        String price = txtBatchPrice.getText();
        String productId = txtProductId.getText();

        Double d;
        try {
            d = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }


        BatchDto batchDto = new BatchDto(id,d,productId);

        try{
            boolean rasp = batchBO.insert(batchDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Batch saved...!").show();
                    loadTableBatch();
                     loadNextBatchId();
                    clearDetailsBatch();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save batch...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadNextBatchId() throws SQLException {
        String id = batchBO.loadNExtID();
        txtBatchId.setText(id);
    }

    private void clearDetailsBatch() throws SQLException {
        txtBatchId.setText("");
        txtBatchPrice.setText("");
        txtProductId.setText("");
        loadNextBatchId();
    }

    private void loadTableBatch() throws SQLException {
        ArrayList<BatchDto> batchDtos = batchBO.loadTbl();

        ObservableList<BatchTM> batchTMS = FXCollections.observableArrayList();


        for (BatchDto batchDto : batchDtos) {
            BatchTM batchTM = new BatchTM(
                    batchDto.getBatchId(),
                    batchDto.getBatchPrice(),
                    batchDto.getProductId()
            );
            batchTMS.add(batchTM);
        }

        tblbatch.setItems(batchTMS);
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtBatchId.getText();
        String price = txtBatchPrice.getText();
        String productId = txtProductId.getText();

        Double d;
        try {
            d = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        BatchDto batchDto = new BatchDto(id,d,productId);

        try{
            boolean rasp = batchBO.update(batchDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Batch Update Sucsess...!").show();
                loadTableBatch();
                clearDetailsBatch();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update batch...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        batchId.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        batchPrice.setCellValueFactory(new PropertyValueFactory<>("batchPrice"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));

        btnBatchDelete.setDisable(true);
        btnBatchUpdate.setDisable(true);

        try{
            loadTableBatch();
            loadNextBatchId();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void resetOnAction(ActionEvent actionEvent) throws SQLException {
        btnBatchDelete.setDisable(true);
        btnBatchSave.setDisable(false);
        btnBatchUpdate.setDisable(true);
        clearDetailsBatch();
    }

    public void tblBatchOnClick(MouseEvent mouseEvent) {
        btnBatchDelete.setDisable(false);
        btnBatchSave.setDisable(true);
        btnBatchUpdate.setDisable(false);
        txtBatchId.setText(tblbatch.getSelectionModel().getSelectedItem().getBatchId());
        txtBatchPrice.setText(tblbatch.getSelectionModel().getSelectedItem().getBatchPrice().toString());
        txtProductId.setText(tblbatch.getSelectionModel().getSelectedItem().getProductId());

    }
}
