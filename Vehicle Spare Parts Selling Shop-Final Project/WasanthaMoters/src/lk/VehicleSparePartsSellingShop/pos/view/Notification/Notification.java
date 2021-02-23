package lk.VehicleSparePartsSellingShop.pos.view.Notification;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;



public class Notification {
    public  static  void getNotification(String url,String text,String title){
        Image image =new Image(url);
        Notifications notifications= Notifications.create();
       notifications.graphic(new ImageView(image));
       //notifications.position(Pos.BASELINE_RIGHT);
        notifications.text(text);
        notifications.title(title);
       // notifications.darkStyle();
        notifications.hideAfter(Duration.seconds(3));
        notifications.show();

    }
}
