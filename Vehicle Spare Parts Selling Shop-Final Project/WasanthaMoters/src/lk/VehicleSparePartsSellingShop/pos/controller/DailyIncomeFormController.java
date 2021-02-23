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
import lk.VehicleSparePartsSellingShop.pos.bo.custom.DailyIncomeBo;
import lk.VehicleSparePartsSellingShop.pos.dto.AnnualIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.DailyIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Predicate;

public class DailyIncomeFormController {
    public TableView<DailyIncomeDTO> tbldailyincome;
    public TableColumn colincome;
    public AnchorPane root;
    public TextField txtsearchbar;
    public TableColumn colyear;
    public TableColumn colmonthname;
    public TableColumn colweek;
    public TableColumn colday;
    public TableColumn colno;
    ObservableList<DailyIncomeDTO>dailyincomelist= FXCollections.observableArrayList();
    DailyIncomeBo dailyIncomebo = BoFactory.getInstance().getBo(BoFactory.BoType.DailyIncome);
    public  void initialize(){


        colno.setCellValueFactory(new PropertyValueFactory<>("No"));
        colyear.setCellValueFactory(new PropertyValueFactory<>("Year"));
        colmonthname.setCellValueFactory(new PropertyValueFactory<>("Monthname"));
        colweek.setCellValueFactory(new PropertyValueFactory<>("WeekName"));
        colday.setCellValueFactory(new PropertyValueFactory<>("Day"));
        colincome.setCellValueFactory(new PropertyValueFactory<>("Income"));
        LoadAllData();
        Search();

    }

    private void Search() {
        FilteredList<DailyIncomeDTO> filteredListdata=new FilteredList<>(dailyincomelist, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super DailyIncomeDTO>) income->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(income.getDay().contains(newValue)){

                        return true;
                    }else if(income.getWeekName().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    else if(income.getMonthname().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<DailyIncomeDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tbldailyincome.comparatorProperty());
            tbldailyincome.setItems(sortedList);
        });
    }

    private void LoadAllData() {
        try {
            ObservableList<DailyIncomeDTO> dailyIncome = dailyIncomebo.dailyIncome();
            tbldailyincome.setItems(dailyIncome);
        } catch (SQLException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");
        } catch (ClassNotFoundException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");
        }

    }

}
