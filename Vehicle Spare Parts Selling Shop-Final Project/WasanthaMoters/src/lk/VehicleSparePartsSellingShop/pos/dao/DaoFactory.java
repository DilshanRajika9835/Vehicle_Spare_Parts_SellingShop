package lk.VehicleSparePartsSellingShop.pos.dao;

import lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return daoFactory==null?daoFactory=new DaoFactory():daoFactory;

    }
    public enum DaoType{
        Allparts,Employee,ManageItem,Supplier,SupplierDetail,
        LoginHistory,payment,Order,Orderdetail,Return,Query,ReturnHistory
    }
     public <T > T getDao(DaoType daoType){
        switch (daoType){
            case Allparts:return (T) new AllPartsDAOImpl();
            case Employee:return (T) new EmployeeDAOImpl();
            case ManageItem:return (T) new ManageItemDAOImpl();
            case Supplier:return (T) new SupplierDAOImpl();
            case SupplierDetail:return (T) new SupplyDetailDAOImpl();
            case LoginHistory:return (T) new LoginDAOImpl();
            case payment:return (T) new PaymentDAOImpl();
            case Order:return (T) new OrderDAOImpl();
            case Orderdetail:return (T) new OrderDetailsDAOImpl();
            case Return:return (T) new ReturnDAOImpl();
            case Query:return (T) new QueryDAOImpl();
            case ReturnHistory:return (T) new ReturnItemHistoryDAOImpl();
            default:return null;
        }
     }
}

