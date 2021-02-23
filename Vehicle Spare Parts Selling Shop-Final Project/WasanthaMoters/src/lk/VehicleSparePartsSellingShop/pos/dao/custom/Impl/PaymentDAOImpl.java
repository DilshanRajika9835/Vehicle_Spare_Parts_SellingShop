package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.PaymentDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.Payment;
import lk.VehicleSparePartsSellingShop.pos.entity.PaymentHistory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean add(Payment payment) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO Payment(OrderID,PaymentType,Cost,Cash,Balance) VALUES(?,?,?,?,?)",
                payment.getOrderID(),payment.getPaymentType(),payment.getCost(),payment.getCash(),
                payment.getBalance());
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Payment payment) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Payment search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<Payment> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }
}
