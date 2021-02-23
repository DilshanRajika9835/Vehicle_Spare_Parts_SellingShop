package lk.VehicleSparePartsSellingShop.pos.controller;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.ReturnBo;
import lk.VehicleSparePartsSellingShop.pos.bo.ReturnItemHistoryBo;
import lk.VehicleSparePartsSellingShop.pos.dto.ReturnItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.ReturnItemHistoryDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Predicate;

public class ReturnItemHistoryFormController {
    public AnchorPane root;
    public TableView <ReturnItemHistoryDTO>tblReturnItemHistory;
    public TableColumn colorderid;
    public TableColumn colmodelno;
    public TableColumn coltotalcost;
    public TableColumn colreturnqty;
    public TableColumn colreturnmoney;
    public TableColumn colreturndate;
    public TableColumn colreturntime;
    public TextField txtsearchbar;
    public TableColumn colNo;
    ReturnItemHistoryBo returnbo = BoFactory.getInstance().getBo(BoFactory.BoType.ReturnHistory);
    ObservableList<ReturnItemHistoryDTO> returnItemDetail= FXCollections.observableArrayList();
        public  void initialize(){
            colNo.setCellValueFactory(new PropertyValueFactory<>("ReturnID"));
           colorderid.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
           coltotalcost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
           colreturnqty.setCellValueFactory(new PropertyValueFactory<>("ReturnQty"));
           colreturnmoney.setCellValueFactory(new PropertyValueFactory<>("ReturnMoney"));
           colmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
           colreturndate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
           colreturntime.setCellValueFactory(new PropertyValueFactory<>("ReturnTime"));

          LoadAllData();
            Search();

        }

    private void LoadAllData() {
        try {
            returnItemDetail.clear();
            returnItemDetail= returnbo.GetReturnItems();
            tblReturnItemHistory.setItems(returnItemDetail);
        } catch (SQLException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");
        } catch (ClassNotFoundException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");         }

    }

    private void Search() {

        FilteredList<ReturnItemHistoryDTO> filteredListdata = new FilteredList<>(returnItemDetail, e -> true);
        txtsearchbar.setOnKeyReleased(e -> {
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListdata.setPredicate((Predicate<? super ReturnItemHistoryDTO>) part -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowercasefilter = newValue.toLowerCase();
                    if (part.getModelNo().contains(newValue)) {

                        return true;
                    } else if (part.getOrderID().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    }else if (part.getReturnDate().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    }
                    return false;
                });
            }));
            SortedList<ReturnItemHistoryDTO> sortedList = new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblReturnItemHistory.comparatorProperty());
            tblReturnItemHistory.setItems(sortedList);
        });


    }

}
