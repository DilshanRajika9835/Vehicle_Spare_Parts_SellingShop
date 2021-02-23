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
import lk.VehicleSparePartsSellingShop.pos.bo.EmployeeBo;
import lk.VehicleSparePartsSellingShop.pos.dto.EmployeeDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public AnchorPane root;
    public TableView<EmployeeDTO> tblEmployee;
    public TableColumn colnicnumber;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn colphonenumber;
    public TableColumn colgender;
    public TableColumn colbirthday;
    public TableColumn colsalary;
    public TextField txtSearchbar;
    public DatePicker txtbirthday;
    public TableColumn colpost;
    public JFXTextField txtnic;
    public JFXTextField txtphonenumber;
    public JFXTextField txtempname;
    public JFXTextField txtaddress;
    public JFXTextField txtsalary;
    public JFXTextField txtpost;
    public JFXComboBox cmbgender;
    public TableColumn colno;
    EmployeeBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.Employee);
    ObservableList<EmployeeDTO> allemployee = FXCollections.observableArrayList();
    ObservableList genderlist=FXCollections.observableArrayList("Male","Female");
    public  void initialize(){

        colno.setCellValueFactory(new PropertyValueFactory<>("No"));
        colnicnumber.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colgender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        colbirthday.setCellValueFactory(new PropertyValueFactory<>("BirthDay"));
        colphonenumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        colpost.setCellValueFactory(new PropertyValueFactory<>("Post"));
        colsalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        cmbgender.setItems(genderlist);
        LoadAllEmployee();
        Search();
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            LoadUniqueData(newValue);
        });


    }

    private void Search() {
        FilteredList<EmployeeDTO> filteredListdata = new FilteredList<>(allemployee, e -> true);
        txtSearchbar.setOnKeyReleased(e -> {
            txtSearchbar.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredListdata.setPredicate((Predicate<? super EmployeeDTO>) emp -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowercasefilter = newValue.toLowerCase();
                    if (emp.getName().contains(newValue)) {
                        return true;
                    } else if (emp.getAddress().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    }
                    else if (emp.getPhoneNumber().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    } else if (emp.getNic().toLowerCase().contains(lowercasefilter)) {

                        return true;
                    }
                    return false;
                });
            }));
            SortedList<EmployeeDTO> sortedList = new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblEmployee.comparatorProperty());
            tblEmployee.setItems(sortedList);
        });

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((((observable, oldValue, newValue) -> {

            if (newValue != null) {
                LoadUniqueData(newValue);

            }
        })));
    }

    private void LoadAllEmployee() {
        try {
            allemployee.clear();
            allemployee = bo.getAll();
            tblEmployee.setItems(allemployee);
        } catch (Exception ex) {
        }

    }


    private void LoadUniqueData(EmployeeDTO newValue) {
        if(newValue!=null){
            txtnic.setText(newValue.getNic());
            txtphonenumber.setText(newValue.getNic());
            txtempname.setText(newValue.getName());
            txtaddress.setText(newValue.getAddress());
            txtbirthday.setValue(LocalDate.parse(newValue.getBirthDay()));
            txtphonenumber.setText(newValue.getPhoneNumber());
            txtpost.setText(newValue.getPost());
            txtsalary.setText(Double.toString(newValue.getSalary()));
            cmbgender.setValue(newValue.getGender());

        }
    }
    private boolean CheckDetail(){
        try {
            if (Pattern.compile("^[0-9]{9}[V]{1}|[0-9]{12}$").matcher(txtnic.getText()).matches()) {
                if (Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$").matcher(txtempname.getText()).matches()) {
                    if (Pattern.compile("^[A-z ,0-9,/-]{10,}|[ ]$").matcher(txtaddress.getText()).matches()) {
                        if (cmbgender.getSelectionModel().getSelectedIndex()!=-1) {
                            if (Pattern.compile("^[0-9]{10}$").matcher(txtphonenumber.getText()).matches()) {
                                if (txtbirthday.getValue().toString().length() > 0) {
                                    if (Pattern.compile("^[[0-9].[0-9]]{1,}$").matcher(txtsalary.getText()).matches()) {
                                        if (Pattern.compile("^[A-z]{5,}$").matcher(txtpost.getText()).matches()) {
                                            return true;

                                        } else {
                                            txtpost.setFocusColor(Paint.valueOf("red"));
                                            txtpost.requestFocus();
                                        }

                                    } else {
                                        txtsalary.setFocusColor(Paint.valueOf("red"));
                                        txtsalary.requestFocus();

                                    }

                                } else {
                                }
                            } else {
                                txtphonenumber.setFocusColor(Paint.valueOf("red"));
                                txtphonenumber.requestFocus();
                            }
                        } else {
                            cmbgender.setFocusColor(Paint.valueOf("red"));
                            cmbgender.requestFocus();
                        }
                    } else {
                        txtaddress.setFocusColor(Paint.valueOf("red"));
                        txtaddress.requestFocus();
                    }
                } else {
                    txtempname.setFocusColor(Paint.valueOf("red"));
                    txtempname.requestFocus();
                }
            } else {
                txtnic.setFocusColor(Paint.valueOf("red"));
                txtnic.requestFocus();
            }
        }catch (Exception ex){

        }
        return false;
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            if (CheckDetail()) {
                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure add this " + txtempname.getText() + " Employee?", ok, no);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.orElse(no) == ok) {
                        boolean added = bo.add(new EmployeeDTO(txtnic.getText(),
                                txtempname.getText(), txtaddress.getText(), cmbgender.getValue().toString(),
                                txtbirthday.getValue().toString(), txtphonenumber.getText(), txtpost.getText(),
                                Double.parseDouble(txtsalary.getText())));
                        if (added) {
                            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png",
                                    "Employee Added Successfully...", "Successfully!");
                            btnClearOnAction(actionEvent);
                            LoadAllEmployee();
                        }
                }
            }
            } catch (SQLException ex){
            switch (ex.getErrorCode()){
                case 1062 :{
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                            txtnic.getText()+" This Employee Already added  in the System! ","Warning!");
                    break;
                }
                case 1452 :{
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                            txtnic.getText()+" This Employee Not Registered  in the System!","Warning!");
                    break;
                }
            }

        } catch (Exception ex) {
            CheckDetail();

        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (CheckDetail()) {
                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure Update this " + txtempname.getText() + " Employee?", ok, no);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.orElse(no) == ok) {


                        boolean updated = bo.update(new EmployeeDTO(txtnic.getText(), txtempname.getText(), txtaddress.getText(), cmbgender.getValue().toString(), txtbirthday.getValue().toString(), txtphonenumber.getText(), txtpost.getText(), Double.parseDouble(txtsalary.getText())));
                        if (updated) {
                            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png", "Employee Update Successfully...", "Successfully!");
                            btnClearOnAction(actionEvent);
                            LoadAllEmployee();
                        } else {
                            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png", "Employee Update is Fail please Try Again...", "Warning!");
                        }
                }
            }
            } catch (Exception ex) {
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtnic.clear();
        txtempname.clear();
        txtaddress.clear();
        txtbirthday.setValue(null);
        txtphonenumber.clear();
        txtpost.clear();
        txtsalary.clear();
        cmbgender.setValue(null);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^[0-9]{9}[V]{1}|[0-9]{12}$").matcher(txtnic.getText()).matches()) {
                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure Update this " + txtempname.getText() + " Employee?", ok, no);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.orElse(no) == ok) {
                        boolean deleted = bo.delete(txtnic.getText());
                        if (deleted) {
                            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png", "Employee Delete Successfully!", "Successfully!");
                            btnClearOnAction(actionEvent);
                            LoadAllEmployee();
                        } else {
                            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png", "Employee Delete Fail..Try again!", "Warning!");
                        }
                }
            }else {
                txtnic.setFocusColor(Paint.valueOf("red"));
                txtnic.requestFocus();
            }
        }catch (Exception ex){
            txtnic.setFocusColor(Paint.valueOf("red"));
            txtnic.requestFocus();
        }

    }

    public void NICSearchOnAction(ActionEvent actionEvent) {
        try {
        if(Pattern.compile("^[0-9]{9}[V]{1}|[0-9]{12}$").matcher(txtnic.getText()).matches()){

                EmployeeDTO employee = bo.search(txtnic.getText());
                if( employee!=null){
                    txtnic.setText(employee.getNic());
                    txtempname.setText(employee.getName());
                    txtaddress.setText(employee.getAddress());
                    cmbgender.setValue(employee.getGender());
                    txtbirthday.setValue(LocalDate.parse(employee.getBirthDay()));
                    txtphonenumber.setText(employee.getPhoneNumber());
                    txtpost.setText(employee.getPost());
                    txtsalary.setText(Double.toString(employee.getSalary()));
                }else {
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png", txtnic.getText()+" This Employee Not Found!", "Warning!");
                }

        }else {
            txtnic.setFocusColor(Paint.valueOf("red"));
            txtnic.requestFocus();
        }
        } catch (Exception ex) {
            txtnic.setFocusColor(Paint.valueOf("red"));
            txtnic.requestFocus();
        }
    }

    public void EmployeeEnterAddOnAction(ActionEvent actionEvent) {
        btnAddOnAction(actionEvent);
    }
}
