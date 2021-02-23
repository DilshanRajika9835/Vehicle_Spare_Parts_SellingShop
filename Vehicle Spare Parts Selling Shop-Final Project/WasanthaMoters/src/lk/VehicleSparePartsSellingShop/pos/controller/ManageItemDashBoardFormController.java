package lk.VehicleSparePartsSellingShop.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.io.IOException;

public class ManageItemDashBoardFormController {
    public AnchorPane root;
public void initialize(){
    InitUI("ManageItemForm.fxml");
}
    public void InitUI(String location) {
        try {
            this.root.getChildren().clear();
            this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/VehicleSparePartsSellingShop/pos/view/" + location)));
        } catch (IOException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");

        }

    }

    public void AddNewEmployeeOnAction(ActionEvent actionEvent) {
        InitUI("EmployeeForm.fxml");
    }

    public void AddNewSupplierOnAction(ActionEvent actionEvent) {
        InitUI("SupplierForm.fxml");
    }

    public void DailyIncomeOnAction(ActionEvent actionEvent) {
        InitUI("DailyIncomeForm.fxml");
    }

    public void MonthlyIncomeOnAction(ActionEvent actionEvent) {
        InitUI("MonthlyIncomeForm.fxml");
    }

    public void AnnualInComeOnAction(ActionEvent actionEvent) {
        InitUI("AnnualIncomeForm.fxml");
    }


    public void LoginHistoryOnAction(ActionEvent actionEvent) {
        InitUI("LoginHistoryForm.fxml");
    }


    public void AddSupplyDetailsOnAction(ActionEvent actionEvent) {
       InitUI("SupplyDetailForm.fxml");

    }

    public void ReturnItemHistoryOnAction(ActionEvent actionEvent) {
        InitUI("ReturnItemHistoryForm.fxml");
    }

    public void OrderPaymentHistoryOnAction(ActionEvent actionEvent) {
        InitUI("PaymentHistoryForm.fxml");
    }

    public void NewItemOnAction(ActionEvent actionEvent) { InitUI("ManageItemForm.fxml"); }
    public void ExitOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/VehicleSparePartsSellingShop/pos/view/MainForm.fxml"))));

    }

    public void MostMoviableItemOnAction(ActionEvent actionEvent) {
        InitUI("MostMoviableForm.fxml");
    }

    public void LeastMoviableItemOnAction(ActionEvent actionEvent) {
        InitUI("LeastMoviableForm.fxml");
    }
}




