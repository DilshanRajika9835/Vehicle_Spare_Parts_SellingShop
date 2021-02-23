package lk.VehicleSparePartsSellingShop.pos.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.custom.AllPartsBo;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.io.IOException;

public class DefaultFormController {
    public TableView <ItemDTO>tblcar;
    public TableColumn colcarmodelno;
    public TableColumn colcardescription;
    public TableColumn colcarqtyonhand;
    public TableView  <ItemDTO>tblvan;
    public TableColumn colvanmodelno;
    public TableColumn colvandescription;
    public TableColumn colvanqtyonhand;
    public TableView  <ItemDTO>tblbus;
    public TableColumn colbusmodelno;
    public TableColumn colbusdescription;
    public TableColumn colbusqtyonhand;
    public TableView  <ItemDTO>tblthreeweel;
    public TableColumn colthreewheelmodelno;
    public TableColumn colthreewheeldescription;
    public TableColumn colthreewheelqtyonhand;
    public TableView  <ItemDTO> tbllorry;
    public TableColumn collorrymodelno;
    public TableColumn collorrydescription;
    public TableColumn collorryqtyonhand;
    public TableView  <ItemDTO>tblmoterbike;
    public TableColumn colmoterbikemodelno;
    public TableColumn colmoterbikedescription;
    public TableColumn colmoterbikeqtyonhand;
    public AnchorPane root;
    AllPartsBo allPartsbo = BoFactory.getInstance().getBo(BoFactory.BoType.Allparts);

    public void initialize() {

        colbusmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        colbusdescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colbusqtyonhand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        LoadBusQtyOnHand();

        colvanmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        colvandescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colvanqtyonhand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        LoadVanQtyOnHand();

        collorrymodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        collorrydescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        collorryqtyonhand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        LoadLorryQtyOnHand();

        colthreewheelmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        colthreewheeldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colthreewheelqtyonhand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        LoadThreeWheelQtyOnHand();

        colcarmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        colcardescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colcarqtyonhand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        LoadCarQtyOnHand();

        colmoterbikemodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        colmoterbikedescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colmoterbikeqtyonhand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        LoadMoterBikeQtyOnHand();

    }

    private void LoadMoterBikeQtyOnHand() {
        try {
            ObservableList<ItemDTO> moterBike = allPartsbo.getItemAvailibility("MoterBike");
            tblmoterbike.setItems(moterBike);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }
    }

    private void LoadCarQtyOnHand() {
        try {
            ObservableList<ItemDTO> car = allPartsbo.getItemAvailibility("Car");
            tblcar.setItems(car);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }
    }

    private void LoadThreeWheelQtyOnHand() {
        try {
            ObservableList<ItemDTO> threewheel = allPartsbo.getItemAvailibility("ThreeWheel");
            tblthreeweel.setItems(threewheel);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }
    }

    private void LoadLorryQtyOnHand() {
        try {
            ObservableList<ItemDTO> lorry = allPartsbo.getItemAvailibility("Lorry");
            tbllorry.setItems(lorry);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }
    }

    private void LoadVanQtyOnHand() {
        try {
            ObservableList<ItemDTO> van = allPartsbo.getItemAvailibility("Van");
            tblvan.setItems(van);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }
    }

    private void LoadBusQtyOnHand() {
        try {
            ObservableList<ItemDTO> bus = allPartsbo.getItemAvailibility("Bus");
            tblbus.setItems(bus);
        } catch (Exception ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }
    }


    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
   Stage stage= (Stage) root.getScene().getWindow();
   stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/VehicleSparePartsSellingShop/pos/view/MainForm.fxml"))));


    }
}
