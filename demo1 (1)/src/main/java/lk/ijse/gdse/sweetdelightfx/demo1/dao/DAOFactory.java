package lk.ijse.gdse.sweetdelightfx.demo1.dao;

import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.CustomerDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOType {
        CUSTOMER,PRODUCT,EMPLOYEE,SUPPLIER,BATCH,RETURN,PAYMENT,SUPPLIERPAYMENT,EMPLOYEESALARY,DELIVERY,ORDER,ORDERDETAIL
    }
    public  CrudDAO getDAO(DAOType daotype) {
        switch (daotype){
            case CUSTOMER:
                return new CustomerDAOImpl();
                case PRODUCT:
                    return new ProductDAOImpl();
                    case EMPLOYEE:
                        return new EmployeeDAOImpl();
                        case SUPPLIER:
                            return new SupplierDAOImpl();
                            case BATCH:
                                return new BatchDAOImpl();
                                case RETURN:
                                    return new ReturnDAOImpl();
                                    case PAYMENT:
                                        return new PaymentDAOImpl();
                                        case SUPPLIERPAYMENT:
                                            return new SupplierPaymentDAOImpl();
                                            case EMPLOYEESALARY:
                                                return new EmployeeSalaryDAOImpl();
                                                case DELIVERY:
                                                    return new DeliveryDAOImpl();
                                                    case ORDER:
                                                        return new OrderDAOImpl();
                                                        case ORDERDETAIL:
                                                            return new OrderDetailDAOImpl();
                        default:
                            return null;
        }
    }
}
