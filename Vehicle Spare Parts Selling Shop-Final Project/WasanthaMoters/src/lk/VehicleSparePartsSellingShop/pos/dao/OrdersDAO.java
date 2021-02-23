package lk.VehicleSparePartsSellingShop.pos.dao;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.OrderDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Item;
import lk.VehicleSparePartsSellingShop.pos.entity.Order;

import java.sql.SQLException;

public interface OrdersDAO extends SuperDAO<Item,String> {
    public ObservableList<Item> getModelNo() throws SQLException, ClassNotFoundException;
    public Item Search(String id) throws SQLException, ClassNotFoundException;
    public boolean Add(Order order) throws SQLException, ClassNotFoundException;


}
