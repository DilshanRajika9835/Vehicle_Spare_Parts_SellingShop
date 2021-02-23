package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.SupplyDetailBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.SupplierDAO;
import lk.VehicleSparePartsSellingShop.pos.dao.SupplyDetailDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplierDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplyDetailDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.SupplyDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyDetailBoImpl implements SupplyDetailBo {
    SupplyDetailDAO supplyDetaildao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.SupplierDetail);

    @Override
    public boolean addSupplyDetail(SupplyDetailDTO supplyDetailDTO) throws ClassNotFoundException, SQLException {
     return supplyDetaildao.add(new SupplyDetail(supplyDetailDTO.getSupplierID(),supplyDetailDTO.getModelNo(),
             supplyDetailDTO.getSupplyDate(),supplyDetailDTO.getSupplyTime()));
    }

    @Override
    public boolean DeleteSupplyDetail(String ModelNo, String SupplierID) throws SQLException, ClassNotFoundException {
        return supplyDetaildao.DeleteSupplyDetail(ModelNo,SupplierID);
    }

    @Override
    public boolean updateSupplyDetail(SupplyDetailDTO supplyDetailDTO) throws ClassNotFoundException, SQLException {
        return supplyDetaildao.update(new SupplyDetail(supplyDetailDTO.getSupplierID(),supplyDetailDTO.getModelNo(),
                supplyDetailDTO.getSupplyDate(),supplyDetailDTO.getSupplyTime()));
    }


    @Override
    public ObservableList<SupplyDetailDTO> getAllSupplyDetails() throws ClassNotFoundException, SQLException {
        ObservableList<SupplyDetailDTO>supplydetail= FXCollections.observableArrayList();
        ObservableList<SupplyDetail> all = supplyDetaildao.getAll();
        for (SupplyDetail supply:all) {
            supplydetail.add(new SupplyDetailDTO(supply.getNo(),supply.getSupplierID(),supply.getModelNo(),
                    supply.getSupplyDate(),supply.getSupplyTime()));

        }
        return supplydetail;
    }


}
