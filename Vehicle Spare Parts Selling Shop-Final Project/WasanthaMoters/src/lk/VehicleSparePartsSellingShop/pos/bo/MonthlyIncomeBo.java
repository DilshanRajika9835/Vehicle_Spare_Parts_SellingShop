package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.AnnualIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.MonthlyIncomeDTO;

public interface MonthlyIncomeBo  extends SuperBo{
    public ObservableList<MonthlyIncomeDTO> getMonthlyIncome()throws Exception;

}
