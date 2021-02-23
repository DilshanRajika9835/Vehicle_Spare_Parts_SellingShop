package lk.VehicleSparePartsSellingShop.pos.entity;

public class MonthlyIncome  implements SuperEntity  {
    private int No;
    private String YearName;
    private String MonthName;

    public MonthlyIncome(int no, String yearName, String monthName, double income) {
        No = no;
        YearName = yearName;
        MonthName = monthName;
        Income = income;
    }

    private double Income;

    public MonthlyIncome() {
    }



    public String getYearName() {
        return YearName;
    }

    public void setYearName(String yearName) {
        YearName = yearName;
    }

    public String getMonthName() {
        return MonthName;
    }

    public void setMonthName(String monthName) {
        MonthName = monthName;
    }

    public double getIncome() {
        return Income;
    }

    public void setIncome(double income) {
        Income = income;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }
}
