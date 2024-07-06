package lk.ijse.Greenery.bo;

import lk.ijse.Greenery.bo.impl.*;
import lk.ijse.Greenery.dao.custom.impl.CustomerDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }


    public enum BOTypes{
        CUSTOMER,SUPPLIER,PRODUCT,EMPLOYEE,STOCK,PAYMENT
    }
    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case PRODUCT:
               return new ProductBOImpl();
            case STOCK:
                return new StockBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }

}
