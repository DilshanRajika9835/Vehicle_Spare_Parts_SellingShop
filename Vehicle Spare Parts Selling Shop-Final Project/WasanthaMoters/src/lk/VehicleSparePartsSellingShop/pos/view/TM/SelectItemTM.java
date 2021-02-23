package lk.VehicleSparePartsSellingShop.pos.view.TM;


import javafx.scene.control.Button;


public class SelectItemTM {
    private String ModelNo;
    private String Description;
    private String Type;
    private double UnitPrice;
    private double Discount;
    private int Qty;
    private double Total;
    private Button btn;

    public SelectItemTM(int qty) {
        Qty = qty;
    }

    public SelectItemTM() {
    }



    public SelectItemTM(String modelNo, String description, String type, double unitPrice, double discount, int qty, double total, Button btn) {
        ModelNo = modelNo;
        Description = description;
        Type = type;
        UnitPrice = unitPrice;
        Discount = discount;
        Qty = qty;
        Total = total;
        this.btn = btn;
    }



    public String getModelNo() {
        return ModelNo;
    }

    public void setModelNo(String modelNo) {
        ModelNo = modelNo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }


}