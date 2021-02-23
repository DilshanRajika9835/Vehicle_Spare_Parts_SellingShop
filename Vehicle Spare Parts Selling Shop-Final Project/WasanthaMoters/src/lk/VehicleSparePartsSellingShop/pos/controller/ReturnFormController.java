package lk.VehicleSparePartsSellingShop.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.OrderBo;
import lk.VehicleSparePartsSellingShop.pos.bo.ReturnBo;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.ReturnItemDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;
import lk.VehicleSparePartsSellingShop.pos.view.TM.DateTime;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ReturnFormController {
    public TableView<ReturnItemDTO> tblorder;
    public TextField txtsearch;
    public TableColumn colorderid;
    public TableColumn colmodelno;
    public TableColumn coldescription;
    public TableColumn colorderdate;
    public TableColumn coldescount;
    public TableColumn coltotalcost;
    public TableColumn colorderqty;
    public TableColumn coldescountprice;
    public JFXTextField txtorderid;
    public JFXTextField txtdescription;
    public JFXTextField txtunitprice;
    public JFXTextField txtreturnmoney;
    public JFXTextField txtmodelno;
    public JFXTextField txttotalcost;
    public JFXTextField txtdiscount;
    public JFXTextField txtorderdate;
    public JFXTextField txtorderqty;
    public JFXTextField txtreturnQty;
    private double itemdescount;

    ReturnBo returnbo = BoFactory.getInstance().getBo(BoFactory.BoType.Return);
    OrderBo orderbo = BoFactory.getInstance().getBo(BoFactory.BoType.Order);

    public  void initialize() {
            txtorderid.requestFocus();
            colorderid.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
            colmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
            coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            colorderdate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
            coltotalcost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
            colorderqty.setCellValueFactory(new PropertyValueFactory<>("OrderQTY"));
            coldescount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
            coldescountprice.setCellValueFactory(new PropertyValueFactory<>("Cost"));
            txtreturnQty.setDisable(true);
            txtreturnmoney.setDisable(true);

        }

    private void Search(ObservableList<ReturnItemDTO>orderlist) {

        FilteredList<ReturnItemDTO> filteredListdata = new FilteredList<>(orderlist, e -> true);
        txtsearch.setOnKeyReleased(e -> {
            txtsearch.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListdata.setPredicate((Predicate<? super ReturnItemDTO>) part -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowercasefilter = newValue.toLowerCase();
                    if (part.getModelNo().contains(newValue)) {

                        return true;
                    } else if (part.getDescription().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    }
                    return false;
                });
            }));
            SortedList<ReturnItemDTO> sortedList = new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblorder.comparatorProperty());
            tblorder.setItems(sortedList);
        });

        tblorder.getSelectionModel().selectedItemProperty().addListener((((observable, oldValue, newValue) -> {

            if (newValue != null) {
                LoadUniqueData(newValue);

            }
        })));
    }

    private void LoadUniqueData(ReturnItemDTO newValue) {
        try {
            ItemDTO search = orderbo.Search(newValue.getModelNo());

            txtmodelno.setText(newValue.getModelNo());
            txtorderdate.setText(newValue.getOrderDate());
            txtdescription.setText(newValue.getDescription());
            txttotalcost.setText(Double.toString(newValue.getCost()));
            txtorderqty.setText(Integer.toString(newValue.getOrderQTY()));
            txtunitprice.setText(Double.toString(search.getUnitPrice()));
            txtdiscount.setText(Double.toString(newValue.getDiscount()));
            itemdescount = search.getDiscount();
            txtreturnQty.requestFocus();
        }catch (Exception ex){

        }
    }
    public void CalculateCost(){
    try {
        double unitprice = Double.parseDouble(txtunitprice.getText());
        int returnqty = Integer.parseInt(txtreturnQty.getText());
        int orderQty=Integer.parseInt(txtorderqty.getText());
        double returnMoney=(unitprice*returnqty)-((unitprice*returnqty)*(itemdescount/100));

        if(Pattern.compile("^[0-9]{1,}$").matcher(Integer.toString(returnqty)).matches()){
            if(returnqty<=orderQty){
            txtreturnmoney.setText(Double.toString(returnMoney));
                txtreturnQty.setFocusColor(Paint.valueOf("blue"));
            }else {
                txtreturnQty.setFocusColor(Paint.valueOf("red"));
            }
        }else {
            txtreturnQty.setFocusColor(Paint.valueOf("red"));
        }

       }catch ( NumberFormatException ex){
        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
    }

    }

    public void btnReturnOnAction(ActionEvent actionEvent) {
        try {
            if(txtorderid.getText().length()>0&&txtmodelno.getText().length()>0&&txtreturnmoney.getText().length()>0&txtreturnQty.getText().length()>0) {
                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("Cancel", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Return this " + txtmodelno.getText() + " Item?", ok, no);
                Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.orElse(no) == ok) {
                        if (Pattern.compile("^[ORD]{3}[0-9]{1,}$").matcher(txtorderid.getText()).matches() && txtdescription.getText().length() > 0) {
                            if (Pattern.compile("^[0-9]{1,}$").matcher(txtreturnQty.getText()).matches()) {
                                String modelno = txtmodelno.getText();
                                String orderid = txtorderid.getText();
                                double totalcost = Double.parseDouble(txttotalcost.getText());
                                int returnqty = Integer.parseInt(txtreturnQty.getText());
                                double returnmoney = Double.parseDouble(txtreturnmoney.getText());
                                String date = DateTime.getDateTime("yyyy/MM/dd");
                                String time = DateTime.getDateTime("HH:mm:ss a");

                                boolean returned = returnbo.AddReturnItem(new ReturnItemDTO(orderid, totalcost, returnqty, returnmoney, modelno, date, time));
                                if (returned) {
                                    UpdateOrderDetail();
                                }
                            } else {
                                txtreturnQty.setFocusColor(Paint.valueOf("red"));
                                txtreturnQty.requestFocus();
                            }
                        } else {
                            txtorderid.setFocusColor(Paint.valueOf("red"));
                            txtorderid.requestFocus();
                        }
                    }


            }
        }catch (Exception ex){


        }
    }
    private  boolean CheckingReturn(){
        String CurrentDate =DateTime.getDateTime("yyyy/MM/dd");
        try {
            String checkingreturn = returnbo.DateDifferent(CurrentDate,txtorderdate.getText());
           int canreturn= (1+Integer.parseInt(checkingreturn));
           if(canreturn>=0&&canreturn<=7){
               return true;
           }

        } catch (SQLException ex) {

        } catch (Exception ex) {
            txtorderid.setFocusColor(Paint.valueOf("red"));
            txtorderid.requestFocus();

        }
        return false;
    }
    private  void Clean(){
        txtmodelno.clear();
        txtorderdate.clear();
        txtdescription.clear();
        txttotalcost.clear();
        txtorderqty.clear();
        txtunitprice.clear();
        txtdiscount.clear();
        txtreturnQty.clear();
        txtreturnmoney.clear();
    }
    private void UpdateOrderDetail() {
        try {
            int OrderQty = Integer.parseInt(txtorderqty.getText());
            int returnQty = Integer.parseInt(txtreturnQty.getText());
            String modelno = txtmodelno.getText();
            String OrderID = txtorderid.getText();
            boolean updated = returnbo.UpdateReturnItem(OrderQty, returnQty, modelno, OrderID);
            if (updated) {
                Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png",
                        "Item return Successfully!","Successfully!");
                Clean();
                txtOrderIDSearchOnAction(new ActionEvent());
                txtreturnQty.setDisable(true);
                txtreturnmoney.setDisable(true);
            } else {
                Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                        "Item return Fail!","Warning!");
            }
        }catch (Exception ex){

        }

    }




    public void txtOrderIDSearchOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^ORD[0-9]{1,}$").matcher(txtorderid.getText()).matches()) {
                ObservableList<ReturnItemDTO> items = returnbo.getItems(txtorderid.getText());
                tblorder.setItems(items);
                Search(items);
                txtorderid.setFocusColor(Paint.valueOf("blue"));
            } else {
                txtorderid.setFocusColor(Paint.valueOf("red"));
                txtorderid.requestFocus();
            }
        }catch (Exception ex){
            txtorderid.setFocusColor(Paint.valueOf("red"));
            txtorderid.requestFocus();

        }
    }


    public void btnCheckOnAction(ActionEvent actionEvent) {
     if(CheckingReturn()){
         txtreturnQty.setDisable(false);
         txtreturnmoney.setDisable(false);
     }else {
         if(txtdescription.getText().length()>0) {
             Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                     "Sorry! " + txtmodelno.getText() + " This item Cannot be return The validate date has Ex:paired.. ", "Warning!");
             txtmodelno.setFocusColor(Paint.valueOf("red"));
         }else {
           txtorderid.setFocusColor(Paint.valueOf("red"));
         }
     }
    }

    public void txtreturnQtyOnAction(ActionEvent actionEvent) {
        CalculateCost();
    }
}
