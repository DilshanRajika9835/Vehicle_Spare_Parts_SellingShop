package lk.VehicleSparePartsSellingShop.pos.controller;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.PaymentBo;
import lk.VehicleSparePartsSellingShop.pos.dto.PaymentHistoryDTO;

import java.util.function.Predicate;

public class PaymentHistoryFormController {
    public AnchorPane root;
    public TableView<PaymentHistoryDTO> tblpaymentHistory;
    public TableColumn colorderid;
    public TableColumn colpayid;
    public TableColumn colpaymenttype;
    public TableColumn colcost;
    public TableColumn coldescount;
    public TableColumn colcash;
    public TableColumn colorderdate;
    public TableColumn colbalance;
    public TableColumn colordertime;
    public TextField txtsearchbar;
    PaymentBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.Payment);
    ObservableList<PaymentHistoryDTO> paymenthistory=FXCollections.observableArrayList();

    public void initialize(){
      colpayid.setCellValueFactory(new PropertyValueFactory<>("PayID"));
      colorderid.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
      colpaymenttype.setCellValueFactory(new PropertyValueFactory<>("PaymentType"));
      colcost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
      coldescount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
      colcash.setCellValueFactory(new PropertyValueFactory<>("Cash"));
      colbalance.setCellValueFactory(new PropertyValueFactory<>("Balance"));
      colorderdate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
      colordertime.setCellValueFactory(new PropertyValueFactory<>("OrderTime"));
      getAllpaymentHistory();
    }

    private void getAllpaymentHistory() {
        try {
            paymenthistory = bo.paymrntHistory();
            tblpaymentHistory.setItems(paymenthistory);
            Search();
        }catch (Exception ex){

        }
    }


    private void Search() {
        FilteredList<PaymentHistoryDTO> filteredListdata=new FilteredList<>(paymenthistory, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super PaymentHistoryDTO>) payment->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(payment.getOrderID().contains(newValue)){

                        return true;
                    }else if(payment.getOrderDate().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    else if(payment.getOrderTime().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    return false;
                });
            } ));
            SortedList<PaymentHistoryDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblpaymentHistory.comparatorProperty());
            tblpaymentHistory.setItems(sortedList);
        });

    }



}
