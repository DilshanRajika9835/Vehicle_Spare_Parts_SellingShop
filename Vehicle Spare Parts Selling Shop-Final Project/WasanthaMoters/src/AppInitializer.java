import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppInitializer  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("lk/VehicleSparePartsSellingShop/pos/view/MainForm.fxml"))));
      // primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();

    }
}
