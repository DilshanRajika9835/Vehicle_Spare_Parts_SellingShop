package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.ReturnItemDTO;

import java.sql.SQLException;

public interface ReturnBo extends SuperBo{
    public ObservableList<ReturnItemDTO> getItems(String OrderID) throws SQLException, ClassNotFoundException;
    public boolean AddReturnItem(ReturnItemDTO dto) throws SQLException, ClassNotFoundException;
    public boolean UpdateReturnItem(int OrderQty,int ReturnQty, String ModelNo,String OrderID) throws SQLException, ClassNotFoundException;
    public  String DateDifferent(String CurrentDate,String OrderDate) throws SQLException, ClassNotFoundException;


}
