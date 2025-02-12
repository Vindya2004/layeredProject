package lk.ijse.gdse.sweetdelightfx.demo1.controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.CategoryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.CategoryTM;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.CategoryDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductCategoryController implements Initializable {

    @FXML
    private Button btnEmpDelete;

    @FXML
    private Button btnEmpSave;

    @FXML
    private Button btnEmpUpdate;

    @FXML
    private TableColumn<CategoryTM, String> categiryId;

    @FXML
    private TableColumn<CategoryTM, String> categoryName;

    @FXML
    private TableView<CategoryTM> tblCategory;

    @FXML
    private TextField txtCategoryId;

    @FXML
    private TextField txtCategoryName;
    @FXML

    private CategoryDAOImpl categoryModel;

    public ProductCategoryController() {
        categoryModel = new CategoryDAOImpl();
    }

    @FXML
    void OnActionSave(ActionEvent event) {
        String id = txtCategoryId.getText();
        String name = txtCategoryName.getText();

        CategoryDto categoryDto = new CategoryDto(id, name);

        try{
            boolean rasp = categoryModel.insertCategory(categoryDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Category saved...!").show();
                loadTableCat();
                clearDetailsCat();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save category...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTableCat() throws SQLException {
        ArrayList<CategoryDto> categoryDtos = categoryModel.loadTblCat();

        ObservableList<CategoryTM> categoryTMS = FXCollections.observableArrayList();


        for (CategoryDto categoryDto : categoryDtos) {
            CategoryTM categoryTM = new CategoryTM(
                    categoryDto.getCategoryId(),
                    categoryDto.getCategoryName()
            );


            categoryTMS.add(categoryTM);
        }

        tblCategory.setItems(categoryTMS);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtCategoryId.getText();

        CategoryDto categoryDto = new CategoryDto(id,"");

        try{
            boolean rasp = categoryModel.deleteCategory(categoryDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Category delete...!").show();
                loadTableCat();
                clearDetailsCat();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete category...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearDetailsCat() {
        txtCategoryId.setText("");
        txtCategoryName.setText("");
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtCategoryId.getText();
        String name = txtCategoryName.getText();

        CategoryDto categoryDto = new CategoryDto(id, name);

        try{
            boolean rasp = categoryModel.updateCategory(categoryDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Category upadate...!").show();
                loadTableCat();
                clearDetailsCat();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update category...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categiryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        categoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        try {
            loadTableCat();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
