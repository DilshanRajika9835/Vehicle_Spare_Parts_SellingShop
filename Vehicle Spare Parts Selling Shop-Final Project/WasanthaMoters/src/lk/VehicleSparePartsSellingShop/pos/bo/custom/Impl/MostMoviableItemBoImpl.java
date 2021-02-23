package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.MostMoviableItemBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.QueryDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.MostMoviableItemDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.MostMoviableItem;

import java.sql.SQLException;

public class MostMoviableItemBoImpl implements MostMoviableItemBo {
    QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Query);

    @Override
    public ObservableList<MostMoviableItemDTO> getMostMoviableItems() throws SQLException, ClassNotFoundException {
        ObservableList<MostMoviableItem> all = queryDAO.getMostMoviableItem();
        ObservableList<MostMoviableItemDTO>mostMoviableItemList= FXCollections.observableArrayList();
        for (MostMoviableItem item:all) {
            mostMoviableItemList.add(new MostMoviableItemDTO(item.getNo(),item.getModelNo(),item.getDescription(),
                    item.getType(),item.getBrand(),item.getSalse()));

        }
        return mostMoviableItemList;
    }
}
