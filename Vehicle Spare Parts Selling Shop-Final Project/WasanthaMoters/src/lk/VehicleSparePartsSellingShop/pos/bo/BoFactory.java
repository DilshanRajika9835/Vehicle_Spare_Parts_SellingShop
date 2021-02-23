package lk.VehicleSparePartsSellingShop.pos.bo;

import lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl.*;
import lk.VehicleSparePartsSellingShop.pos.dao.ReturnItemHistoryDAO;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
public  static  BoFactory getInstance(){
        return boFactory==null?(boFactory=new BoFactory()):boFactory;
}
public enum BoType {
    Allparts,AnnualIncome,Employee,Login,ManageItem,Payment,Order,Return,Supplier,SupplyDetail,
    MonthlyIncome,DailyIncome,LeastMoviable,MostMoviable,ReturnHistory
    }
    public <T extends SuperBo>T getBo(BoType type){
      switch (type){
          case Allparts:return (T)new AllPartsBoImpl();
          case AnnualIncome:return (T)new AnnualIncomeBoImpl();
          case MonthlyIncome:return (T)new MonthlyIncomeBoImpl();
          case DailyIncome:return (T)new DailyIncomeBoImpl();
          case Employee:return (T)new EmployeeBoImpl();
          case Login:return (T)new LoginBoImpl();
          case ManageItem:return (T)new ManageItemBoImpl();
          case Payment:return (T)new PaymentBoImpl();
          case Order:return (T)new OrderBoImpl();
          case Return:return (T)new ReturnBoImpl();
          case Supplier:return (T)new SupplierBoImpl();
          case SupplyDetail:return (T)new SupplyDetailBoImpl();
          case MostMoviable:return (T)new MostMoviableItemBoImpl() ;
          case LeastMoviable:return (T)new LeastMoviableItemBoImpl();
          case ReturnHistory:return (T)new ReturnItemHistoryBoImpl();
          default: return null;
      }

    }
}
