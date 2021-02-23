package lk.VehicleSparePartsSellingShop.pos.entity;

public class Employee  implements SuperEntity{
    private  int no;
   private String nicnumber;
    private String name;
    private String address;
    private String  gender;
    private String birthday;
    private String phonenumber;
    private String  post;
    private double salary;

    public Employee() {
    }

    public Employee(String nicnumber, String name, String address, String gender,
                    String birthday, String phonenumber, String post, double salary) {
        this.nicnumber = nicnumber;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.phonenumber = phonenumber;
        this.post = post;
        this.salary = salary;
    }

    public Employee(int no, String nicnumber, String name, String address, String gender, String birthday,
                    String phonenumber, String post, double salary) {
        this.no = no;
        this.nicnumber = nicnumber;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.phonenumber = phonenumber;
        this.post = post;
        this.salary = salary;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNicnumber() {
        return nicnumber;
    }

    public void setNicnumber(String nicnumber) {
        this.nicnumber = nicnumber;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
