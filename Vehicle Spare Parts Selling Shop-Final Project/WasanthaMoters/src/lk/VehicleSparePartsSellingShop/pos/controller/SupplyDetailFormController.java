package lk.VehicleSparePartsSellingShop.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.SupplierBo;
import lk.VehicleSparePartsSellingShop.pos.bo.SupplyDetailBo;
import lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl.SupplyDetailDAOImpl;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplierDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplyDetailDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.SupplyDetail;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;
import lk.VehicleSparePartsSellingShop.pos.view.TM.DateTime;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SupplyDetailFormController {

    public TableView<SupplyDetailDTO> tblsupplierdetail;
    public TableColumn colsupplyid;
    public TableColumn colmodelno;
    public TableColumn coldate;
    public TableColumn coltime;
    public TextField txtsearchbar;

    public JFXTextField txtphonenumber;
    public JFXTextField txtSuppliername;
    public JFXTextField txtaddress;
    public JFXTextField txtcompanyname;
    public JFXTextField txtEmailaddress;
    public TableColumn colno;
    public JFXComboBox cmbSupplierID;
    public AnchorPane root;
    public JFXTextField txtmodelno;
    SupplyDetailBo supplyDetailbo = BoFactory.getInstance().getBo(BoFactory.BoType.SupplyDetail);
    SupplierBo supplierBo = BoFactory.getInstance().getBo(BoFactory.BoType.Supplier);
    ObservableList<SupplyDetailDTO> allSupplyDetails=FXCollections.observableArrayList();
    public void initialize()  {

        colmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("SupplyDate"));
        coltime.setCellValueFactory(new PropertyValueFactory<>("SupplyTime"));
        colsupplyid.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        colno.setCellValueFactory(new PropertyValueFactory<>("No"));

        getAllSupplyID();
        LoadAllSupplyDetail();
        tblsupplierdetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
              LoadUniqueData(newValue);
            }
        });
        Search();

    }

    private void Search() {
        FilteredList<SupplyDetailDTO> filteredListdata=new FilteredList<>(allSupplyDetails, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super SupplyDetailDTO>) supply->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(supply.getModelNo().contains(newValue)){

                        return true;
                    }else if(supply.getSupplyDate().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(supply.getSupplierID().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(supply.getSupplyTime().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<SupplyDetailDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblsupplierdetail.comparatorProperty());
            tblsupplierdetail.setItems(sortedList);
        });
    }

    private void LoadUniqueData(SupplyDetailDTO newValue) {
        try {
            SupplierDTO supplierDTO = supplierBo.searchSupplier(newValue.getSupplierID());
            txtmodelno.setText(newValue.getModelNo());
            txtSuppliername.setText(supplierDTO.getSupplierName());
            txtaddress.setText(supplierDTO.getCompanyAddress());
            txtEmailaddress.setText(supplierDTO.getCompanyEmailAddress());
            txtphonenumber.setText(supplierDTO.getTel());
            txtcompanyname.setText(supplierDTO.getCompanyName());
            cmbSupplierID.setValue(supplierDTO.getSupplierID());
        } catch (Exception ex) {
        }
    }

    private void LoadAllSupplyDetail() {
        allSupplyDetails.clear();
        try {
          allSupplyDetails = supplyDetailbo.getAllSupplyDetails();
            tblsupplierdetail.setItems(allSupplyDetails);
        } catch (Exception ex) {
        }
    }
    private void getAllSupplyID() {
        try {
            ObservableList SID=FXCollections.observableArrayList();
            ObservableList<SupplierDTO> allSuppliers = supplierBo.getAllSuppliers();
            for (SupplierDTO supplyer:allSuppliers) {
                SID.add(supplyer.getSupplierID());
            }
            cmbSupplierID.setItems(SID);

        } catch (Exception ex) {
        }

    }


    public void btnAddOnAction(ActionEvent actionEvent) {

            try {
                if(Pattern.compile("^[A-z 0-9 -)]{4,}$").matcher(txtmodelno.getText()).matches()){
                    ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  add place this " + txtmodelno.getText() + " Item?", ok, no);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.orElse(no)==ok){
                        String date = DateTime.getDateTime("yyyy/MM/dd");
                        String time = DateTime.getDateTime("HH:mm:ss a");
                        String modelno = txtmodelno.getText();
                        String supplyerid = cmbSupplierID.getValue().toString();
                        boolean added = supplyDetailbo.addSupplyDetail(new SupplyDetailDTO(supplyerid, modelno, date, time));
                        if (added) {
                            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png", txtmodelno.getText() + "This ModelNo place Successfully! ", "Successfully!");
                            LoadAllSupplyDetail();

                        } else {
                            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png", txtmodelno.getText() + "This ModelNo place fail! ", "Warning!");

                        }
                    }
                }else {
                    CheckValidate();
                }


            } catch (SQLException ex) {
                switch (ex.getErrorCode()){
                    case 1062 :{
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtmodelno.getText()+" This item Already added  in the System! ","Warning!");
                        break;
                    }
                    case 1452 :{
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtmodelno.getText()+" This item not Registered in the System! ","Warning!");
                        break;
                    }
                    default:{CheckValidate();}

                }

            } catch (Exception ex) {
                CheckValidate();

            }

    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {

        try {
            if(Pattern.compile("^[A-z 0-9 -)]{4,}$").matcher(txtmodelno.getText()).matches()) {
                boolean deleted = supplyDetailbo.DeleteSupplyDetail(txtmodelno.getText(),cmbSupplierID.getValue().toString());
                if (deleted) {
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png", txtmodelno.getText() + "This ModelNo remove Successfully! ", "Successfully!");
                    LoadAllSupplyDetail();
                } else {
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png", txtmodelno.getText() + "This ModelNo remove Fail..Try again!! ", "Warning!");
                }
            }else {
                CheckValidate();
            }
        } catch (Exception ex) {
            CheckValidate();
        }
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if(Pattern.compile("^[A-z 0-9 -)]{4,}$").matcher(txtmodelno.getText()).matches()) {
                String date = DateTime.getDateTime("yyyy/MM/dd");
                String time = DateTime.getDateTime("HH:mm:ss a");
                boolean update = supplyDetailbo.updateSupplyDetail(new SupplyDetailDTO(cmbSupplierID.getValue().toString(), txtmodelno.getText(), date, time));
                if (update) {
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png", txtmodelno.getText() + "This ModelNo Update Successfully! ", "Successfully!");
                    LoadAllSupplyDetail();
                } else {
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png", txtmodelno.getText() + "This ModelNo Update fail..try again! ", "Warning!");
                }
            }else {
                CheckValidate();

            }
        } catch (Exception ex) {
       CheckValidate();

        }
    }
    public void CheckValidate(){
        if(cmbSupplierID.getSelectionModel().getSelectedIndex()==-1){
            cmbSupplierID.setFocusColor(Paint.valueOf("red"));
            cmbSupplierID.requestFocus();
        } else {
            txtmodelno.requestFocus();
            txtmodelno.setFocusColor(Paint.valueOf("red"));
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtmodelno.clear();
        txtSuppliername.clear();
        txtaddress.clear();
        txtEmailaddress.clear();
        txtphonenumber.clear();
        txtcompanyname.clear();
        cmbSupplierID.setValue(null);
    }

    public void txtmodelNoEnterdOnAction(ActionEvent actionEvent) {
        btnAddOnAction(actionEvent);
    }

    public void cmbSupplierIDSelectOnAction(ActionEvent actionEvent) {
        try {
            SupplierDTO supplierDTO = supplierBo.searchSupplier(cmbSupplierID.getValue().toString());
            txtSuppliername.setText(supplierDTO.getSupplierName());
            txtaddress.setText(supplierDTO.getCompanyAddress());
            txtEmailaddress.setText(supplierDTO.getCompanyEmailAddress());
            txtphonenumber.setText(supplierDTO.getTel());
            txtcompanyname.setText(supplierDTO.getCompanyName());
            cmbSupplierID.setValue(supplierDTO.getSupplierID());
        }  catch (Exception ex) {
        }
    }
}
