package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.SupplierBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.SupplierDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplierDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Supplier;

import java.sql.SQLException;

public class SupplierBoImpl implements SupplierBo {
    SupplierDAO supplierdao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Supplier);

    @Override
    public boolean addSupplier(SupplierDTO dto) throws ClassNotFoundException, SQLException {
       return supplierdao.add(new Supplier(dto.getSupplierID(),dto.getCompanyName(),dto.getSupplierName(),
               dto.getTel(),dto.getCompanyAddress(),dto.getCompanyEmailAddress()));
    }

    @Override
    public boolean deleteSupplier(String id) throws ClassNotFoundException, SQLException {
        return supplierdao.delete(id);
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws ClassNotFoundException, SQLException {
        return supplierdao.update(new Supplier(dto.getSupplierID(),dto.getCompanyName(),
                dto.getSupplierName(),dto.getTel(),dto.getCompanyAddress(),dto.getCompanyEmailAddress()));

    }

    @Override
    public SupplierDTO searchSupplier(String id) throws ClassNotFoundException, SQLException {
        Supplier search = supplierdao.search(id);
        return new SupplierDTO(search.getSupplierID(),search.getCompany(),search.getName(),
                search.getPhoneNumber(),search.getCompanyAddress(),search.getCompanyEmailAddress());

    }

    @Override
    public ObservableList<SupplierDTO> getAllSuppliers() throws ClassNotFoundException, SQLException {
        ObservableList<SupplierDTO>supplierlist= FXCollections.observableArrayList();
        ObservableList<Supplier> all = supplierdao.getAll();
        for (Supplier supplier:all) {
            supplierlist.add(new SupplierDTO(supplier.getNo(),supplier.getSupplierID(),
                    supplier.getCompany(),supplier.getName(),supplier.getPhoneNumber(),supplier.getCompanyAddress(),
                    supplier.getCompanyEmailAddress()));

        }
        return supplierlist;
    }
}
