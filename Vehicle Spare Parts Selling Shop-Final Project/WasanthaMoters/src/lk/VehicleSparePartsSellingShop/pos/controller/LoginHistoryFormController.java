package lk.VehicleSparePartsSellingShop.pos.controller;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.LoginBo;
import lk.VehicleSparePartsSellingShop.pos.dto.LoginHistoryDTO;

import java.sql.SQLException;
import java.util.function.Predicate;

public class LoginHistoryFormController {
    public AnchorPane root;
    public TableView<LoginHistoryDTO> tblloginhistory;
    public TableColumn colloginID;
    public TableColumn colusername;
    public TableColumn colpassword;
    public TableColumn collogindate;
    public TableColumn collogintime;
    public TableColumn colloginstates;
    public TextField txtsearchbar;
    LoginBo loginbo = BoFactory.getInstance().getBo(BoFactory.BoType.Login);
    ObservableList<LoginHistoryDTO> loginHistory= FXCollections.observableArrayList();
    public  void initialize(){
      colloginID.setCellValueFactory(new PropertyValueFactory<>("LoginID"));
      colusername.setCellValueFactory(new PropertyValueFactory<>("UserName"));
      colpassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
      collogindate.setCellValueFactory(new PropertyValueFactory<>("LoginDate"));
      collogintime.setCellValueFactory(new PropertyValueFactory<>("LoginTime"));
      colloginstates.setCellValueFactory(new PropertyValueFactory<>("LoginStates"));
      LoadLoginHistory();



    }

    private void LoadLoginHistory() {
        try {
            loginHistory.clear();
           loginHistory = loginbo.getLoginHistory();
           tblloginhistory.setItems(loginHistory);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Search();
    }

    private void Search() {

        FilteredList<LoginHistoryDTO> filteredListdata=new FilteredList<>(loginHistory, e->true);
        txtsearchbar.setOnKeyReleased(e->{
            txtsearchbar.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super LoginHistoryDTO>) login->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(login.getLoginDate().contains(newValue)){

                        return true;
                    }else if(login.getUserName().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }

                    return false;
                });
            } ));
            SortedList<LoginHistoryDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblloginhistory.comparatorProperty());
            tblloginhistory.setItems(sortedList);
        });

    }



}
