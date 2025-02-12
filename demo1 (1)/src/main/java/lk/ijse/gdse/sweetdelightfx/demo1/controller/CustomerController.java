package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.CustomerDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.CustomerTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.CustomerBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.CustomerDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.db.DBConnection;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Customer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerController implements Initializable {
    public AnchorPane acn23;
    @FXML
    private AnchorPane acnCustomer;

    @FXML
    private TableColumn<CustomerTM, String> address;

    @FXML
    private Button btnCustDelete;

    @FXML
    private Button btnCustSave;

    @FXML
    private Button btnCustUpdate;

    @FXML
    private TableColumn<CustomerTM, String> customerId;

    @FXML
    private TableColumn<CustomerTM, String> email;


    @FXML
    public Label lblcid;

    @FXML
    private TableColumn<CustomerTM, String> name;

    @FXML
    private TableColumn<CustomerTM, String> phone;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtcaddress;

    @FXML
    private TextField txtcemail;

    @FXML
    private TextField txtcname;

    @FXML
    private TextField txtcphone;
    @FXML
    private CustomerDAOImpl customerModel;

    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);



    public CustomerController() {
        customerModel = new CustomerDAOImpl();
    }

    @FXML
    void onActionDelete(ActionEvent event) {
        String id = lblcid.getText();

        Customer customer = new Customer(id,"","","","");

        try{
            boolean rasp = customerBO.delete(customer);
            loadNExtCusId();
           // refeshPage();
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Customer delete...!").show();
                loadTableCustomer();
                clearDetailsCustomer();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Customer...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void onActionUpdate(ActionEvent event) {
        String id = lblcid.getText();
        String name = txtcname.getText();
        String email = txtcemail.getText();
        String address = txtcaddress.getText();
        String phone = txtcphone.getText();

        CustomerDto customerDto = new CustomerDto(id, name, email, address, phone);

        try{
            boolean rasp = customerBO.update(customerDto);
           // refeshPage();
            loadNExtCusId();
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
                loadTableCustomer();
                clearDetailsCustomer();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update Customer...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void clearDetailsCustomer() throws SQLException {

        lblcid.setText("");
        txtcname.setText("");
        txtcemail.setText("");
        txtcaddress.setText("");
        txtcphone.setText("");

        loadNExtCusId();
    }

    private void loadTableCustomer() throws SQLException {
        ArrayList<CustomerDto> customerDtos = customerBO.loadTbl();

        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();


        for (CustomerDto customerDto : customerDtos) {
            CustomerTM customerTM = new CustomerTM(
                   customerDto.getCustomerId(),
                    customerDto.getName(),
                    customerDto.getEmail(),
                    customerDto.getAddress(),
                    customerDto.getPhone()
            );
            customerTMS.add(customerTM);
        }

        tblCustomer.setItems(customerTMS);
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = lblcid.getText();
        String name = txtcname.getText();
        String email = txtcemail.getText();
        String address = txtcaddress.getText();
        String phone = txtcphone.getText();

        CustomerDto customerDto =new CustomerDto(id, name, email,address, phone);

        if (!isValidPhoneNumber(phone)) {
            new Alert(Alert.AlertType.ERROR, "Invalid phone number! Please enter a valid phone number.").show();
            return; // Stop further execution if phone number is invalid
        }

        if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address! Please enter a valid email.").show();
            return; // Stop further execution if email is invalid
        }

        try{
            boolean rasp = customerBO.insert(customerDto);

            //customerOnAction(event);
            loadTableCustomer();
           // refeshPage();
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
               // refeshPage();
                loadNExtCusId();
                loadTableCustomer();
                clearDetailsCustomer();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Customer...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        String regex = "^(\\+\\d{1,3})?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        btnCustDelete.setDisable(true);
        btnCustUpdate.setDisable(true);
        try{
            loadTableCustomer();
          //  refeshPage();
            loadNExtCusId();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    private void loadNExtCusId() throws SQLException {
            String id = customerBO.loadNExtID();
            lblcid.setText(id);


    }


    public void MouseClickCustTable(MouseEvent mouseEvent) {
        btnCustSave.setDisable(true);
        btnCustUpdate.setDisable(false);
        btnCustDelete.setDisable(false);
        txtcname.setText(tblCustomer.getSelectionModel().getSelectedItem().getName());
        txtcaddress.setText(tblCustomer.getSelectionModel().getSelectedItem().getAddress());
        txtcphone.setText(tblCustomer.getSelectionModel().getSelectedItem().getPhone());
        lblcid.setText(tblCustomer.getSelectionModel().getSelectedItem().getCustomerId());
        txtcemail.setText(tblCustomer.getSelectionModel().getSelectedItem().getEmail());

    }

    public void ResetOn(ActionEvent actionEvent) throws SQLException {
        btnCustUpdate.setDisable(true);
        btnCustDelete.setDisable(true);
        btnCustSave.setDisable(false);
        clearDetailsCustomer();
    }

    public void genarateAllCustomerReportOnAction(ActionEvent actionEvent) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/report/customer_report.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
          e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }
}
