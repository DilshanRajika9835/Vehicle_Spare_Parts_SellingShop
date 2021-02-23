package lk.VehicleSparePartsSellingShop.pos.bo.custom;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.SuperBo;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;

public interface AllPartsBo extends SuperBo {
    public ObservableList<ItemDTO> LoadAllData()throws Exception;
    public ObservableList<ItemDTO> getvehicle(String type)throws Exception;
    public ObservableList<ItemDTO> getItemAvailibility(String type)throws Exception;

}
