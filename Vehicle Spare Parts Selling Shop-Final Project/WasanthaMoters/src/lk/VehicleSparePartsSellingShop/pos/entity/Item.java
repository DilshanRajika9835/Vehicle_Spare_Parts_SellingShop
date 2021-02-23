package lk.VehicleSparePartsSellingShop.pos.entity;

public class Item implements SuperEntity{
    private int no;
    private String modelno;
    private String description;
    private int qtyonhand;
    private String brand;
    private String colour;
    private String type;
    private String width;
    private String height;
    private double unitPrice;
    private double discount;

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "modelno='" + modelno + '\'' +
                '}';
    }

    public Item(String modelno, String description, int qtyonhand, String brand, String colour,
                String type, String width, String height, double unitPrice, double discount) {
        this.modelno = modelno;
        this.description = description;
        this.qtyonhand = qtyonhand;
        this.brand = brand;
        this.colour = colour;
        this.type = type;
        this.width = width;
        this.height = height;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public Item(int no, String modelno, String description, int qtyonhand, String brand, String colour,
                String type, String width, String height, double unitPrice, double discount) {
        this.no = no;
        this.modelno = modelno;
        this.description = description;
        this.qtyonhand = qtyonhand;
        this.brand = brand;
        this.colour = colour;
        this.type = type;
        this.width = width;
        this.height = height;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public Item(String modelno, String description, int qtyonhand) {
        this.modelno = modelno;
        this.description = description;
        this.qtyonhand = qtyonhand;
    }

    public Item(String modelno) {
        this.modelno = modelno;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getModelno() {
        return modelno;
    }

    public void setModelno(String modelno) {
        this.modelno = modelno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtyonhand() {
        return qtyonhand;
    }

    public void setQtyonhand(int qtyonhand) {
        this.qtyonhand = qtyonhand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
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
