package lk.VehicleSparePartsSellingShop.pos.dto;

public class EmployeeDTO {
    private  int No;
    private String nic;
    private String name;
    private String address;
    private String gender;
    private String birthDay;
    private String phoneNumber;
    private String post;
    private double salary;

    public EmployeeDTO(String nic, String name, String address, String gender, String birthDay, String phoneNumber,
                       String post, double salary) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.post = post;
        this.salary = salary;
    }

    public EmployeeDTO(int no, String nic, String name, String address, String gender, String birthDay,
                       String phoneNumber, String post, double salary) {
        No = no;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.post = post;
        this.salary = salary;
    }

    public EmployeeDTO() {
    }


    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
