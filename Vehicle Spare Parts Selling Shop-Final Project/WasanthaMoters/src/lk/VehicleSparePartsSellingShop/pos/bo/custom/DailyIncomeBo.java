package lk.VehicleSparePartsSellingShop.pos.bo.custom;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.SuperBo;
import lk.VehicleSparePartsSellingShop.pos.dto.DailyIncomeDTO;

import java.sql.SQLException;

public interface DailyIncomeBo extends SuperBo {
    public ObservableList<DailyIncomeDTO>dailyIncome() throws SQLException, ClassNotFoundException;
}
