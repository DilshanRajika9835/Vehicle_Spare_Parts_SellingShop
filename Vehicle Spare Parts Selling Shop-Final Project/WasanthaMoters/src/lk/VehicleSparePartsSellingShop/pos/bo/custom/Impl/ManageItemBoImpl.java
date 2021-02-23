package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import lk.VehicleSparePartsSellingShop.pos.bo.ManageItemBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.ManageItemDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Item;

import java.sql.SQLException;

public class ManageItemBoImpl implements ManageItemBo {
    ManageItemDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ManageItem);
    @Override
    public boolean add(ItemDTO dto) throws ClassNotFoundException, SQLException {
        return dao.add(new Item(dto.getModelNo(),dto.getDescription(),dto.getQtyOnHand(),dto.getBrand(),dto.getColor(),
                dto.getType(),dto.getWidth(),dto.getHeight(),dto.getDiscount(),dto.getUnitPrice()));
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return dao.delete(id);
    }

    @Override
    public boolean update(ItemDTO dto) throws ClassNotFoundException, SQLException {
        return dao.update(new Item(dto.getModelNo(),dto.getDescription(),dto.getQtyOnHand(),dto.getBrand(),
                dto.getColor(),dto.getType(),dto.getWidth(),dto.getHeight(),dto.getDiscount(),dto.getUnitPrice()));
    }

    @Override
    public ItemDTO search(String id) throws ClassNotFoundException, SQLException {
        Item item = dao.search(id);
        return new ItemDTO(item.getNo(),item.getModelno(),item.getDescription(),item.getQtyonhand(),item.getBrand(),
                item.getColour(),item.getType(),item.getWidth(),item.getHeight(),item.getDiscount(),item.getUnitPrice());

    }


}
