package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.ReturnItemHistoryDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.ReturnItemHistory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnItemHistoryDAOImpl implements ReturnItemHistoryDAO {
    @Override
    public boolean add(ReturnItemHistory returnItemHistory) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(ReturnItemHistory returnItemHistory) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public ReturnItemHistory search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<ReturnItemHistory> getAll() throws SQLException, ClassNotFoundException {
         ObservableList<ReturnItemHistory> historylist= FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM ReturnItem");
        while (rst.next()){
            historylist.add(new ReturnItemHistory(rst.getString(1),
                    rst.getDouble(2),rst.getInt(3),rst.getDouble(4),
                    rst.getString(5),rst.getString(6),rst.getString(7)));
        }

        return historylist;
    }
}
