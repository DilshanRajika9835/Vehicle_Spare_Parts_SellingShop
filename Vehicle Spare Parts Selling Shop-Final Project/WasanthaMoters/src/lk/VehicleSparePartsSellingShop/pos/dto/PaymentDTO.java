package lk.VehicleSparePartsSellingShop.pos.dto;

public class PaymentDTO {
    private String OrderID;
    private String PaymentType;
    private double Cost;
    private double Cash;
    private double Balance;

    public PaymentDTO() {
    }

    public PaymentDTO(String orderID, String paymentType, double cost, double cash, double balance) {

        OrderID = orderID;
        PaymentType = paymentType;
        Cost = cost;
        Cash = cash;
        Balance = balance;
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


}
