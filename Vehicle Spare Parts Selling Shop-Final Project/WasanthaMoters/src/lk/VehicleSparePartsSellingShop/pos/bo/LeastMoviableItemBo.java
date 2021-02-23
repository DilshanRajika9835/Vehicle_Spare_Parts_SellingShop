package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.LeastMoviableItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.MostMoviableItemDTO;

import java.sql.SQLException;

public interface LeastMoviableItemBo extends SuperBo {
    public ObservableList<LeastMoviableItemDTO> getLeastMoviableItems() throws SQLException, ClassNotFoundException;
}
