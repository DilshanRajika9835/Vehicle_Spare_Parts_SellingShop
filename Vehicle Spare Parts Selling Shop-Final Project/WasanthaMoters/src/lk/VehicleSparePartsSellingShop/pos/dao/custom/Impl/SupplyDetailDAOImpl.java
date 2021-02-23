package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.SupplyDetailDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplierDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Supplier;
import lk.VehicleSparePartsSellingShop.pos.entity.SupplyDetail;
import lk.VehicleSparePartsSellingShop.pos.view.TM.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyDetailDAOImpl implements SupplyDetailDAO {
    @Override
    public boolean add(SupplyDetail supplyDetail) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("INSERT INTO SupplyDetail VALUES(?,?,?,?)",
                    supplyDetail.getSupplierID(),supplyDetail.getModelNo(),supplyDetail.getSupplyDate(),
                    supplyDetail.getSupplyTime());
    }

    @Override
    public boolean delete(String modelno) throws SQLException, ClassNotFoundException {
           return false;
    }

    @Override
    public boolean update(SupplyDetail supplyDetail) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("UPDATE SupplyDetail SET ModelNo=? WHERE SupplierID=?"
                    ,supplyDetail.getModelNo(),supplyDetail.getSupplierID());
    }

    @Override
    public SupplyDetail search(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<SupplyDetail> getAll() throws ClassNotFoundException, SQLException {
            ObservableList<SupplyDetail>supplierlist=FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM SupplyDetail");
        int i=1;
        while (rst.next()){
            supplierlist.add(new SupplyDetail(i++,rst.getString(1),
                    rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return supplierlist;

    }


    @Override
    public boolean DeleteSupplyDetail(String ModelNo, String SupplierID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM SupplyDetail WHERE ModelNo=?|| SupplierID=?",
                ModelNo,SupplierID);

    }
}
