package lk.VehicleSparePartsSellingShop.pos.entity;

public class Supplier implements SuperEntity {
    private  int No;
    private String SupplierID;
    private String Company;
    private String Name;
    private String PhoneNumber;
    private String CompanyAddress;
    private String CompanyEmailAddress;

    public Supplier(int no, String supplierID, String company, String name, String phoneNumber, String companyAddress, String companyEmailAddress) {
        No = no;
        SupplierID = supplierID;
        Company = company;
        Name = name;
        PhoneNumber = phoneNumber;
        CompanyAddress = companyAddress;
        CompanyEmailAddress = companyEmailAddress;
    }

    public Supplier() {
    }

    public Supplier(String supplierID) {
        SupplierID = supplierID;
    }

    public Supplier(String supplierID, String Company, String Name, String PhoneNumber, String companyAddress, String companyEmailAddress) {
        this. SupplierID = supplierID;
        this.Company = Company;
        this. Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.CompanyAddress = companyAddress;
        this.CompanyEmailAddress = companyEmailAddress;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this. CompanyAddress = companyAddress;
    }

    public String getCompanyEmailAddress() {
        return CompanyEmailAddress;
    }

    public void setCompanyEmailAddress(String companyEmailAddress) {

        this.CompanyEmailAddress = companyEmailAddress;
    }


    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }
}
