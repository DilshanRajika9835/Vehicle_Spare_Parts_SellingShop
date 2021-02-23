package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.ReturnBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.QueryDAO;
import lk.VehicleSparePartsSellingShop.pos.dao.ReturnDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.ReturnItemDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.ReturnItem;

import java.sql.SQLException;

public class ReturnBoImpl implements ReturnBo {
    ReturnDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Return);
    QueryDAO querydao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Query);

    @Override
    public ObservableList<ReturnItemDTO> getItems(String OrderID) throws SQLException, ClassNotFoundException {
        ObservableList<ReturnItemDTO>orderlist= FXCollections.observableArrayList();
        ObservableList<ReturnItem> search = dao.Search(OrderID);
        for (ReturnItem orders:search) {
         orderlist.add(new ReturnItemDTO(orders.getOrderID(),orders.getModelNo(),orders.getDescription(),
                 Math.abs(orders.getOrderQTY()),orders.getOrderDate(),Math.abs(orders.getTotalCost()),
                 Math.abs(orders.getDiscount()),Math.abs(orders.getCost())));
        }
        return orderlist;
    }

    @Override
    public boolean AddReturnItem(ReturnItemDTO dto) throws SQLException, ClassNotFoundException {
      return dao.add(new ReturnItem(dto.getOrderID(),dto.getTotalCost(),dto.getReturnQty(),dto.getReturnMoney(),
              dto.getModelNo(),dto.getReturnDate(),dto.getReturnTime()));
    }

    @Override
    public boolean UpdateReturnItem(int OrderQty,int ReturnQty, String ModelNo,String OrderID) throws SQLException, 
            ClassNotFoundException {
        return dao.UpdateStock(OrderQty,ReturnQty,ModelNo,OrderID);
    }

    @Override
    public String DateDifferent(String CurrentDate, String OrderDate) throws SQLException, ClassNotFoundException {
        return querydao.getDateDifferent(CurrentDate,OrderDate);

    }
}
