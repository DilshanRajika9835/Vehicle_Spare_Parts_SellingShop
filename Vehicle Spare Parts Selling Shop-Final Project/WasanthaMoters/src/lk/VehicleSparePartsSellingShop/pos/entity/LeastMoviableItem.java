package lk.VehicleSparePartsSellingShop.pos.entity;

public class LeastMoviableItem  implements SuperEntity {
    private int no;
    private String modelno;
    private String description;
    private String  type;
    private String  brand;
    private int salse;

    public LeastMoviableItem(int no, String modelno, String description, String type, String brand, int salse) {
        this.no = no;
        this.modelno = modelno;
        this.description = description;
        this.type = type;
        this.brand = brand;
        this.salse = salse;
    }

    public LeastMoviableItem() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSalse() {
        return salse;
    }

    public void setSalse(int salse) {
        this.salse = salse;
    }
}
