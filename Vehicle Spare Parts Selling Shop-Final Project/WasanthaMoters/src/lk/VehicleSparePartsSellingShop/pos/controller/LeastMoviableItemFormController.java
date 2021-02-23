package lk.VehicleSparePartsSellingShop.pos.controller;



import com.sun.org.apache.xerces.internal.util.PropertyState;
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
import lk.VehicleSparePartsSellingShop.pos.bo.LeastMoviableItemBo;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.LeastMoviableItemDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.sql.SQLException;
import java.util.function.Predicate;

public class LeastMoviableItemFormController {
    public TableView<LeastMoviableItemDTO> tblleastitem;
    public TableColumn colmodelno;
    public TableColumn colsalse;
    public AnchorPane root;
    public TextField txtsearchbar;
    public TableColumn coldescription;
    public TableColumn coltype;
    public TableColumn colbrand;
    public TableColumn colno;
    LeastMoviableItemBo leastMoviableItembo = BoFactory.getInstance().getBo(BoFactory.BoType.LeastMoviable);
    ObservableList<LeastMoviableItemDTO> leastMoviableItems= FXCollections.observableArrayList();

    public void initialize(){
        colno.setCellValueFactory(new PropertyValueFactory<>("No"));
        colmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colbrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        colsalse.setCellValueFactory(new PropertyValueFactory<>("Salse"));
        GetLeastMoviableItem();
        Search();

    }

    private void Search() {
        FilteredList<LeastMoviableItemDTO> filteredListdata=new FilteredList<>(leastMoviableItems, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super LeastMoviableItemDTO>) item->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(item.getModelno().contains(newValue)){

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
            SortedList<LeastMoviableItemDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblleastitem.comparatorProperty());
            tblleastitem.setItems(sortedList);
        });
    }

    private void GetLeastMoviableItem() {
        leastMoviableItems.clear();
        try {
           leastMoviableItems = leastMoviableItembo.getLeastMoviableItems();
            tblleastitem.setItems(leastMoviableItems);

        } catch (SQLException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");        }
        catch (ClassNotFoundException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");
        }
    }

}
