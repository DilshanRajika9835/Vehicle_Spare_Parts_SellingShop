package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.custom.AllPartsBo;
import lk.VehicleSparePartsSellingShop.pos.dao.AllPartsDAO;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl.AllPartsDAOImpl;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Item;

import java.sql.SQLException;

public class AllPartsBoImpl implements AllPartsBo {
    AllPartsDAO allpartsdao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Allparts);

    @Override
    public ObservableList<ItemDTO> LoadAllData() throws SQLException, ClassNotFoundException {
        ObservableList<Item> getparts = allpartsdao.getAll();
        ObservableList<ItemDTO>getpartsdto= FXCollections.observableArrayList();
        for (Item item:getparts) {
        getpartsdto.add(new ItemDTO(item.getNo(),item.getModelno(),item.getDescription(),item.getQtyonhand(),
                item.getBrand(),item.getColour(),item.getType(),item.getWidth(),item.getHeight(),item.getDiscount(),
                item.getUnitPrice()));
        }
        return getpartsdto;
    }

    @Override
    public ObservableList<ItemDTO> getvehicle(String type) throws Exception {
        ObservableList<ItemDTO>vehicle= FXCollections.observableArrayList();
        ObservableList<Item> vehicletype = allpartsdao.Search(type);
        for (Item item:vehicletype) {
            vehicle.add(new ItemDTO(item.getNo(),item.getModelno(),item.getDescription(),item.getQtyonhand(),
                    item.getBrand(),item.getColour(),item.getType(),item.getWidth(),item.getHeight(),item.getDiscount(),
                    item.getUnitPrice()));

        }
        return vehicle;
    }

    @Override
    public ObservableList<ItemDTO> getItemAvailibility(String type) throws Exception {
        ObservableList<ItemDTO>StockAvaiable=FXCollections.observableArrayList();
        ObservableList<Item> items = allpartsdao.get(type);
        for (Item stockQty:items) {
          StockAvaiable.add(new ItemDTO(stockQty.getModelno(),stockQty.getDescription(),stockQty.getQtyonhand()));

        }
        return StockAvaiable;
    }
}

