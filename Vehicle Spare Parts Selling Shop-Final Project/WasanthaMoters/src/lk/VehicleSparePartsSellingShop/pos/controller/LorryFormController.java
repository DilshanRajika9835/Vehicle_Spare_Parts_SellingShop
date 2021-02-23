package lk.VehicleSparePartsSellingShop.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.custom.AllPartsBo;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.util.function.Predicate;

public class LorryFormController {
    public TableView<ItemDTO> tbllorry;

    public TextField txtsearch;
    public TableColumn coluModelNo;
    public TableColumn colDescription;
    public TableColumn colQtyOnHand;
    public TableColumn colBrand;
    public TableColumn colColour;
    public TableColumn colType;
    public TableColumn colWidth;
    public TableColumn colHeight;
    public TableColumn colDiscount;
    public TableColumn colUnitPrice;
    public TableColumn colno;
    AllPartsBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.Allparts);
    ObservableList<ItemDTO> alllorry= FXCollections.observableArrayList();

    public void initialize() {
        colno.setCellValueFactory(new PropertyValueFactory<>("No"));
        coluModelNo.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("Color"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colWidth.setCellValueFactory(new PropertyValueFactory<>("Width"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("Height"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));

        LoadLorryData();
        Search();
    }

    private void LoadLorryData() {
        alllorry.clear();
        try {
            alllorry = bo.getvehicle("Lorry");
            tbllorry.setItems(alllorry);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");

        }
    }

    private void Search() {
        FilteredList<ItemDTO> filteredListdata=new FilteredList<>(alllorry, e->true);
        txtsearch.setOnKeyReleased(e->{
            txtsearch.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super ItemDTO>) part->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(part.getModelNo().contains(newValue)){

                        return true;
                    }else if(part.getModelNo().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    else if(part.getDescription().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    return false;
                });
            } ));
            SortedList<ItemDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tbllorry.comparatorProperty());
            tbllorry.setItems(sortedList);
        });


    }
}
