package lk.VehicleSparePartsSellingShop.pos.dto;

import java.util.ArrayList;

public class OrderDTO {
    private String OrderID;
    private String OrderDate;
    private String OrderTime;
    private ArrayList<OrderDetailDTO>orderdetillist;
    private  PaymentDTO paymentDTO;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String orderDate, String orderTime, ArrayList<OrderDetailDTO> orderdetillist, PaymentDTO paymentDTO) {
        OrderID = orderID;
        OrderDate = orderDate;
        OrderTime = orderTime;
        this.orderdetillist = orderdetillist;
        this.paymentDTO = paymentDTO;
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

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public ArrayList<OrderDetailDTO> getOrderdetillist() {
        return orderdetillist;
    }

    public void setOrderdetillist(ArrayList<OrderDetailDTO> orderdetillist) {
        this.orderdetillist = orderdetillist;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }
}
