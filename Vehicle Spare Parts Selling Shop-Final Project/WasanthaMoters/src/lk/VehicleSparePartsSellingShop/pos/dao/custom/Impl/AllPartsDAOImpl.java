package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.AllPartsDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllPartsDAOImpl implements AllPartsDAO {


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
        ObservableList <Item>itemlist=FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
       int i=1;
        while (rst.next()){
            itemlist.add(new Item(i++,rst.getString(1),rst.getString(2),rst.getInt(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getDouble(9),rst.getDouble(10)));
        }

        return itemlist;
    }

    @Override
    public ObservableList<Item> Search(String type) throws ClassNotFoundException, SQLException {
        ObservableList <Item>itemlist=FXCollections.observableArrayList();
            ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE VehicleType=?",type);
           int i=1;
            while (rst.next()){
                itemlist.add(new Item(i++,rst.getString(1),rst.getString(2),
                        rst.getInt(3),rst.getString(4),rst.getString(5),
                        rst.getString(6),rst.getString(7),rst.getString(8),
                        rst.getDouble(9),rst.getDouble(10)));
            }

        return itemlist;
    }

    @Override
    public ObservableList<Item> get(String type) throws ClassNotFoundException, SQLException {
        ObservableList <Item>itemavaiable=FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT ModelNo,Description,QtyOnHand FROM Item WHERE QtyOnHand <=10 && VehicleType=?",type);
        while (rst.next()){
            itemavaiable.add(new Item(rst.getString(1),rst.getString(2),rst.getInt(3)));
        }

        return itemavaiable;

    }


}
