package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.ReturnDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.ReturnItemDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.ReturnItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnDAOImpl implements ReturnDAO {
    @Override
    public ObservableList<ReturnItem> Search(String OrderID) throws SQLException, ClassNotFoundException {
        ObservableList<ReturnItem>orderlist= FXCollections.observableArrayList();
            ResultSet rst= CrudUtil.execute("SELECT O.OrderID,i.ModelNo,i.Description,d.OrderQty,o.OrderDate,SUM(i" +
                    ".UnitPrice*d.OrderQty)AS TotalCost,SUM((d.OrderQty*i.UnitPrice)*(i.Descount/100)) AS Descount," +
                    "SUM((i.UnitPrice*d.OrderQty)-((d.OrderQty*i.UnitPrice)*(i.Descount/100)))AS DescountPrice FROM " +
                    "Orders o,Item i,Payment p,OrderDetail d WHERE o.OrderID=?&& o.OrderID=d.OrderID && p.OrderID=d.OrderID &&i.ModelNo=d.ModelNo GROUP BY i.ModelNo",OrderID);
            while (rst.next()){
             orderlist.add(new ReturnItem(rst.getString(1),rst.getString(2),
                     rst.getString(3),rst.getInt(4),rst.getString(5),
                     rst.getDouble(6),rst.getDouble(7),rst.getDouble(8)));
            }
        return orderlist;

            }

    @Override
    public boolean add(ReturnItem returnItem) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO ReturnItem (OrderID,TotalCost,ReturnQty,ReturnMoney,ModelNo,ReturnDate,ReturnTime)VALUES(?,?,?,?,?,?,?)",
                returnItem.getOrderID(),returnItem.getTotalCost(),returnItem.getReturnQty(),
                returnItem.getReturnMoney(),returnItem.getModelNo(),returnItem.getReturnDate(),
                returnItem.getReturnTime());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(ReturnItem returnItem) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public ReturnItem search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<ReturnItem> getAll( ) {
   return null;
    }


    @Override
    public boolean UpdateStock(int OrderQty,int RetuenQty, String ModelNo,String OrderID) throws SQLException, ClassNotFoundException {

            if(OrderQty!= RetuenQty){
                int qty=OrderQty-RetuenQty;
        boolean updated= CrudUtil.execute("UPDATE OrderDetail SET OrderQty=? WHERE ModelNo=?&& OrderID=?", qty, ModelNo,OrderID);
                if(updated){
                    ResultSet rst= CrudUtil.execute("SELECT QtyOnHand FROM Item WHERE ModelNo=?", ModelNo);
                    if(rst.next()){
                        int qtyonhand=rst.getInt("QtyOnHand");
                        int newQtyOnhand=qtyonhand+RetuenQty;
                        return CrudUtil.execute("UPDATE  Item SET QtyOnHand=? WHERE ModelNo=?", newQtyOnhand, ModelNo);
                    }
                }
            }else {
                ResultSet rst= CrudUtil.execute("SELECT QtyOnHand FROM Item WHERE ModelNo=?", ModelNo);
                if(rst.next()){
                int qty=rst.getInt("QtyOnHand");
                int newQtyOnhand=qty+RetuenQty;
                    boolean Itemupdated = CrudUtil.execute("UPDATE  Item SET QtyOnHand=? WHERE ModelNo=?", newQtyOnhand, ModelNo);
                    if(Itemupdated){
                       return  CrudUtil.execute("DELETE FROM OrderDetail  WHERE ModelNo=?&& OrderID=?", ModelNo,OrderID);

                    }
                }

            }
        return false;

    }



    @Override
    public void Delete(String ModelNo) {

    }
}
