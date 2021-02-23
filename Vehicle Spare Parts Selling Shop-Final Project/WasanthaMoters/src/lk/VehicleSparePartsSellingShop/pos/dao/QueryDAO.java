package lk.VehicleSparePartsSellingShop.pos.dao;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO  {
    public String getOrderID() throws SQLException, ClassNotFoundException;
    public ObservableList<Supplier> getSupplierID()throws ClassNotFoundException, SQLException;
    public String getDateDifferent(String CurrentDate, String OrderDate) throws SQLException, ClassNotFoundException;
    public ObservableList<AnnualIncome> getAnnualIncome() throws SQLException, ClassNotFoundException;
    public ObservableList<DailyIncome> getDailyIncome() throws SQLException, ClassNotFoundException;
    public ObservableList<LeastMoviableItem> getLeastMoviableItem() throws SQLException, ClassNotFoundException;
    public ObservableList<MonthlyIncome> getMonthlyIncome() throws SQLException, ClassNotFoundException;
    public ObservableList<MostMoviableItem> getMostMoviableItem() throws SQLException, ClassNotFoundException;
    public ObservableList<PaymentHistory> GetPaymentHistory() throws SQLException, ClassNotFoundException;

}
