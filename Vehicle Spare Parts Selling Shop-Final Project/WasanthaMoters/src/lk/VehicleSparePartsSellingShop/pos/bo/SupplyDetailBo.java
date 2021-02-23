package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplierDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplyDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplyDetailBo extends SuperBo {
    public boolean addSupplyDetail(SupplyDetailDTO supplyDetailDTO)throws ClassNotFoundException, SQLException;
    public boolean DeleteSupplyDetail(String ModelNo, String SupplierID) throws SQLException, ClassNotFoundException;
    public boolean updateSupplyDetail(SupplyDetailDTO supplyDetailDTO)throws ClassNotFoundException, SQLException;
    public ObservableList<SupplyDetailDTO> getAllSupplyDetails()throws ClassNotFoundException, SQLException;
}
