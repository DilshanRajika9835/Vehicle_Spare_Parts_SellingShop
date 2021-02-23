package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.LoginDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.LoginHistoryDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.LoginHistory;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginBo extends SuperBo {
    public ObservableList<LoginHistoryDTO> getLoginHistory() throws SQLException, ClassNotFoundException;
    public String CheckID(String name) throws SQLException, ClassNotFoundException;
    public void addLoginHistory(LoginDTO login) throws SQLException, ClassNotFoundException;



}
