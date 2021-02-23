package lk.VehicleSparePartsSellingShop.pos.dao;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.entity.SuperEntity;

import java.io.Serializable;
import java.sql.SQLException;

public interface SuperDAO <T extends SuperEntity,ID extends Serializable>{
    public boolean add(T t)throws ClassNotFoundException, SQLException;
    public boolean delete(ID id)throws ClassNotFoundException, SQLException;
    public boolean update(T t)throws ClassNotFoundException, SQLException;
    public T search(ID id)throws ClassNotFoundException, SQLException;
    public ObservableList<T> getAll()throws ClassNotFoundException, SQLException;
}
