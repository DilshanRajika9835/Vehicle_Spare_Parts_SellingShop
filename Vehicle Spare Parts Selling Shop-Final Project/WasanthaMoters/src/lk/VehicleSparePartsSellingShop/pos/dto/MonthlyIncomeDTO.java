package lk.VehicleSparePartsSellingShop.pos.dto;

public class MonthlyIncomeDTO {
    private  int No;
    private String YearName;
    private String MonthName;

    public MonthlyIncomeDTO(int no, String yearName, String monthName, double income) {
        No = no;
        YearName = yearName;
        MonthName = monthName;
        Income = income;
    }

    private double Income;

    public MonthlyIncomeDTO() {
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
