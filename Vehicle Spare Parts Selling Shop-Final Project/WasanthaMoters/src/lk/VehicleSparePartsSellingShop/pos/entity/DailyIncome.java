package lk.VehicleSparePartsSellingShop.pos.entity;

public class DailyIncome  implements SuperEntity  {
    private  int no;
    private String year;
    private String monthname;
    private String weekName;
    private String day;
    private double income;

    public DailyIncome(int no, String year, String monthname, String weekName, String day, double income) {
        this.no = no;
        this.year = year;
        this.monthname = monthname;
        this.weekName = weekName;
        this.day = day;
        this.income = income;
    }

    public DailyIncome() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonthname() {
        return monthname;
    }

    public void setMonthname(String monthname) {
        this.monthname = monthname;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
