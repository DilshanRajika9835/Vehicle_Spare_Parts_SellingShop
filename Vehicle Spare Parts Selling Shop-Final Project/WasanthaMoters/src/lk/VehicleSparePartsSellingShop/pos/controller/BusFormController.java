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

import java.util.function.Predicate;

public class BusFormController {

    public TextField txtsearch;
    public TableView tblbus;
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

    private ObservableList<ItemDTO> allbus= FXCollections.observableArrayList();
    AllPartsBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.Allparts);


    public  void initialize(){
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

        LoadBusData();
    }

    private void LoadBusData() {
        allbus.clear();
        try {
            allbus = bo.getvehicle("Bus");
            tblbus.setItems(allbus);
            Search();
        } catch (Exception e) {
        }

    }

    private void Search() {
        FilteredList<ItemDTO> filteredListdata=new FilteredList<>(allbus, e->true);
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
            sortedList.comparatorProperty().bind(tblbus.comparatorProperty());
            tblbus.setItems(sortedList);
        });


    }
}
