package lk.VehicleSparePartsSellingShop.pos.bo;

import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;

import java.sql.SQLException;

public interface ManageItemBo extends SuperBo {
    public boolean add(ItemDTO dto)throws ClassNotFoundException, SQLException;
    public boolean delete(String id)throws ClassNotFoundException, SQLException;
    public boolean update(ItemDTO dto)throws ClassNotFoundException, SQLException;
    public ItemDTO search(String id)throws ClassNotFoundException, SQLException;

}
