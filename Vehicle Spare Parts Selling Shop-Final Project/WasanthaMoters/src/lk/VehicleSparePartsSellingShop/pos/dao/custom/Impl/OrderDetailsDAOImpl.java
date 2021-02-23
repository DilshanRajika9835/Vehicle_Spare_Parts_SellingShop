package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.OrderDetailsDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.Order;
import lk.VehicleSparePartsSellingShop.pos.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetail orderdetail) throws SQLException, ClassNotFoundException {
     return CrudUtil.execute("INSERT INTO OrderDetail VALUES(?,?,?,?)",orderdetail.getOrderID(),
             orderdetail.getModelNo(),orderdetail.getOrderQTy(),orderdetail.getDiscount());

    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderdetail) throws ClassNotFoundException, SQLException {
        ResultSet rst  = CrudUtil.execute("SELECT QtyOnHand FROM Item WHERE ModelNo=?",
                orderdetail.getModelNo());
        if(rst.next()){
            int qty = rst.getInt("QtyOnHand");
            int QtyOnHand=qty-orderdetail.getOrderQTy();
            return CrudUtil.execute("UPDATE Item SET QtyOnHand=? WHERE ModelNo=?",
                    QtyOnHand,orderdetail.getModelNo());
        }


        return false;
    }

    @Override
    public OrderDetail search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<OrderDetail> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }




}
