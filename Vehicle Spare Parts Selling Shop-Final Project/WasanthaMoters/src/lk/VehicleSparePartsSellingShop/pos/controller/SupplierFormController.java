package lk.VehicleSparePartsSellingShop.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.SupplierBo;
import lk.VehicleSparePartsSellingShop.pos.dto.SupplierDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SupplierFormController {


    public AnchorPane root;
    public TableView<SupplierDTO> tblsupplier;
    public TableColumn colsuplierid;
    public TableColumn colsupliername;
    public TableColumn colphonenumber;
    public TableColumn coladdress;
    public TableColumn colcompanyname;
    public TableColumn colemailaddress;
    public TextField txtsearchbar;
    public JFXTextField txtsupplierid;
    public JFXTextField txtcompanyname;
    public JFXTextField txtname;
    public JFXTextField txtaddress;
    public JFXTextField txtphonenumber;
    public JFXTextField txtemail;
    public TableColumn colno;
    SupplierBo supplierbo = BoFactory.getInstance().getBo(BoFactory.BoType.Supplier);
    ObservableList<SupplierDTO> allSuppliers= FXCollections.observableArrayList();
    public void initialize(){
    colno.setCellValueFactory(new PropertyValueFactory<>("No"));
    colsuplierid.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
    colcompanyname.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
    colsupliername.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
    colphonenumber.setCellValueFactory(new PropertyValueFactory<>("Tel"));
    coladdress.setCellValueFactory(new PropertyValueFactory<>("CompanyAddress"));
    colemailaddress.setCellValueFactory(new PropertyValueFactory<>("companyEmailAddress"));
    loadAllSupplier();
    tblsupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        loaduniqueData(newValue);
    });
        Search();

}

    private void Search() {
        FilteredList<SupplierDTO> filteredListdata=new FilteredList<>(allSuppliers, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super SupplierDTO>) supplier->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(supplier.getSupplierID().contains(newValue)){

                        return true;
                    }else if(supplier.getCompanyName().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(supplier.getSupplierName().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(supplier.getCompanyAddress().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<SupplierDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblsupplier.comparatorProperty());
            tblsupplier.setItems(sortedList);
        });
    }

    private boolean CheckSupplierDetails() {
        try {
            if (Pattern.compile("[0-9]{9}[V]{1}|[0-9]{12}$").matcher(txtsupplierid.getText()).matches()) {
                if (Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$").matcher(txtname.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{10}$").matcher(txtphonenumber.getText()).matches()) {
                        if (Pattern.compile("^[A-z() .]{3,}$").matcher(txtcompanyname.getText()).matches()) {
                            if (Pattern.compile("^[A-z ,0-9,/-]{10,}|[ ]$").matcher(txtaddress.getText()).matches()) {
                                if (Pattern.compile("^([A-z]|[0-9]){1,}[@]{1}[A-z]{1,}.[A-z]{1,}$").matcher(txtemail.getText()).matches()) {
                                    return true;
                                } else {
                                    txtemail.setFocusColor(Paint.valueOf("red"));
                                    txtemail.requestFocus();
                                }
                            } else {
                                txtaddress.setFocusColor(Paint.valueOf("red"));
                                txtaddress.requestFocus();

                            }
                        } else {
                            txtcompanyname.setFocusColor(Paint.valueOf("red"));
                            txtcompanyname.requestFocus();

                        }
                    } else {
                        txtphonenumber.setFocusColor(Paint.valueOf("red"));
                        txtphonenumber.requestFocus();

                    }
                } else {
                    txtname.setFocusColor(Paint.valueOf("red"));
                    txtname.requestFocus();

                }
            } else {
                txtsupplierid.setFocusColor(Paint.valueOf("red"));
                txtsupplierid.requestFocus();

            }
        }catch (Exception ex){

        }

     return false;
    }

    private void loaduniqueData(SupplierDTO newValue) {
        if(newValue!=null) {
            txtsupplierid.setText(newValue.getSupplierID());
            txtcompanyname.setText(newValue.getCompanyName());
            txtname.setText(newValue.getSupplierName());
            txtphonenumber.setText(newValue.getTel());
            txtaddress.setText(newValue.getCompanyAddress());
            txtemail.setText(newValue.getCompanyEmailAddress());
        }
    }

    private void loadAllSupplier() {

        try {
            allSuppliers.clear();
            allSuppliers = supplierbo.getAllSuppliers();
            tblsupplier.setItems(allSuppliers);
        } catch (Exception ex) {
        }

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
      if(Pattern.compile("^[0-9]{9}[V]{1,}|[0-9]{12}$").matcher(txtsupplierid.getText()).matches()){
          ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
          ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  Remove " + txtsupplierid.getText() + " Supplier?", ok, no);
          Optional<ButtonType> buttonType = alert.showAndWait();
          if (buttonType.orElse(no) == ok) {

                  boolean deleted = supplierbo.deleteSupplier(txtsupplierid.getText());
                  if (deleted) {
                      Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png","Supplier Add is Successfully!","Successfully!");
                      loadAllSupplier();
                      btnClearOnAction(actionEvent);
                  } else {
                      Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png","Supplier Delete Fail! Try again..","Warning!");
                  }

          }
      }else {
          txtsupplierid.setFocusColor(Paint.valueOf("red"));
          txtsupplierid.requestFocus();
      }
      }  catch (Exception ex) {
          CheckSupplierDetails();
        }

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
        if(CheckSupplierDetails()){
            ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  add this " + txtname.getText() + "  Supplier?", ok, no);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.orElse(no) == ok) {
                    boolean add = supplierbo.addSupplier(new SupplierDTO(txtsupplierid.getText(), txtcompanyname.getText(), txtname.getText(),txtphonenumber.getText(), txtaddress.getText(), txtemail.getText()));
                    if (add) {
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png","Supplier Added Successfully","Successfully!");
                        loadAllSupplier();
                        btnClearOnAction(actionEvent);
                    } else {
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png","Supplier Added is Fail! Try again..","Warning!");
                    }
            }
        }
        } catch (SQLException ex) {
            switch (ex.getErrorCode()){
                case 1062 :{
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtsupplierid.getText()+" This Supplier Already Registered this System ","Warning!");
                    break;
                }
                case 1452 :{
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtsupplierid.getText()+" This Supplier Not Registered this System.... ","Warning!");
                    break;
                }

            }
        }
        catch (Exception ex) {
            CheckSupplierDetails();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
                if(CheckSupplierDetails()){
                    ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure  Update  this " + txtsupplierid.getText() + "  Supplier?", ok, no);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.orElse(no) == ok) {

                            boolean updated = supplierbo.updateSupplier(new SupplierDTO(txtsupplierid.getText(), txtcompanyname.getText(), txtname.getText(), txtphonenumber.getText(), txtaddress.getText(), txtemail.getText()));
                            if (updated) {
                                new Alert(Alert.AlertType.CONFIRMATION, txtsupplierid.getText() + " supplier  Update Successfully", ButtonType.OK).show();
                                loadAllSupplier();
                                btnClearOnAction(actionEvent);

                            } else {
                                new Alert(Alert.AlertType.WARNING, txtsupplierid.getText() + " supplier  Update is fail!", ButtonType.OK).show();
                            }
                    }
                }
                }catch (SQLException ex){
            switch (ex.getErrorCode()){
                case 1452 :{
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtsupplierid.getText()+" This Supplier Not Registered this System.... ","Warning!");
                    break;
                }

            }
        }
        catch (Exception ex) {
            CheckSupplierDetails();
                 }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtsupplierid.clear();
        txtcompanyname.clear();
        txtname.clear();
        txtphonenumber.clear();
        txtaddress.clear();
        txtemail.clear();
    }


    public void txtSupplierIDSearchOnAction(ActionEvent actionEvent) {
        try {
        if(Pattern.compile("^[0-9]{9}[V]{1,}|[0-9]{12}$").matcher(txtsupplierid.getText()).matches()){
                SupplierDTO supplierDTO = supplierbo.searchSupplier(txtsupplierid.getText());
                loaduniqueData(supplierDTO);
        }else {
            txtsupplierid.setFocusColor(Paint.valueOf("red"));
            txtsupplierid.requestFocus();
        }
        } catch (Exception ex) {
        }

    }
}
