package lk.VehicleSparePartsSellingShop.pos.entity;

public class AnnualIncome  implements SuperEntity  {
    private int no;
    private String year;
    private double income;

    public AnnualIncome() {
    }

    public AnnualIncome(int no, String year, double income) {
        this.no = no;
        this.year = year;
        this.income = income;
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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
