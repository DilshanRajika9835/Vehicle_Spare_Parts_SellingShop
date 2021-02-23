package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.LeastMoviableItemBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.QueryDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.LeastMoviableItemDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.LeastMoviableItem;

import java.sql.SQLException;

public class LeastMoviableItemBoImpl implements LeastMoviableItemBo {
    QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Query);

    @Override
    public ObservableList<LeastMoviableItemDTO> getLeastMoviableItems() throws SQLException, ClassNotFoundException {
        ObservableList<LeastMoviableItem> all = queryDAO.getLeastMoviableItem();
        ObservableList<LeastMoviableItemDTO>leastmoviableitemlist= FXCollections.observableArrayList();
        for (LeastMoviableItem item:all) {
            leastmoviableitemlist.add(new LeastMoviableItemDTO(item.getNo(),item.getModelno(),item.getDescription(),
                    item.getType(),item.getBrand(),item.getSalse()));

        }
        return leastmoviableitemlist;
    }
}
