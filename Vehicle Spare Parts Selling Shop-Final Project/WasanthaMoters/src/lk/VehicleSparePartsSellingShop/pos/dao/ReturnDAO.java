package lk.VehicleSparePartsSellingShop.pos.dao;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.entity.ReturnItem;

import java.sql.SQLException;

public interface ReturnDAO extends SuperDAO<ReturnItem,String> {
    public ObservableList<ReturnItem> Search(String OrderID) throws SQLException, ClassNotFoundException;
    public ObservableList<ReturnItem>  getAll();
    public boolean UpdateStock(int OrderQty,int ReturnQty,String ModelNo,String OrderID) throws SQLException, ClassNotFoundException;
    public void Delete(String ModelNo);
}
