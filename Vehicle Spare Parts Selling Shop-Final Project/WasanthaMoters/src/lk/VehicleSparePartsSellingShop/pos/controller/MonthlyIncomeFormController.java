package lk.VehicleSparePartsSellingShop.pos.controller;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.MonthlyIncomeBo;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.MonthlyIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.io.IOException;
import java.util.function.Predicate;

public class MonthlyIncomeFormController {
    public TableView<MonthlyIncomeDTO> tblmonthlyincome;
    public TableColumn colincome;
    public AnchorPane root;
    public TextField txtsearchbar;
    public TableColumn colmonthname;
    public TableColumn colyearname;
    public TableColumn colno;
    MonthlyIncomeBo monthlyIncomebo = BoFactory.getInstance().getBo(BoFactory.BoType.MonthlyIncome);
    ObservableList<MonthlyIncomeDTO> monthlyIncome= FXCollections.observableArrayList();
    public void initialize(){
        colno.setCellValueFactory(new PropertyValueFactory<>("No"));
        colyearname.setCellValueFactory(new PropertyValueFactory<>("YearName"));
        colmonthname.setCellValueFactory(new PropertyValueFactory<>("MonthName"));
        colincome.setCellValueFactory(new PropertyValueFactory<>("Income"));
        LoadAllMonthlyIncome();
        Search();


}

    private void Search() {
        FilteredList<MonthlyIncomeDTO> filteredListdata=new FilteredList<>(monthlyIncome, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super MonthlyIncomeDTO>) income->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(income.getYearName().contains(newValue)){

                        return true;
                    }else if(income.getMonthName().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<MonthlyIncomeDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblmonthlyincome.comparatorProperty());
            tblmonthlyincome.setItems(sortedList);
        });

    }

    private void LoadAllMonthlyIncome() {
        monthlyIncome.clear();
        try {
         monthlyIncome = monthlyIncomebo.getMonthlyIncome();
            tblmonthlyincome.setItems(monthlyIncome);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }
    }

}
