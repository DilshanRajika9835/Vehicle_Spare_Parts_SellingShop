package lk.VehicleSparePartsSellingShop.pos.entity;

public class ReturnItem   implements SuperEntity {
    private String OrderID;
    private double TotalCost;
    private int ReturnQty;
    private double ReturnMoney;
    private String ModelNo;
    private String ReturnDate;
    private String ReturnTime;

    private String Description;
    private String OrderDate;
    private double Discount;
    private int OrderQTY;
    private double UnitPrice;
    private double Cost;

    public ReturnItem() {
    }


    public ReturnItem(String orderID,String modelNo,String description, int orderQTY, String orderDate, double totalCost, double descount,double cost   ) {
        OrderID = orderID;
        TotalCost = totalCost;
        ModelNo = modelNo;
        Description = description;
        OrderDate = orderDate;
        Discount = descount;
        OrderQTY = orderQTY;
        Cost = cost;
    }

    public ReturnItem(String orderID, double totalCost, int returnQty, double returnMoney, String modelNo, String returnDate, String returnTime) {
        OrderID = orderID;
        TotalCost = totalCost;
        ReturnQty = returnQty;
        ReturnMoney = returnMoney;
        ModelNo = modelNo;
        ReturnDate = returnDate;
        ReturnTime = returnTime;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double descount) {
        Discount = descount;
    }

    public int getOrderQTY() {
        return OrderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        OrderQTY = orderQTY;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }
}
