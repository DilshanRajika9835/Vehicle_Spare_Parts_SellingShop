package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplierDTO;

import java.sql.SQLException;

public interface SupplierBo extends SuperBo {
    public boolean addSupplier(SupplierDTO supplierDTO)throws ClassNotFoundException, SQLException;
    public boolean deleteSupplier(String id)throws ClassNotFoundException, SQLException;
    public boolean updateSupplier(SupplierDTO t)throws ClassNotFoundException, SQLException;
    public SupplierDTO searchSupplier(String id)throws ClassNotFoundException, SQLException;
    public ObservableList<SupplierDTO> getAllSuppliers()throws ClassNotFoundException, SQLException;
}
