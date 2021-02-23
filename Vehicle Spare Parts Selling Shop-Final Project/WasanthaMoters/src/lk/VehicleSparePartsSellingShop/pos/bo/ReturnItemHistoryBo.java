package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.ReturnItemHistoryDTO;

import java.sql.SQLException;

public interface ReturnItemHistoryBo extends SuperBo {
    public ObservableList<ReturnItemHistoryDTO>GetReturnItems() throws SQLException, ClassNotFoundException;
}
