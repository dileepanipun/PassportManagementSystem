package lk.ijse.pms.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminStageController implements Initializable {

    @FXML
    private AnchorPane panelLoader;

    @FXML
    void customers(ActionEvent event) {

    }

    @FXML
    void dailymain(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void other(ActionEvent event) {

    }

    @FXML
    void passport(ActionEvent event) {

    }

    @FXML
    void userData(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane devicePane = null;
        try {
            devicePane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pms/view/admin/DashboardAdmin.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panelLoader.getChildren().setAll(devicePane);
    }
}
