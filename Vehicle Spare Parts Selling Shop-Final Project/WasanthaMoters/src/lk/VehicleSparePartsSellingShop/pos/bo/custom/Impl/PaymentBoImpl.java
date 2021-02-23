package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.PaymentBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.PaymentDAO;
import lk.VehicleSparePartsSellingShop.pos.dao.QueryDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.PaymentDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.PaymentHistoryDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Payment;
import lk.VehicleSparePartsSellingShop.pos.entity.PaymentHistory;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBo {
    QueryDAO querydao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Query);

    @Override
    public ObservableList<PaymentHistoryDTO> paymrntHistory() throws SQLException, ClassNotFoundException {
        ObservableList <PaymentHistoryDTO>paymentList= FXCollections.observableArrayList();
        ObservableList<PaymentHistory> payments = querydao.GetPaymentHistory();
        for (PaymentHistory pay:payments) {
        paymentList.add(new PaymentHistoryDTO(pay.getPayID(),pay.getOrderID(),pay.getPaymentType(),
                pay.getCost(),pay.getDiscount(),pay.getCash(),pay.getBalance(),pay.getOrderDate(),
                pay.getOrderTime()));
        }
        return paymentList;
    }


}
