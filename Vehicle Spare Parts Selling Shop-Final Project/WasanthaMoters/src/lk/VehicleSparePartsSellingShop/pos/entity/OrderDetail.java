package lk.VehicleSparePartsSellingShop.pos.entity;

public class OrderDetail   implements SuperEntity {
    private String OrderID;
    private String ModelNo;
    private int OrderQTy;
    private double Discount;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, String modelNo, int orderQTy, double discount) {
        OrderID = orderID;
        ModelNo = modelNo;
        OrderQTy = orderQTy;
        Discount = discount;
    }




    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getModelNo() {
        return ModelNo;
    }

    public void setModelNo(String modelNo) {
        ModelNo = modelNo;
    }

    public int getOrderQTy() {
        return OrderQTy;
    }

    public void setOrderQTy(int orderQTy) {
        OrderQTy = orderQTy;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }
}
