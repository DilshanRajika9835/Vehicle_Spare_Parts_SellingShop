package lk.VehicleSparePartsSellingShop.pos.dao;

import lk.VehicleSparePartsSellingShop.pos.entity.SupplyDetail;
import java.sql.SQLException;

public interface SupplyDetailDAO extends SuperDAO<SupplyDetail,String> {
    public boolean DeleteSupplyDetail(String ModelNo, String SupplierID) throws SQLException, ClassNotFoundException;
}