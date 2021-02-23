package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.ManageItemDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageItemDAOImpl implements ManageItemDAO {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
          return CrudUtil.execute("INSERT INTO Item VALUES(?,?,?,?,?,?,?,?,?,?)",
                  item.getModelno(),item.getDescription(),item.getQtyonhand(),item.getBrand(),
                  item.getColour(),item.getType(),item.getWidth(),item.getHeight(),item.getDiscount(),
                  item.getUnitPrice());


    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

            return CrudUtil.execute("DELETE FROM Item WHERE ModelNo =?",id);
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
           return CrudUtil.execute("UPDATE Item  SET Description=?,QtyOnHand=?,Brand=?,Color=?,VehicleType=?,Width=?," +
                           "Height=?,Descount=?,UnitPrice=?WHERE ModelNo=?",
                   item.getDescription(), item.getQtyonhand(),item.getBrand(),item.getColour(),
                   item.getType(),item.getWidth(),item.getHeight(),item.getDiscount(),item.getUnitPrice(),
                   item.getModelno());

    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
            int i=1;
            ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE ModelNo=?", id);
            if(rst.next()){
            return new Item(i++,rst.getString(1),rst.getString(2),
                    rst.getInt(3),rst.getString(4),rst.getString(5),
                    rst.getString(6),rst.getString(7),rst.getString(8),
                    rst.getDouble(9),rst.getDouble(10));
            }
            return null;

    }

    @Override
    public ObservableList<Item> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }


}
