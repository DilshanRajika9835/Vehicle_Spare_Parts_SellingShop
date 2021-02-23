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
import lk.VehicleSparePartsSellingShop.pos.bo.AnnualIncomeBo;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.dto.AnnualIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.util.function.Predicate;


public class AnnualIncomeFormController {
    public TableView <AnnualIncomeDTO>tblannualincome;
    public TableColumn colyear;
    public TableColumn colincome;
    public AnchorPane root;
    public TextField txtsearchbar;
    public TableColumn colno;
    AnnualIncomeBo annualincomebo = BoFactory.getInstance().getBo(BoFactory.BoType.AnnualIncome);
    ObservableList<AnnualIncomeDTO>annualIncome= FXCollections.observableArrayList();
    public void initialize(){
   colno.setCellValueFactory(new PropertyValueFactory<>("No"));
   colyear.setCellValueFactory(new PropertyValueFactory<>("Year"));
   colincome.setCellValueFactory(new PropertyValueFactory<>("Income"));
   GetAnnualIncome();
   Search();
}

    private void Search() {
        FilteredList<AnnualIncomeDTO> filteredListdata=new FilteredList<>(annualIncome, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super AnnualIncomeDTO>) income->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(income.getYear().contains(newValue)){

                        return true;
                    }else if(income.getYear().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<AnnualIncomeDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblannualincome.comparatorProperty());
            tblannualincome.setItems(sortedList);
        });

    }

    private void GetAnnualIncome(){
        annualIncome.clear();
        try {
            annualIncome = annualincomebo.getAnnualIncome();
            tblannualincome.setItems(annualIncome);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");
        }

    }

}
