package lk.VehicleSparePartsSellingShop.pos.controller;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;

import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.LoginBo;

import lk.VehicleSparePartsSellingShop.pos.dto.LoginDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;
import lk.VehicleSparePartsSellingShop.pos.view.TM.DateTime;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


public class MainFormController {


    public JFXTextField txtusername;
    public CheckBox checkshowpassword;
    public JFXPasswordField txtpassword;
    public JFXTextField txtshowpassword;
    public AnchorPane root;
    public Label lbltime;
    public Label lbldate;
    String loginType = "Login Fail";
    String Userpassword;
    LoginBo loginbo = BoFactory.getInstance().getBo(BoFactory.BoType.Login);

    private String CheckingUserPassword(String username) {

        try {
            return loginbo.CheckID(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "NotFound";
    }

    public void initialize() {
        txtpassword.setVisible(true);
        txtshowpassword.setVisible(false);
        LoadTime();

    }

    private void LoadTime() {
        Thread timerThread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000); //1 second
                } catch (InterruptedException ex) {
                    Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lbltime.setText(time);
                });
            }
        });   timerThread.start();//start the thread and its ok

        String date = DateTime.getDateTime("yyyy/MMMMM/dd");
        lbldate.setText(date);

    }



    public void Login() {

        try {
            String date = DateTime.getDateTime("yyyy/MM/dd");
            String time = DateTime.getDateTime("HH:mm:ss a");
            loginbo.addLoginHistory(new LoginDTO(txtusername.getText(), Userpassword, date, time, loginType));
        } catch (SQLException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");        }
        catch (ClassNotFoundException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                    ex.getMessage(),"Warning!");

        }

    }


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        checkBoxShowPassWordOnAction(new ActionEvent());
        String name = txtusername.getText();
        String password = Userpassword;
        if (Pattern.compile("^[A-Z]{1,5}.[A-z ]{1,}|^[A-Z]{1,5}.[A-z]{1,}$").matcher(name).matches()) {
            if (Pattern.compile("^[0-9]{9}[V]{1}|[0-9]{12}$").matcher(password).matches()) {
                switch (txtusername.getText()) {
                    case "Admin": {
                        if (txtusername.getText().equalsIgnoreCase("Admin") && Userpassword.equalsIgnoreCase("411971001998")) {
                            loginType = "Login Successfully";
                            Login();
                            this.root.getChildren().clear();
                            this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/VehicleSparePartsSellingShop/pos/view/ManageItemDashBoardForm.fxml")));
                            FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
                            fadeIn.setFromValue(0.0);
                            fadeIn.setToValue(1.0);
                            fadeIn.play();



                        } else {
                            new Alert(Alert.AlertType.WARNING, " Unknown UserName or PassWord please  try again! ", ButtonType.OK).show();
                        }
                        break;
                    }
                    default: {
                        if (Userpassword.equalsIgnoreCase(CheckingUserPassword(txtusername.getText()))) {
                            loginType = "Login Successfully";
                            Login();
                            this.root.getChildren().clear();
                            this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/VehicleSparePartsSellingShop/pos/view/DashBoardForm.fxml")));
                            FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
                            fadeIn.setFromValue(0.0);
                            fadeIn.setToValue(1.0);
                            fadeIn.play();



                        } else {
                            new Alert(Alert.AlertType.WARNING, " Unknown UserName or PassWord please  try again! ", ButtonType.OK).show();
                        }

                    }
                }

            } else {
                Login();

                txtpassword.requestFocus();
                txtpassword.setFocusColor(Paint.valueOf("red"));
            }

        } else {
            Login();
            txtusername.requestFocus();
            txtusername.setFocusColor(Paint.valueOf("red"));
        }


    }

    public void checkBoxShowPassWordOnAction(ActionEvent actionEvent) {
        if (checkshowpassword.isSelected()) {
            txtshowpassword.setVisible(true);
            txtshowpassword.setText(Userpassword);
            txtpassword.setVisible(false);

        } else {
            txtpassword.setVisible(true);
            txtpassword.setText(Userpassword);
            txtshowpassword.setVisible(false);

        }
    }

    public void txtpasswordOnAction(KeyEvent keyEvent) {
        Userpassword = txtshowpassword.getText();
    }

    public void txtpasswordfieldonAction(KeyEvent keyEvent) {
        Userpassword = txtpassword.getText();
    }
}
