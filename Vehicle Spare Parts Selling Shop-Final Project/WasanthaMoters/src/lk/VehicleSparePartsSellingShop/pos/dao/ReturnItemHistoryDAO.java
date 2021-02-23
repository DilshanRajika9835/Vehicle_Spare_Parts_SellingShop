package lk.VehicleSparePartsSellingShop.pos.dao;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.entity.ReturnItemHistory;

import java.sql.SQLException;

public interface ReturnItemHistoryDAO extends SuperDAO<ReturnItemHistory,String> {
    public ObservableList<ReturnItemHistory>getAll() throws SQLException, ClassNotFoundException;
}
