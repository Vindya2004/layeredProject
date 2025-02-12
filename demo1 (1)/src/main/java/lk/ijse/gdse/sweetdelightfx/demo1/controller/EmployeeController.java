package lk.ijse.gdse.sweetdelightfx.demo1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm.EmployeeTM;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.BOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.EmployeeBO;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.EmployeeDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private TableView<EmployeeTM> tblEmployee;
    @FXML
    private Button btnEmpSave;
    @FXML
    private Button btnEmpUpdate;
    @FXML
    private Button btnEmpDelete;
    @FXML
    private Button btnEmpReset;
    @FXML
    private TextField txtEmpId;
    @FXML
    private TextField txtPNumber;
    @FXML
    private TextField txtSalary;
    @FXML
    private TextField txtEmpName;
    @FXML
    private TableColumn<EmployeeTM,String> employeeId;
    @FXML
    private TableColumn<EmployeeTM,String> employeeName;
    @FXML
    private TableColumn<EmployeeTM,Double> employeeSalary;
    @FXML
    private TableColumn<EmployeeTM,String> employeePhoneNumber;

    EmployeeBO employeeBO =(EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);

   // EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    private EmployeeDAOImpl empModel;

    public EmployeeController() {
        empModel = new EmployeeDAOImpl();
    }



    @FXML
    private void loadTableEmp() throws SQLException {
        ArrayList<EmployeeDto> customerDTOS = employeeBO.loadTbl();

        ObservableList<EmployeeTM> customerTMS = FXCollections.observableArrayList();


        for (EmployeeDto customerDTO : customerDTOS) {
            EmployeeTM customerTM = new EmployeeTM(
                customerDTO.getEmployeeId(),
                    customerDTO.getEmployeeName(),
                    customerDTO.getSalary(),
                    customerDTO.getEmployeePhone()
            );
            customerTMS.add(customerTM);
        }

            tblEmployee.setItems(customerTMS);



    }

    @FXML

    private void OnActionSave(ActionEvent actionEvent) {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String salary = txtSalary.getText();
        String phone = txtPNumber.getText();

        double ss;
        try {
            ss = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        EmployeeDto employeeDto = new EmployeeDto(id, name, ss, phone);

        try{
            boolean rasp = employeeBO.insert(employeeDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Employee saved...!").show();
                loadTableEmp();
                loadNExtEmpId();
                clearDetailsEmp();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Employee...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadNExtEmpId() throws SQLException {
        String id = employeeBO.loadNExtID();
        txtEmpId.setText(id);
    }

    private void clearDetailsEmp() throws SQLException {
        txtEmpId.setText("");
        txtEmpName.setText("");
        txtSalary.setText("");
        txtPNumber.setText("");
        loadNExtEmpId();


    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtEmpId.getText();
        String name = txtEmpName.getText();
        String salary = txtSalary.getText();
        String phone = txtPNumber.getText();

        double ss;
        try {
            ss = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        EmployeeDto employeeDto = new EmployeeDto(id, name, ss, phone);

        try{
            boolean rasp = employeeBO.update(employeeDto);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Employee Update Sucsess...!").show();
                loadTableEmp();
                clearDetailsEmp();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update Employee...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtEmpId.getText();
        double d = 0;
        Employee employee =new Employee(id,"",d,"");

        try{
            boolean rasp = employeeBO.delete(employee);
            if (rasp) {
                new Alert(Alert.AlertType.INFORMATION, "Employee delete Sucsess...!").show();
                loadTableEmp();
                clearDetailsEmp();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Employee...!").show();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
    }

    public void OnActionId(ActionEvent actionEvent) {
        String id=txtEmpId.getText();
    }

    public void OnActionName(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        employeeName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        employeeSalary.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
        employeePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNb"));

        btnEmpDelete.setDisable(true);
        btnEmpUpdate.setDisable(true);

        try {
            loadTableEmp();
            loadNExtEmpId();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void resetOnAction(ActionEvent actionEvent) throws SQLException {
        btnEmpSave.setDisable(false);
        btnEmpUpdate.setDisable(true);
        btnEmpDelete.setDisable(true);
        clearDetailsEmp();
        
    }

    public void onMousetblEmp(MouseEvent mouseEvent) {
        btnEmpSave.setDisable(true);
        btnEmpUpdate.setDisable(false);
        btnEmpDelete.setDisable(false);
        txtEmpId.setText(tblEmployee.getSelectionModel().getSelectedItem().getEmpId());
        txtEmpName.setText(tblEmployee.getSelectionModel().getSelectedItem().getEmpName());
        txtSalary.setText(String.valueOf(tblEmployee.getSelectionModel().getSelectedItem().getEmpSalary()));
        txtPNumber.setText(tblEmployee.getSelectionModel().getSelectedItem().getPhoneNb());
    }
}
