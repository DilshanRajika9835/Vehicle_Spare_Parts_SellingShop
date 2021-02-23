package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.AnnualIncomeDTO;

public interface AnnualIncomeBo extends SuperBo {
    public ObservableList<AnnualIncomeDTO>getAnnualIncome()throws Exception;
}
