package lk.VehicleSparePartsSellingShop.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.ManageItemBo;
import lk.VehicleSparePartsSellingShop.pos.bo.custom.AllPartsBo;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.LeastMoviableItemDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ManageItemFormController {

    public AnchorPane root;
    public JFXTextField txtmodelno;
    public JFXTextField txtcolor;
    public JFXTextField txtdescription;
    public JFXTextField txtqtyonhand;
    public JFXTextField txtwidth;
    public JFXTextField txtheight;
    public JFXTextField txtbrand;
    public JFXTextField txtunitprice;
    public JFXTextField txtdiscount;
    public TextField txtsearchbar;
    public TableView<ItemDTO> tblitem;
    public TableColumn colmodelno;
    public TableColumn coldescription;
    public TableColumn colqtyonhand;
    public TableColumn colbrand;
    public TableColumn colcolor;
    public TableColumn coltype;
    public TableColumn colwidth;
    public TableColumn colheight;
    public TableColumn coldiscount;
    public TableColumn colunitprice;
    public JFXComboBox cmbtype;
    public TableColumn colno;
    ManageItemBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.ManageItem);
    AllPartsBo itembo = BoFactory.getInstance().getBo(BoFactory.BoType.Allparts);
    ObservableList<String>vehicletype= FXCollections.observableArrayList("Car","Van","Bus","Lorry","ThreeWheel","MoterBike");
    ObservableList<ItemDTO> allParts=FXCollections.observableArrayList();

    public void initialize()  {

        colno.setCellValueFactory(new PropertyValueFactory<>("No"));
        colmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colqtyonhand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        colbrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        colcolor.setCellValueFactory(new PropertyValueFactory<>("Color"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colwidth.setCellValueFactory(new PropertyValueFactory<>("Width"));
        colheight.setCellValueFactory(new PropertyValueFactory<>("Height"));
        coldiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));;
        cmbtype.setItems(vehicletype);
        LoadAllItem();
        tblitem.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue!=null){
                loadUnqData( newValue);

            }
        }
        ));
        Search();

    }

    private void Search() {
        FilteredList<ItemDTO> filteredListdata=new FilteredList<>(allParts, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super ItemDTO>) item->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(item.getModelNo().contains(newValue)){

                        return true;
                    }else if(item.getDescription().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    else if(item.getBrand().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    return false;
                });
            } ));
            SortedList<ItemDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblitem.comparatorProperty());
            tblitem.setItems(sortedList);
        });
    }

    private boolean CheckItemDetail(){
        try {
            if (Pattern.compile("^[A-z 0-9 -)]{4,}$").matcher(txtmodelno.getText()).matches()) {
                if (Pattern.compile("^[A-z 0-9 -)]{4,}$").matcher(txtdescription.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{1,}$").matcher(txtqtyonhand.getText()).matches()) {
                        if (Pattern.compile("^[A-z 0-9 -)]{4,}$").matcher(txtbrand.getText()).matches()) {
                            if (Pattern.compile("^[A-z]{3,}|[-]{1}$").matcher(txtcolor.getText()).matches()) {
                                if (cmbtype.getSelectionModel().getSelectedIndex() != -1) {
                                    if (Pattern.compile("^[0-9]{1,}(mm|inch|cm)|[0-9]{1,}|[0-9.]{1,}[0-9]{1}" +
                                            "(mm|inch|cm)|[-]{1}$").matcher(txtwidth.getText()).matches()) {
                                        if (Pattern.compile("^[0-9]{1,}(mm|inch|cm)|[0-9]{1,}|[0-9.]{1,}[0-9]{1}" +
                                                "(mm|inch|cm)|[-]{1}$").matcher(txtheight.getText()).matches()) {
                                            if (Pattern.compile("^[0-9]{1,}|[0-9.]{1,}[0-9]{1}$").
                                                    matcher(txtdiscount.getText()).matches() &&
                                                    Double.parseDouble(txtdiscount.getText()) >= 0 &&
                                                    Double.parseDouble(txtdiscount.getText()) <= 100) {
                                                if (Pattern.compile("^[0-9]{1,}|[0-9.]{1,}[0-9]{1}$").
                                                        matcher(txtunitprice.getText()).matches() &&
                                                        txtunitprice.getText().length() <= 10) {
                                                    return true;
                                                } else {
                                                    txtunitprice.setFocusColor(Paint.valueOf("red"));
                                                    txtunitprice.requestFocus();
                                                }
                                            } else {
                                                txtdiscount.setFocusColor(Paint.valueOf("red"));
                                                txtdiscount.requestFocus();

                                            }
                                        } else {
                                            txtheight.setFocusColor(Paint.valueOf("red"));
                                            txtheight.requestFocus();

                                        }
                                    } else {
                                        txtwidth.setFocusColor(Paint.valueOf("red"));
                                        txtwidth.requestFocus();

                                    }
                                } else {
                                    cmbtype.setFocusColor(Paint.valueOf("red"));
                                    cmbtype.requestFocus();
                                }
                            } else {
                                txtcolor.setFocusColor(Paint.valueOf("red"));
                                txtcolor.requestFocus();

                            }
                        } else {
                            txtbrand.setFocusColor(Paint.valueOf("red"));
                            txtbrand.requestFocus();

                        }
                    } else {
                        txtqtyonhand.setFocusColor(Paint.valueOf("red"));
                        txtqtyonhand.requestFocus();

                    }
                } else {
                    txtdescription.setFocusColor(Paint.valueOf("red"));
                    txtdescription.requestFocus();

                }
            } else {
                txtmodelno.setFocusColor(Paint.valueOf("red"));
                txtmodelno.requestFocus();

            }
        }catch (Exception ex){

        }
        return false;
    }

    private void LoadAllItem() {
        allParts.clear();
        try {
            allParts = itembo.LoadAllData();
            tblitem.setItems(allParts);
        } catch (Exception ex) {
        }

    }



    private void loadUnqData(ItemDTO newValue) {
        if(newValue!=null) {
            txtmodelno.setText(newValue.getModelNo());
            txtdescription.setText(newValue.getDescription());
            cmbtype.setValue(newValue.getType());
            txtheight.setText(newValue.getHeight());
            txtwidth.setText(newValue.getWidth());
            txtcolor.setText(newValue.getColor());
            txtdiscount.setText(Double.toString(newValue.getDiscount()));
            txtqtyonhand.setText(Integer.toString(newValue.getQtyOnHand()));
            txtunitprice.setText(Double.toString(newValue.getUnitPrice()));
            txtbrand.setText(newValue.getBrand());
        }

    }



    public void txtModelNoSearchOnAction(ActionEvent actionEvent) {
        try {
        if(Pattern.compile("^[A-z 0-9 -)]{4,}$").matcher(txtmodelno.getText()).matches()){
                ItemDTO search = bo.search(txtmodelno.getText());
                loadUnqData(search);
        }else {
            txtmodelno.setFocusColor(Paint.valueOf("red"));
            txtmodelno.requestFocus();
        }
        } catch (Exception ex) {
            txtmodelno.setFocusColor(Paint.valueOf("red"));
            txtmodelno.requestFocus();
        }

    }

    public void EnterOnAction(ActionEvent actionEvent) {
        btnAddOnAction( actionEvent);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if(Pattern.compile("^[A-z 0-9 -)]{4,}$").matcher(txtmodelno.getText()).matches()){
                ButtonType yes=new ButtonType("yes",ButtonBar.ButtonData.OK_DONE);
                ButtonType no=new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Delete this " + txtmodelno.getText() + "  Item?", yes, no);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.orElse(no)==yes){
                    boolean delete = bo.delete(txtmodelno.getText());
                    if(delete){
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png",txtmodelno.getText()+"  item Was Delete Successfully!","Successfully!");
                        btnClearOnAction(actionEvent);
                        LoadAllItem();
                    }
                    else {
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtmodelno.getText()+"  Item Was Delete fail... try again!","Warning!");
                    }
                }
            }else {
                txtmodelno.setFocusColor(Paint.valueOf("red"));
                txtmodelno.requestFocus();
            }

        } catch (Exception ex){
            CheckItemDetail();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if(CheckItemDetail()){
            ButtonType yes=new ButtonType("yes",ButtonBar.ButtonData.OK_DONE);
            ButtonType no=new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Update " + txtmodelno.getText() + " This ModelNo?", yes, no);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.orElse(no)==yes){
                try {
                    boolean update= bo.update(new ItemDTO(txtmodelno.getText(),txtdescription.getText(),Integer.parseInt(txtqtyonhand.getText()),txtbrand.getText(),txtcolor.getText(),cmbtype.getValue().toString(),txtwidth.getText(),txtheight.getText(),Double.parseDouble(txtdiscount.getText()),Double.parseDouble(txtunitprice.getText())));
                    if(update){
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png",txtmodelno.getText()+"  Item is Update Successfully!","Successfully!");
                        btnClearOnAction(actionEvent);
                        LoadAllItem();
                    }else {
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtmodelno.getText()+" Item is Update fail..!Try again..!","Warning!");

                    }
                } catch (Exception ex){
                    CheckItemDetail();
                }
            }
        }


    }

    public  void btnAddOnAction(ActionEvent actionEvent) {
        try {
        if(CheckItemDetail()){
            ButtonType yes=new ButtonType("yes",ButtonBar.ButtonData.OK_DONE);
            ButtonType no=new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Add " + txtmodelno.getText() + " This ModelNo?", yes, no);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.orElse(no)==yes){
                boolean add = bo.add(new ItemDTO(txtmodelno.getText(),txtdescription.getText(),Integer.parseInt(txtqtyonhand.getText()),txtbrand.getText(),txtcolor.getText(),cmbtype.getValue().toString(),txtwidth.getText(),txtheight.getText(),Double.parseDouble(txtdiscount.getText()),Double.parseDouble(txtunitprice.getText())));
                if(add){
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png",txtmodelno.getText()+"  Item Added Successfully!","Successfully!");
                    btnClearOnAction(actionEvent);
                    LoadAllItem();
                }else { Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtmodelno.getText()+"  Item Added Fail..! Try again..!","Warning!");

                }
            }
        }
        }catch (SQLException ex){
            switch (ex.getErrorCode()){
                case 1062 :{
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtmodelno.getText()+"  Item Already added  in the System! ","Warning!");
                    break;
                }
                case 1452 :{
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",txtmodelno.getText()+"  Item Not Registered  in the System!","Warning!");
                    break;
                }
            }
        }
        catch (Exception ex) {
            CheckItemDetail();
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtmodelno.clear();
        txtdescription.clear();
        cmbtype.setValue(null);
        txtheight.clear();
        txtwidth.clear();
        txtcolor.clear();
        txtdiscount.clear();
        txtqtyonhand.clear();
        txtunitprice.clear();
        txtbrand.clear();
    }
}
