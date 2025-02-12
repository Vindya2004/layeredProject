package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.ProductDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.ProductTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.ProductBO;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.SupplierBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.ProductDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.ProductDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
//    @FXML
//    private TableView tblProduct;
    @FXML
    private TableView<ProductTM> tblProduct;
    @FXML
    private Button btnProDelete;

    @FXML
    private Button btnProReset;

    @FXML
    private Button btnProSave;

    @FXML
    private Button btnProUpdate;

    @FXML
    private TableColumn<ProductTM, String>productId;

    @FXML
    private TableColumn<ProductTM, String> productName;

    @FXML
    private TableColumn<ProductTM, Double> productPrice;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProId;

    @FXML
    private TextField txtProName;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtqty;
    @FXML
    private TableColumn<ProductTM, String> inventory;

    @FXML
    private TableColumn<ProductTM, Integer> quantity;

    @FXML
    private TableColumn<ProductTM, String> supplierId;
    @FXML
    private TextField txtInventory;

    @FXML


    private ProductDAOImpl proModel;

    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
    //ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
   // ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);

    public ProductController(){
        proModel = new ProductDAOImpl();

    }

    private void loadTablePro() throws ClassNotFoundException, SQLException {
        ArrayList<ProductDto> productDTOS = productBO.loadTbl();

        ObservableList<ProductTM> productTMS = FXCollections.observableArrayList();


        for (ProductDto productDTO : productDTOS) {
            ProductTM productTM = new ProductTM(
                    productDTO.getTxtProId(),
                    productDTO.getTxtProName(),

                    productDTO.getTxtPrice(),
                    productDTO.getTxtQty(),
                    productDTO.getTxtInventory(),
                    productDTO.getTxtSupplierId()
            );
            productTMS.add(productTM);
        }

        tblProduct.setItems(productTMS);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtProId.getText();

        double d=0;

        Product product = new Product(id,"",d,1,"","");

        try{
            boolean rasp = productBO.delete(product);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Product delete Sucsess...!").show();
                loadTablePro();
                clearDetailsPro();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Product...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = txtProId.getText();
        String name = txtProName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtqty.getText());
        String inventory =txtInventory.getText();
        String supplierId =txtSupplierId.getText();

        double x = 0;
        int z = 4;

        ProductDto productDto = new ProductDto(id, name ,price,qty,inventory,supplierId);

        try{
            boolean rasp = productBO.insert(productDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Product saved...!").show();
               loadTablePro();
               loadNextPro();
               clearDetailsPro();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Product...!").show();
            }
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            System.out.println(e);
        } catch (ClassNotFoundException e) {
           // throw new RuntimeException(e);
            System.out.println(e);
        }


    }

    private void loadNextPro() throws SQLException {
        String id = productBO.loadNExtID();
        txtProId.setText(id);
    }

    private void clearDetailsPro() throws SQLException {
        txtProId.setText("");
        txtProName.setText("");
        txtPrice.setText("");
        txtqty.setText("");
        txtInventory.setText("");
        txtSupplierId.setText("");
        loadNextPro();
    }


    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtProId.getText();
        String name = txtProName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtqty.getText());
        String inventory =txtInventory.getText();
        String supplierId =txtSupplierId.getText();


        ProductDto productDto = new ProductDto(id, name ,price,qty,inventory,supplierId);

        try{
            boolean rasp = productBO.update(productDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Employee Update Sucsess...!").show();
                loadTablePro();
                clearDetailsPro();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update Employee...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        inventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

        btnProDelete.setDisable(true);
        btnProUpdate.setDisable(true);

        try {
            loadTablePro();
            loadNextPro();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void mouseOntblPro(MouseEvent mouseEvent) {
        btnProSave.setDisable(true);
        btnProUpdate.setDisable(false);
        btnProDelete.setDisable(false);
        txtProId.setText(tblProduct.getSelectionModel().getSelectedItem().getProductId());
        txtProName.setText(tblProduct.getSelectionModel().getSelectedItem().getProductName());
        txtPrice.setText(String.valueOf(tblProduct.getSelectionModel().getSelectedItem().getProductPrice()));
        txtqty.setText(String.valueOf(tblProduct.getSelectionModel().getSelectedItem().getProductQuantity()));
        txtInventory.setText(tblProduct.getSelectionModel().getSelectedItem().getInventory());
        txtSupplierId.setText(tblProduct.getSelectionModel().getSelectedItem().getSupplierId());
    }

    public void resetOnAction(ActionEvent actionEvent) throws SQLException {
        btnProSave.setDisable(false);
        btnProUpdate.setDisable(true);
        btnProDelete.setDisable(true);
        clearDetailsPro();
    }
}
