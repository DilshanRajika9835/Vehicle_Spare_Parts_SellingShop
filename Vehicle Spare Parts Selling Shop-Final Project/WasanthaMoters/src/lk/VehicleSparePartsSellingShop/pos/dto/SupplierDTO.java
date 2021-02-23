package lk.VehicleSparePartsSellingShop.pos.dto;

public class SupplierDTO {
    private  int No;
    private String SupplierID;
    private String CompanyName;
    private String SupplierName;
    private String tel;
    private String CompanyAddress;
    private String CompanyEmailAddress;

    public SupplierDTO(int no, String supplierID, String companyName, String supplierName, String tel, String companyAddress, String companyEmailAddress) {
        No = no;
        SupplierID = supplierID;
        CompanyName = companyName;
        SupplierName = supplierName;
        this.tel = tel;
        CompanyAddress = companyAddress;
        CompanyEmailAddress = companyEmailAddress;
    }

    public SupplierDTO() {
    }


    public SupplierDTO(String supplierID) {
        SupplierID = supplierID;
    }

    public SupplierDTO(String supplierID, String companyName, String supplierName, String tel, String companyAddress, String companyEmailAddress) {
        SupplierID = supplierID;
        CompanyName = companyName;
        SupplierName = supplierName;
        this.tel = tel;
        CompanyAddress = companyAddress;
        CompanyEmailAddress = companyEmailAddress;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getCompanyEmailAddress() {
        return CompanyEmailAddress;
    }

    public void setCompanyEmailAddress(String companyEmailAddress) {
        CompanyEmailAddress = companyEmailAddress;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }
}
