package lk.VehicleSparePartsSellingShop.pos.dto;

public class ItemDTO {
    private  int no;
    private String modelNo;
    private String description;
    private int qtyOnHand;
    private String brand;
    private String color;
    private String type;
    private String width;
    private String height;
    private double unitPrice;
    private double discount;

    public ItemDTO() {
    }

    public ItemDTO(String modelNo, String description, int qtyOnHand, String brand, String color, String type,
                   String width, String height, double unitPrice, double discount) {
        this.setModelNo(modelNo);
        this.setDescription(description);
        this.setQtyOnHand(qtyOnHand);
        this.setBrand(brand);
        this.setColor(color);
        this.setType(type);
        this.setWidth(width);
        this.setHeight(height);
        this.setUnitPrice(unitPrice);
        this.setDiscount(discount);
    }

    public ItemDTO(int no, String modelNo, String description, int qtyOnHand, String brand, String color, String type,
                   String width, String height, double unitPrice, double discount) {
        this.setNo(no);
        this.setModelNo(modelNo);
        this.setDescription(description);
        this.setQtyOnHand(qtyOnHand);
        this.setBrand(brand);
        this.setColor(color);
        this.setType(type);
        this.setWidth(width);;
        this.setHeight(height);
        this.setUnitPrice(unitPrice);
        this.setDiscount(discount);
    }

    public ItemDTO(String modelNo, String description, int qtyOnHand) {
        this.setModelNo(modelNo);
        this.setDescription(description);
        this.setQtyOnHand(qtyOnHand);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
       this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
