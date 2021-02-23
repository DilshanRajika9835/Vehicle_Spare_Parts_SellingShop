package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.QueryDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QueryDAOImpl implements QueryDAO {
    @Override
    public String getOrderID() throws SQLException, ClassNotFoundException {
        String orderid = "ORD0001";

            ResultSet rst = CrudUtil.execute("SELECT OrderID FROM Orders  ORDER BY OrderID DESC LIMIT 1 ");

            if(rst.next()){
                orderid=rst.getString(1);
                String[] split = orderid.split("ORD");
                int id=Integer.parseInt(split[1])+1;
                if(id<10){
                    orderid="ORD000"+id;
                }else if(id<100){
                    orderid="ORD00"+id;
                }else if(id<1000) {
                    orderid="ORD0"+id;
                }else {
                    orderid="ORD"+id;
                }

            }
        return orderid;

    }

    @Override
    public ObservableList<Supplier> getSupplierID() throws ClassNotFoundException, SQLException {
        ObservableList<Supplier>supplierid= FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT SupplierID FROM Supplier");
        while (rst.next()){
        supplierid.add(new Supplier(rst.getString(1)) );
        }
        return supplierid;
    }

    @Override
    public String getDateDifferent(String CurrentDate, String OrderDate) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT DATEDIFF(?,?)AS Different", CurrentDate, OrderDate);
        if(rst.next()){
            System.out.println(rst.getString(1));
          return rst.getString(1);
        }
        return null;
    }

    @Override
    public ObservableList<AnnualIncome> getAnnualIncome() throws SQLException, ClassNotFoundException {
        ObservableList <AnnualIncome>incomelist= FXCollections.observableArrayList();
        ResultSet rst= CrudUtil.execute("SELECT YEAR(O.OrderDate)as Year,sum(i.UnitPrice*d.OrderQTY-((d.OrderQTY*i" +
                ".UnitPrice)*(i.Descount/100)))as Income FROM Orders O,OrderDetail d,Item i,Payment p WHERE O.OrderID=d.OrderID&&p.OrderID =O.OrderID&&i.ModelNo=d.ModelNo GROUP BY YEAR(O.OrderDate)");
        int No=1;
        while(rst.next()){
            incomelist.add(new AnnualIncome(No++,rst.getString("Year"),rst.getDouble("Income")));

        }

        return  incomelist;
    }

    @Override
    public ObservableList<DailyIncome> getDailyIncome() throws SQLException, ClassNotFoundException {
        ObservableList<DailyIncome>dailyIncome= FXCollections.observableArrayList();
        ResultSet  rst = CrudUtil.execute("SELECT DISTINCT YEAR(o.OrderDate)AS YearName,MONTHNAME(o.OrderDate)AS " +
                "MonthName,DAYNAME(o.OrderDate)as WeekName,day(o.OrderDate)as DayName,SUM(i.UnitPrice*d.OrderQTY-((d" +
                ".OrderQTY*i.UnitPrice)*(i.Descount/100))) AS Descount FROM Orders o,Payment p ,OrderDetail d,Item i  WHERE o.OrderID=d.OrderID&&p.OrderID=d.OrderID &&i.ModelNo=d.ModelNo GROUP BY DAY(o.OrderDate)");
        int No=1;
        while (rst.next()){
            dailyIncome.add(new DailyIncome(No++,rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4),rst.getDouble(5)));

        }
        return dailyIncome;
    }

    @Override
    public ObservableList<LeastMoviableItem> getLeastMoviableItem() throws SQLException, ClassNotFoundException {
        ObservableList<LeastMoviableItem>leastMoviableItemList= FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT DISTINCT i.ModelNo,i.Description,i.VehicleType,i.Brand,COUNT(o.OrderID)AS Salse FROM Item i,Orders o,OrderDetail d WHERE d.ModelNO=i.ModelNO &&o.OrderID=d.OrderID GROUP BY i.ModelNo ORDER BY Salse ASC");
        int No=1;
        while (rst.next()){
            leastMoviableItemList.add(new LeastMoviableItem(No++,rst.getString(1),
                    rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getInt(5)));
        }
        return leastMoviableItemList;
    }

    @Override
    public ObservableList<MonthlyIncome> getMonthlyIncome() throws SQLException, ClassNotFoundException {
        ObservableList<MonthlyIncome>monthlyincome= FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT DISTINCT YEAR(o.OrderDate)AS YearName,MONTHNAME(o.OrderDate)AS " +
                "MonthName,SUM(i.UnitPrice*d.OrderQTY-((d.OrderQTY*i.UnitPrice)*(i.Descount/100))) as Descount FROM " +
                "Orders o,Payment p ,OrderDetail d,Item i  WHERE o.OrderID=d.OrderID &&p.OrderID=d.OrderID &&i.ModelNo=d.ModelNo GROUP  BY MonthName(o.OrderDate)");
        int No=1;
        while (rst.next()){
            monthlyincome.add(new MonthlyIncome (No++,rst.getString(1),rst.getString(2),
                    rst.getDouble(3)));
        }
        return monthlyincome;
    }

    @Override
    public ObservableList<MostMoviableItem> getMostMoviableItem() throws SQLException, ClassNotFoundException {
        ResultSet  rst = CrudUtil.execute("SELECT DISTINCT i.ModelNo,i.Description,i.VehicleType,i.Brand,COUNT(o.OrderID)AS Salse FROM Item i,Orders o,OrderDetail d WHERE d.ModelNo=i.ModelNo && o.OrderID=d.OrderID GROUP BY i.ModelNo ORDER BY Salse DESC");
        ObservableList<MostMoviableItem>mostMoviableItemlist= FXCollections.observableArrayList();
        int No=1;
        while (rst.next()){
            mostMoviableItemlist.add(new MostMoviableItem(No++,rst.getString(1),
                    rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getInt(5)));
        }
        return mostMoviableItemlist;
    }

    @Override
    public ObservableList<PaymentHistory> GetPaymentHistory() throws SQLException, ClassNotFoundException {
        ObservableList <PaymentHistory>paymentlist= FXCollections.observableArrayList();

        ResultSet rst = CrudUtil.execute("SELECT p.PayID,p.OrderID,P.PaymentType,SUM(d.OrderQTY*i.UnitPrice)AS TotalCost,SUM((d.OrderQTY*i.UnitPrice)-(d.OrderQTY*i.UnitPrice)*(i.Descount/100))AS DescountPrice,P.Cash,P.Balance,O.orderDate,O.OrderTime FROM Payment P,Orders O,Item i,OrderDetail d WHERE O.OrderID=p.OrderID && P.OrderID=d.OrderID && d.ModelNo=i.ModelNo&& d.orderID=O.OrderID GROUP BY O.OrderID  Order By O.OrderID DESC, DATE(o.OrderDate)ASC");
        while(rst.next()){
            paymentlist.add(new PaymentHistory(rst.getInt("PayID"),
                    rst.getString("OrderID"),rst.getString("PaymentType"),
                    rst.getDouble("TotalCost"),rst.getDouble("DescountPrice"),
                    rst.getDouble("Cash"),rst.getDouble("Balance"),
                    rst.getString("OrderDate"),rst.getString("OrderTime")));
        }

        return paymentlist;
    }
}
