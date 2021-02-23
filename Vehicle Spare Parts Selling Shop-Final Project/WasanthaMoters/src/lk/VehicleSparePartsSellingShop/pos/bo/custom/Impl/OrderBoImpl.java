package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.OrderBo;
import lk.VehicleSparePartsSellingShop.pos.dao.*;
import lk.VehicleSparePartsSellingShop.pos.db.DBConnection;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.OrderDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.OrderDetailDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.PaymentDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Item;
import lk.VehicleSparePartsSellingShop.pos.entity.Order;
import lk.VehicleSparePartsSellingShop.pos.entity.OrderDetail;
import lk.VehicleSparePartsSellingShop.pos.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {
    OrdersDAO orderdao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Order);
    OrderDetailsDAO orderdetaildao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Orderdetail);
    PaymentDAO paymentdao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.payment);
    QueryDAO querydao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Query);

    @Override
    public ArrayList<ItemDTO> getModelNo() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO>modelnolist=new ArrayList<>();
        ObservableList<Item> modelNo = orderdao.getModelNo();
        for (Item item:modelNo) {
         modelnolist.add(new ItemDTO(item.getModelno(),item.getDescription(),item.getQtyonhand(),item.getBrand(),
                 item.getColour(),item.getType(),item.getWidth(),item.getHeight(),item.getDiscount(),item.getUnitPrice()));
        }
        return modelnolist;
    }

    @Override
    public ItemDTO Search(String id) throws SQLException, ClassNotFoundException {
        Item search = orderdao.Search(id);
        return new ItemDTO(search.getModelno(),search.getDescription(),search.getQtyonhand(),search.getBrand(),
                search.getColour(),search.getType(),search.getWidth(),search.getHeight(),search.getDiscount(),search.getUnitPrice());
    }

    @Override
    public boolean placeOrder(OrderDTO order) {
        Connection connection=null;
        try {
             connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isadded=orderdao.Add(new Order(order.getOrderID(),order.getOrderDate(),order.getOrderTime()));
            if(isadded){
                boolean addOrderDetail=false;
                ArrayList<OrderDetailDTO> orderdetillist = order.getOrderdetillist();
                for (OrderDetailDTO dto:orderdetillist) {
                    OrderDetail orderDetail = new OrderDetail(dto.getOrderID(), dto.getModelNo(), 
                            dto.getOrderQTy(), dto.getDiscount());
                    addOrderDetail = orderdetaildao.add(orderDetail);

                }
                if(addOrderDetail){
                    PaymentDTO paymentDTO=order.getPaymentDTO();
                    boolean paymentadd= paymentdao.add(new Payment(paymentDTO.getOrderID(),paymentDTO.getPaymentType(),
                            paymentDTO.getCost(),paymentDTO.getCash(),paymentDTO.getBalance()));
                        if(paymentadd){
                            boolean ItemUpdate=false;
                            for (OrderDetailDTO dto:orderdetillist) {
                                OrderDetail stockupdate = new OrderDetail(dto.getOrderID(), dto.getModelNo(), 
                                        dto.getOrderQTy(), dto.getDiscount());
                                ItemUpdate= orderdetaildao.update(stockupdate);

                            }
                            if(ItemUpdate){
                              connection.commit();
                             return true;
                            }
                        }
                }
            }else {
                connection.rollback();
                return false;
            }
        } catch (Exception ex){

        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String getOrderID() throws SQLException, ClassNotFoundException {
        return querydao.getOrderID();
    }


}
