package lk.ijse.Greenery.dao;

import lk.ijse.Greenery.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EMPLOYEE,SUPPLIER,PRODUCT,STOCK,ITEM,ORDER,ORDER_DETAILS,QUERY_DAO,PAYMENT,PO
    }

public SuperDAO getDAO(DAOTypes types) {
        switch (types){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUPPLIER:
                return new CustomerDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
}

}