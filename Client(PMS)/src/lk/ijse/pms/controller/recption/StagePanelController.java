package lk.ijse.pms.controller.recption;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pms.custom.NotificationController;

import java.net.URL;
import java.util.ResourceBundle;

public class StagePanelController implements Initializable {

    @FXML
    private AnchorPane panelLoader;

    @FXML
    void logoutOnAction(ActionEvent event) {
        ButtonType configAlert = NotificationController.createConfigAlert();

        if (configAlert == ButtonType.OK){
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            AnchorPane devicePane =  FXMLLoader.load(this.getClass().getResource("/lk/ijse/pms/view/reception/FirstSection.fxml"));
            panelLoader.getChildren().setAll(devicePane);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
