package lk.VehicleSparePartsSellingShop.pos.dto;

public class MostMoviableItemDTO {
    private  int No;
    private String ModelNo;
    private String Description;
    private String  Type;
    private String  Brand;
    private int Salse;

    public MostMoviableItemDTO() {
    }

    public MostMoviableItemDTO(int no, String modelNo, String description, String type, String brand, int salse) {
        No = no;
        ModelNo = modelNo;
        Description = description;
        Type = type;
        Brand = brand;
        Salse = salse;
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

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public int getSalse() {
        return Salse;
    }

    public void setSalse(int salse) {
        Salse = salse;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }
}
