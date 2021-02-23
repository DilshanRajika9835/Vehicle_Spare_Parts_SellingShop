package lk.VehicleSparePartsSellingShop.pos.dao;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.entity.Login;
import lk.VehicleSparePartsSellingShop.pos.entity.LoginHistory;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO<LoginHistory,String> {
    public ObservableList<LoginHistory> getAll() throws SQLException, ClassNotFoundException;
    public String getNICNumber(String id) throws SQLException, ClassNotFoundException;
    public void AddLoginDetail(Login login) throws SQLException, ClassNotFoundException;

}
