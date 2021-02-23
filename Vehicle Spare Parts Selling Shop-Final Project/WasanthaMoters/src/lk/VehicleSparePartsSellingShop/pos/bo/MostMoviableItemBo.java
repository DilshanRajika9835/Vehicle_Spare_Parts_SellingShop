package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.MostMoviableItemDTO;

import java.sql.SQLException;

public interface MostMoviableItemBo  extends SuperBo{
    public ObservableList<MostMoviableItemDTO>getMostMoviableItems() throws SQLException, ClassNotFoundException;
}
