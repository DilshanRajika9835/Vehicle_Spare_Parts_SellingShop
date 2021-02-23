package lk.VehicleSparePartsSellingShop.pos.entity;

public class ReturnItemHistory   implements SuperEntity {
    private String OrderID;
    private double TotalCost;
    private int ReturnQty;
    private double ReturnMoney;
    private String ModelNo;
    private String ReturnDate;
    private String ReturnTime;

    public ReturnItemHistory(String orderID, double totalCost, int returnQty, double returnMoney, String modelNo, String returnDate, String returnTime) {
        OrderID = orderID;
        TotalCost = totalCost;
        ReturnQty = returnQty;
        ReturnMoney = returnMoney;
        ModelNo = modelNo;
        ReturnDate = returnDate;
        ReturnTime = returnTime;
    }

    public ReturnItemHistory() {
    }


    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }

    public int getReturnQty() {
        return ReturnQty;
    }

    public void setReturnQty(int returnQty) {
        ReturnQty = returnQty;
    }

    public double getReturnMoney() {
        return ReturnMoney;
    }

    public void setReturnMoney(double returnMoney) {
        ReturnMoney = returnMoney;
    }

    public String getModelNo() {
        return ModelNo;
    }

    public void setModelNo(String modelNo) {
        ModelNo = modelNo;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(String returnTime) {
        ReturnTime = returnTime;
    }
}
