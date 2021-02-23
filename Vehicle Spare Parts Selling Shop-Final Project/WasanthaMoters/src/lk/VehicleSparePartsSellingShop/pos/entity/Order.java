package lk.VehicleSparePartsSellingShop.pos.entity;

import lk.VehicleSparePartsSellingShop.pos.dto.OrderDetailDTO;

import java.util.ArrayList;

public class Order  implements SuperEntity  {
    private String OrderID;
    private String OrderDate;
    private String OrdrTime;

    public Order(String orderID, String orderDate, String ordrTime) {
        OrderID = orderID;
        OrderDate = orderDate;
        OrdrTime = ordrTime;
    }

    public Order() {
    }


    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrdrTime() {
        return OrdrTime;
    }

    public void setOrdrTime(String ordrTime) {
        OrdrTime = ordrTime;
    }
}
