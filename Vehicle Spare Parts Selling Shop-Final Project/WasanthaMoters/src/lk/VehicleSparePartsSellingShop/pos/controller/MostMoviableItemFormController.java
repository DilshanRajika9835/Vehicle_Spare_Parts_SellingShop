package lk.VehicleSparePartsSellingShop.pos.controller;


import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.MostMoviableItemBo;
import lk.VehicleSparePartsSellingShop.pos.dto.MonthlyIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.MostMoviableItemDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.sql.SQLException;
import java.util.function.Predicate;


public class MostMoviableItemFormController {
    public AnchorPane root;
    public TableColumn colmodelno;
    public TableColumn colsalse;
    public TableView <MostMoviableItemDTO>tblmostmoviabl;
    public TableColumn coldescription;
    public TableColumn coltype;
    public TableColumn colbrand;
    public TextField txtsearchbar;
    public TableColumn colno;
    MostMoviableItemBo mostMoviableItembo = BoFactory.getInstance().getBo(BoFactory.BoType.MostMoviable);
    ObservableList<MostMoviableItemDTO> mostMoviableItems= FXCollections.observableArrayList();
    public void initialize(){
        colno.setCellValueFactory(new PropertyValueFactory<>("No"));
        colmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colbrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        colsalse.setCellValueFactory(new PropertyValueFactory<>("Salse"));
        getAllMostMoviableItems();
        Search();

    }

    private void Search() {
        FilteredList<MostMoviableItemDTO> filteredListdata=new FilteredList<>(mostMoviableItems, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super MostMoviableItemDTO>) item->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(item.getModelNo().contains(newValue)){

                        return true;
                    }else if(item.getDescription().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(item.getType().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(item.getBrand().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<MostMoviableItemDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblmostmoviabl.comparatorProperty());
            tblmostmoviabl.setItems(sortedList);
        });

    }

    private void getAllMostMoviableItems() {
        mostMoviableItems.clear();
        try {
          mostMoviableItems = mostMoviableItembo.getMostMoviableItems();
            tblmostmoviabl.setItems(mostMoviableItems);

        } catch (SQLException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        } catch (ClassNotFoundException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");         }
    }

}
