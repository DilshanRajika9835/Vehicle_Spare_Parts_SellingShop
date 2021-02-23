package lk.VehicleSparePartsSellingShop.pos.dao;


import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.entity.Item;

import java.sql.SQLException;

public interface AllPartsDAO extends SuperDAO<Item,String>{
    public ObservableList<Item> Search(String id)throws ClassNotFoundException, SQLException;
    public ObservableList<Item> get(String id)throws ClassNotFoundException, SQLException;

}
