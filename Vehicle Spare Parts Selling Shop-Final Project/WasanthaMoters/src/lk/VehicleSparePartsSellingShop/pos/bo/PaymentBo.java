package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.PaymentDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.PaymentHistoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBo  extends SuperBo{
    public ObservableList<PaymentHistoryDTO> paymrntHistory() throws SQLException, ClassNotFoundException;
}
