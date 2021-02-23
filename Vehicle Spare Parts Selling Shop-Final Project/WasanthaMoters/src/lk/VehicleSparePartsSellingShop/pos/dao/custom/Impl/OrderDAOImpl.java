package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.OrdersDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.Item;
import lk.VehicleSparePartsSellingShop.pos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrdersDAO {
    @Override
    public ObservableList<Item> getModelNo() throws SQLException, ClassNotFoundException {
        ObservableList<Item> modellist = FXCollections.observableArrayList();

            ResultSet rst = CrudUtil.execute("SELECT ModelNo FROM Item");
            while (rst.next()) {
                modellist.add(new Item(rst.getString(1)));
            }

        return modellist;
    }

    @Override
    public Item Search(String id) throws SQLException, ClassNotFoundException {
            ResultSet srt = CrudUtil.execute("SELECT * FROM Item WHERE ModelNo =?", id);
            if (srt.next()) {
                return new Item(srt.getString(1), srt.getString(2),
                        srt.getInt(3), srt.getString(4), srt.getString(5),
                        srt.getString(6), srt.getString(7), srt.getString(8),
                        srt.getDouble(9), srt.getDouble(10));
            }

        return null;
    }

    @Override
    public boolean Add(Order order) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("INSERT INTO Orders VALUES(?,?,?)",order.getOrderID(),order.getOrderDate(),
                    order.getOrdrTime());
    }


    @Override
    public boolean add(Item item) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Item item) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Item search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Item> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
