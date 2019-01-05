package lk.ijse.pms.main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class admin extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pms/view/admin/AdminStage.fxml"));
        Scene scene = new Scene(root);

        scene.getStylesheets().add("lk/ijse/pms/css/stageStyle.css");
        scene.setFill(null);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });



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
