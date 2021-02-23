package lk.VehicleSparePartsSellingShop.pos.dto;

public class PaymentHistoryDTO {
    private  int PayID;
    private String OrderID;
    private String PaymentType;
    private double Cost;
    private double Discount;
    private double Cash;
    private double Balance;
    private String OrderDate;
    private String OrderTime;

    public PaymentHistoryDTO() {
    }

    public PaymentHistoryDTO(int payID, String orderID, String paymentType, double cost, double discount, double cash, double balance, String orderDate, String orderTime) {
        PayID = payID;
        OrderID = orderID;
        PaymentType = paymentType;
        Cost = cost;
        Discount = discount;
        Cash = cash;
        Balance = balance;
        OrderDate = orderDate;
        OrderTime = orderTime;
    }

    public int getPayID() {
        return PayID;
    }

    public void setPayID(int payID) {
        PayID = payID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getCash() {
        return Cash;
    }

    public void setCash(double cash) {
        Cash = cash;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }
}
