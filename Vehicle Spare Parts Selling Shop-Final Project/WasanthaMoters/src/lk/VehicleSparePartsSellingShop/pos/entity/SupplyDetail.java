package lk.VehicleSparePartsSellingShop.pos.entity;

public class SupplyDetail implements SuperEntity{
    private  int No;
    private String SupplierID;
    private String ModelNo;
    private String SupplyDate;
    private String SupplyTime;

    public SupplyDetail(String supplierID, String modelNo, String supplyDate, String supplyTime) {
        SupplierID = supplierID;
        ModelNo = modelNo;
        SupplyDate = supplyDate;
        SupplyTime = supplyTime;
    }

    public SupplyDetail(int no, String supplierID, String modelNo, String supplyDate,String supplyTime) {
        No = no;
        SupplierID = supplierID;
        ModelNo = modelNo;
        SupplyDate = supplyDate;
        SupplyTime = supplyTime;
    }



    public SupplyDetail() {
    }


    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public String getModelNo() {
        return ModelNo;
    }

    public void setModelNo(String modelNo) {
        ModelNo = modelNo;
    }

    public String getSupplyDate() {
        return SupplyDate;
    }

    public void setSupplyDate(String supplyDate) {
        SupplyDate = supplyDate;
    }

    public String getSupplyTime() {
        return SupplyTime;
    }

    public void setSupplyTime(String supplyTime) {
        SupplyTime = supplyTime;
    }
}
