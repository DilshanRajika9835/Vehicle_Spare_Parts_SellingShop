package lk.VehicleSparePartsSellingShop.pos.bo;

import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo extends SuperBo {
    public ArrayList<ItemDTO> getModelNo() throws SQLException, ClassNotFoundException;
    public ItemDTO Search(String id) throws SQLException, ClassNotFoundException;
    public  boolean placeOrder(OrderDTO order);
    public String getOrderID() throws SQLException, ClassNotFoundException;

}
