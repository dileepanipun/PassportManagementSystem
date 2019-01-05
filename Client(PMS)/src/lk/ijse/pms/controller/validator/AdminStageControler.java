package lk.ijse.pms.controller.validator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminStageControler implements Initializable {

    @FXML
    private AnchorPane panelLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            AnchorPane devicePane =  FXMLLoader.load(this.getClass().getResource("/lk/ijse/pms/view/validator/AproveQue.fxml"));
            panelLoader.getChildren().setAll(devicePane);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
