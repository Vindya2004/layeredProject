package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmpSalaryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.SupPaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.EmpSalaryTM;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.SupPaymentTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.EmployeeSalaryBO;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.PaymentBO;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.SupplierPaymentBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.EmployeeSalaryDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.PaymentDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.SupplierPaymentDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.EmpSalary;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.SupPayment;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML
    private TextField Supdate;
    @FXML
    private TextField date;

    @FXML
    private TextField payId;

    @FXML
    private Button SaveOnAction;

    @FXML
    private TextField empPay;

    @FXML
    private TextField supPay;

    @FXML
    private TextField Suppayment;

    @FXML
    private Button btnPayDelete;

    @FXML
    private Button btnPayDelete1;

    @FXML
    private Button btnPaySave;

    @FXML
    private Button btnPayUpdate;

    @FXML
    private ComboBox<String> empId;

    @FXML
    private Button btnSDelete;

    @FXML
    private Button btnSReset;

    @FXML
    private Button btnSUpdate;



    @FXML
    private TextField salary;

    @FXML
    private ComboBox<?> supPaymentId;

    @FXML
    private ComboBox<String> supplierId;

    @FXML
    private TableView<EmpSalaryTM> tblEmpSalary;

    @FXML
    private TableView<SupPaymentTM> tblPaymentS;

    @FXML
    private TableColumn<EmpSalaryTM, String> tbldate;

    @FXML
    private TableColumn<EmpSalaryTM, String> tblempId;

    @FXML
    private TableColumn<SupPaymentTM, String> tblpaymentDateS;

    @FXML
    private TableColumn<EmpSalaryTM, String> tblpaymentId;

    @FXML
    private TableColumn<SupPaymentTM, String> tblpaymentIdS;

    @FXML
    private TableColumn<SupPaymentTM, Double> tblpaymentS;

    @FXML
    private TableColumn<EmpSalaryTM, Double> tblsalary;

    @FXML
    private TableColumn<SupPaymentTM, String> tblsupplierIdS;


    private PaymentDAOImpl paymentModel;


    PaymentBO paymentBO= (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    EmployeeSalaryBO employeeSalaryBO = (EmployeeSalaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEESALARY);
    SupplierPaymentBO supplierPaymentBO = (SupplierPaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIERPAYMENT);

    @FXML
    void deleteempOnAction(ActionEvent event) throws SQLException {
        String id= payId.getText();


        double d =0;

        EmpSalary empSalary = new EmpSalary(id,d,null,"");

       try{
           boolean rasp = employeeSalaryBO.delete(empSalary);
           if(rasp){
               new Alert(Alert.AlertType.INFORMATION,"Delete emp salary successfully").show();
               loadTable();
               clearDetailEmpPay();
           }else {
               new Alert(Alert.AlertType.ERROR,"Delete emp salary failed").show();
           }
       }catch(SQLException e){
           System.out.println(e);

       }

    }

    @FXML
    void empResetOnAction(ActionEvent event) throws SQLException {
        btnPaySave.setDisable(false);
        btnPayUpdate.setDisable(true);
        btnPayDelete.setDisable(true);
        clearDetailEmpPay();
    }

    private void clearDetailEmpPay() throws SQLException {
        payId.setText("");
        salary.setText("");
        date.setText("");
        empId.setItems(null);
        refreshs();
        loadDate();
        comboxEmp();
    }

    @FXML
    void saveempOnAction(ActionEvent event) throws SQLException {
        String Pid = payId.getText();
        String ss = empId.getSelectionModel().getSelectedItem().toString();
        double sal = Double.parseDouble(salary.getText());
        String datee = date.getText();

        EmpSalaryDto empSalaryDto = new EmpSalaryDto(Pid,sal,datee,ss);

        boolean rasp = employeeSalaryBO.insert(empSalaryDto);
        if (rasp) {
            new Alert(Alert.AlertType.INFORMATION, "Sucsess...!").show();
            refreshs();
            loadTable();
            clearDetailEmpPay();
//            loadTableEmpSalary();
//            loadNExtDelId();
//            clearDetailsDel();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
        }
    }


    @FXML
    void supDeleteOnAction(ActionEvent event) throws SQLException {
        String id = empPay.getText();

        double d =0;

        SupPayment supPayment = new SupPayment(id,d,null,"");

        boolean rasp = supplierPaymentBO.delete(supPayment);
        if(rasp){
            loadTableSup();
            clearDetailSupPay();
           new Alert(Alert.AlertType.INFORMATION,"Delete suppay successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Delete suppay failed").show();
        }

    }

    @FXML
    void supResetOnAction(ActionEvent event) throws SQLException {
        loadTableSup();
        SaveOnAction.setDisable(false);
        btnSDelete.setDisable(true);
        btnSUpdate.setDisable(true);
        clearDetailSupPay();
    }

    private void clearDetailSupPay() throws SQLException {
        empPay.setText("");
        Suppayment.setText("");
        Supdate.setText("");
        supplierId.setItems(null);
        refreshSup();
        loadDate();
        comboxPay();
    }

    @FXML
    void supSaveOnAction(ActionEvent event) {
        String id = empPay.getText();
        Double pay = Double.parseDouble(Suppayment.getText());
        String datee = Supdate.getText();
        String supId = supplierId.getSelectionModel().getSelectedItem().toString();

        SupPaymentDto supPaymentDto = new SupPaymentDto(id,pay,datee,supId);

        try {
            boolean sup = supplierPaymentBO.insert(supPaymentDto);
            if (sup) {
                loadTableSup();
                refreshSup();
                clearDetailSupPay();
                new Alert(Alert.AlertType.INFORMATION, "Sucsess...!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void supUpdateOnAction(ActionEvent event) throws SQLException {
        String id = empPay.getText();
        Double pay = Double.parseDouble(Suppayment.getText());
        String datee = Supdate.getText();
        String supId = supplierId.getSelectionModel().getSelectedItem().toString();

        SupPaymentDto supPaymentDto = new SupPaymentDto(id,pay,datee,supId);

        boolean rasp = supplierPaymentBO.update(supPaymentDto);
        if (rasp) {
            loadTableSup();
            clearDetailSupPay();
            new Alert(Alert.AlertType.INFORMATION, "Sucsess...!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Fail to update ...!").show();
        }

    }

    @FXML
    void updateempOnAction(ActionEvent event) throws SQLException {
        String Pid = payId.getText();
        String ss = empId.getSelectionModel().getSelectedItem().toString();
        double sal = Double.parseDouble(salary.getText());
        String datee = date.getText();

        EmpSalaryDto empSalaryDto = new EmpSalaryDto(Pid,sal,datee,ss);

        boolean rasp = employeeSalaryBO.update(empSalaryDto);
        if (rasp) {
            new Alert(Alert.AlertType.INFORMATION, "Sucsess...!").show();
            refreshs();
            loadTable();
            clearDetailEmpPay();
//            loadTableEmpSalary();
//            loadNExtDelId();
//            clearDetailsDel();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
        }

    }

    public PaymentController() {
        paymentModel = new PaymentDAOImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnPayUpdate.setDisable(true);
        btnPayDelete.setDisable(true);
        SaveOnAction.setDisable(false);
        tblpaymentId.setCellValueFactory(new PropertyValueFactory<>("payId"));
        tblsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tbldate.setCellValueFactory(new PropertyValueFactory<>("payDate"));
        tblempId.setCellValueFactory(new PropertyValueFactory<>("empId"));

        btnSDelete.setDisable(true);
        btnSUpdate.setDisable(true);
        tblpaymentIdS.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        tblsupplierIdS.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tblpaymentDateS.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        tblpaymentS.setCellValueFactory(new PropertyValueFactory<>("payment"));

        comboxEmp();
        comboxPay();
        try {
            loadDate();
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            refreshs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            loadTableSup();
            refreshSup();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String f1 = LocalDate.now().format(dateTimeFormatter);
        date.setText(f1);
        Supdate.setText(f1);
    }

    private void loadTableSup() {
        SaveOnAction.setDisable(false);
        ObservableList<SupPaymentTM> ddd = FXCollections.observableArrayList();
        try {
            List<SupPaymentDto> dd = supplierPaymentBO.getAllSup();
            for (SupPaymentDto empSalaryDto : dd) {
                SupPaymentTM empSalaryTM = new SupPaymentTM(
                        empSalaryDto.getPaymentId(),
                        empSalaryDto.getPayment(),
                        empSalaryDto.getPaymentDate(),
                        empSalaryDto.getSupplierId()
                );
                ddd.add(empSalaryTM);

            }
            tblPaymentS.setItems(ddd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTable() throws SQLException {
        ArrayList<EmpSalaryDto> empSalaryDtos = employeeSalaryBO.loadTbl();

        ObservableList<EmpSalaryTM> empSalaryTMS = FXCollections.observableArrayList();


        for (EmpSalaryDto empSalaryDto : empSalaryDtos) {
            EmpSalaryTM empSalaryTM = new EmpSalaryTM(
                   empSalaryDto.getPayId(),
                    empSalaryDto.getSalary(),
                    empSalaryDto.getPayDate(),
                    empSalaryDto.getEmpId()
            );
            empSalaryTMS.add(empSalaryTM);
        }

        tblEmpSalary.setItems(empSalaryTMS);

    }

    private void refreshSup() throws SQLException {
        String id = supplierPaymentBO.loadNExtID();
        empPay.setText(id);

    }
//-------------------------------------------------------------------------------------------------------
    private void refreshs() throws SQLException {
        String id = employeeSalaryBO.loadNExtID();
       payId.setText(id);
    }

    private void comboxPay() {
        ObservableList<String > CpayList = FXCollections.observableArrayList();
        try {
            List<String > pay = paymentModel.getAllpayId();
            CpayList.addAll(pay);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        supplierId.setItems(CpayList);
    }


    private void comboxEmp() {
        ObservableList<String > CEMPList = FXCollections.observableArrayList();
        try {
            List<String > EmpS = employeeSalaryBO.getAllEmpId();
            CEMPList.addAll(EmpS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        empId.setItems(CEMPList);

    }

    public void ONMouseClickSup(MouseEvent mouseEvent) {
        SaveOnAction.setDisable(true);
        btnSUpdate.setDisable(false);
        btnSDelete.setDisable(false);
        empPay.setText(tblPaymentS.getSelectionModel().getSelectedItem().getPaymentId());
        Suppayment.setText(""+tblPaymentS.getSelectionModel().getSelectedItem().getPayment());
        Supdate.setText(tblPaymentS.getSelectionModel().getSelectedItem().getPaymentDate());
        String ss =(tblPaymentS.getSelectionModel().getSelectedItem().getSupplierId());
        ObservableList<String > sss = FXCollections.observableArrayList();
        sss.addAll(ss);
        supplierId.setItems(sss);
    }

    public void MouseClickEmp(MouseEvent mouseEvent) {
        btnPaySave.setDisable(true);
        btnPayDelete.setDisable(false);
        btnPayUpdate.setDisable(false);
        payId.setText(tblEmpSalary.getSelectionModel().getSelectedItem().getPayId());
        String ss = (tblEmpSalary.getSelectionModel().getSelectedItem().getEmpId());
        salary.setText(""+tblEmpSalary.getSelectionModel().getSelectedItem().getSalary());
        date.setText(tblEmpSalary.getSelectionModel().getSelectedItem().getPayDate());
        ObservableList<String > sss = FXCollections.observableArrayList();
        sss.addAll(ss);
        empId.setItems(sss);
    }
}
