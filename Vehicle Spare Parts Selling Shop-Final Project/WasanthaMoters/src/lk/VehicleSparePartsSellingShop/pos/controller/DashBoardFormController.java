package lk.VehicleSparePartsSellingShop.pos.controller;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DashBoardFormController {


    public AnchorPane root;
    public  Label lbldate;
    public  Label lbltime;
    public ImageView ordericon;


    public void initialize()  {
        InitUi("DefaultForm.fxml");
        Thread timerThread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000); //1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lbltime.setText(time);
                });
            }
        });   timerThread.start();//start the thread and its ok

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        String format = sdf.format(date);
        lbldate.setText(format);
    }
   

    public void btnmoterbikepartsonaction(ActionEvent actionEvent)  {
        InitUi("MoterBikeForm.fxml"); }

    public void btncarpartsonaction(ActionEvent actionEvent)  { InitUi("CarForm.fxml"); }

    public void vehiclepartsonaction(ActionEvent actionEvent) { InitUi("VanForm.fxml"); }

    public void btnlorrypartsonaction(ActionEvent actionEvent) { InitUi("LorryForm.fxml"); }

    public void btnthreewheelonaction(ActionEvent actionEvent)  { InitUi("ThreeWheelForm.fxml"); }

    public void btnreturnpartsonaction(ActionEvent actionEvent)  { InitUi("ReturnForm.fxml"); }

    public void btnallpartsonaction(ActionEvent actionEvent)  { InitUi("AllPartsForm.fxml"); }

    public void btnorderonaction(ActionEvent actionEvent)  { InitUi("OrdersForm.fxml"); }

    public void btnbusonaction(ActionEvent actionEvent)  { InitUi("BusForm.fxml"); }

    public  void InitUi(String location) {
        try {
            this.root.getChildren().clear();
            this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/VehicleSparePartsSellingShop/pos/view/" +location)));
        } catch (IOException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }


    }

    public void lblHomeMouseClickOnAction(MouseEvent mouseEvent) {

            InitUi("DefaultForm.fxml");
    }
}
