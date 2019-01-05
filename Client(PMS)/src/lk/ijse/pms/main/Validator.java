package lk.ijse.pms.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Validator extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pms/view/validator/AdminStage.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("/lk/ijse/pms/assest/logoPassport.png"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
