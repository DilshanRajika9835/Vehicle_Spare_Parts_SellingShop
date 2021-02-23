package lk.VehicleSparePartsSellingShop.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.VehicleSparePartsSellingShop.pos.bo.BoFactory;
import lk.VehicleSparePartsSellingShop.pos.bo.LoginBo;
import lk.VehicleSparePartsSellingShop.pos.dto.LoginDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;
import lk.VehicleSparePartsSellingShop.pos.view.TM.DateTime;

import java.io.IOException;
import java.sql.SQLException;

public class ManageItemLoginFormController {
    public TextField txtpassword;
    public TextField txtusername;
    public AnchorPane root;
    public String loginType;
    LoginBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.Login);
    public void btnloginonaction(ActionEvent actionEvent) throws IOException {


        if(txtpassword.getText().length()>0&txtusername.getText().length()>0){
            if(txtusername.getText().trim().length()>0 && txtpassword.getText().trim().equalsIgnoreCase(CheckingUserPassword(txtusername.getText()))){
                loginType="Login Successfully";
                Login();
                this.root.getChildren().clear();
                this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/VehicleSparePartsSellingShop/pos/view/ManageItemDashBoardForm.fxml")));


            }else{
                new Alert(Alert.AlertType.WARNING,"Invalid UserName Or Password", ButtonType.OK).show();
                loginType="Login fail";
                Login();
            }

        }else{
            new Alert(Alert.AlertType.WARNING,"Enter UserName Or Password", ButtonType.OK).show();
            loginType="Login fail";
            Login();
        }
    }

    private String CheckingUserPassword(String username) {

        try {
            return  bo.CheckID(username);
        } catch (SQLException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        } catch (ClassNotFoundException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");

    }
        return "NotFound";

    }


    public  void Login()  {
        try {
            String date= DateTime.getDateTime("yyyy/MM/dd");
            String time= DateTime.getDateTime("HH:mm:ss a");
            bo.addLoginHistory(new LoginDTO(txtusername.getText(),txtpassword.getText(),date,time,loginType));
        } catch (SQLException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        } catch (ClassNotFoundException ex) {
            Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",ex.getMessage(),"Warning!");
        }

    }

    }


