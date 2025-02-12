package lk.ijse.gdse.sweetdelightfx.demo1.bo;

import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl.*;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.CrudDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl.ProductDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum BOType {
        CUSTOMER,PRODUCT,EMPLOYEE,SUPPLIER,BATCH,RETURN,PAYMENT,DELIVERY,EMPLOYEESALARY,SUPPLIERPAYMENT,ORDER
    }
    public SuperBO getBO(BOType botype) {
        switch (botype){
            case CUSTOMER:
                return new CustomerBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
                case SUPPLIER:
                    return new SupplierBOImpl();
                    case BATCH:
                        return new BatchBOImpl();
                        case RETURN:
                            return new ReturnBOImpl();
                            case PAYMENT:
                                return new PaymentBOImpl();
                                case DELIVERY:
                                    return new DeliveryBOImpl();
                                    case EMPLOYEESALARY:
                                        return new EmployeeSalaryBOImpl();
                                        case SUPPLIERPAYMENT:
                                            return new SupplierPaymentBOImpl();
                                            case ORDER:
                                                return new OrderBOImpl();


            default:
                return null;
        }
    }
}
