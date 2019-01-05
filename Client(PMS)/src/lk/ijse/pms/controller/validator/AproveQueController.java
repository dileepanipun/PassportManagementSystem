package lk.ijse.pms.controller.validator;

import com.jfoenix.controls.JFXCheckBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pms.custom.NotificationController;
import lk.ijse.pms.dto.ClientDTO;
import lk.ijse.pms.dto.PassportDTO;
import lk.ijse.pms.observer.Observer;
import lk.ijse.pms.proxy.ProxyHandeler;
import lk.ijse.pms.reservation.Reservation;
import lk.ijse.pms.service.ServiceFactory;
import lk.ijse.pms.service.custom.ClientService;
import lk.ijse.pms.service.custom.PassportService;

import java.io.Serializable;
import java.net.URL;
import java.rmi.Remote;
import java.util.*;

public class AproveQueController implements Initializable, Observer, Reservation, Remote, Serializable {

    @FXML
    private TableView<PassportDTO> tblPassportDetails;

    @FXML
    private Label lblApplicationID;

    @FXML
    private Label lblNIC;

    @FXML
    private Label lblFullName;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblDOB;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblBOnoDistrict;

    @FXML
    private Label lblProfession;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblMobileNo;

    @FXML
    private Label lblDCno;

    @FXML
    private JFXCheckBox checkCurrentPassport;

    @FXML
    private JFXCheckBox checkOriginalBC;

    @FXML
    private JFXCheckBox checkStudioDetails;

    @FXML
    private JFXCheckBox checkMerrageCetificate;

    @FXML
    private JFXCheckBox checkProffessionalCetificate;

    private PassportDTO approvablePassport = null;

    public AproveQueController() throws Exception {
    }

    @FXML
    void apporvePassport(ActionEvent event) throws Exception {

        if (approvablePassport != null){

            Random random = new Random();
            int randomNumber = random.nextInt(98786723);

            approvablePassport.setPassportID("" + randomNumber);
            approvablePassport.setPoliceApprove(true);
            approvablePassport.setStateOfPassport("PASSPORT_APPROVED");

            System.out.println(approvablePassport);

            PassportService passportService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.PASSPORT);
            boolean resp = passportService.updatePassport(approvablePassport);

            if (resp){
                loadAllPasport();
                NotificationController.createSuccesful("Successfull", "Passport Approved "+approvablePassport.getPassportID());
            }else {
                NotificationController.createError("Error", "Passport is not Approved "+approvablePassport.getPassportID());
                loadAllPasport();
            }

        }else {

            NotificationController.createError("Error", "Please select passport first");
        }

    }

    @FXML
    void passportDetaliSelected(MouseEvent event) throws Exception {

        PassportDTO selectedItem = tblPassportDetails.getSelectionModel().getSelectedItem();

        approvablePassport = selectedItem;

        ClientService clientService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CLIENT);
        ClientDTO clientDTO = clientService.searchClient(approvablePassport.getClientNic());

        lblApplicationID.setText(approvablePassport.getApplicationID());
        lblNIC.setText(approvablePassport.getClientNic());
        lblFullName.setText(clientDTO.getFullName());
        lblAddress.setText(clientDTO.getAddress());
        lblDOB.setText(clientDTO.getDob());
        lblGender.setText(clientDTO.getGender());
        lblBOnoDistrict.setText(clientDTO.getbODetails());

        lblProfession.setText(clientDTO.getProffession());
        lblEmail.setText(clientDTO.getEmail());
        lblMobileNo.setText(clientDTO.getTelephone());
        lblDCno.setText(clientDTO.getdCitizen());

        if (selectedItem.getPassportID().equals("NO_RESOURCE_FOUND")){
            checkCurrentPassport.setSelected(false);
        }else {
            checkCurrentPassport.setSelected(true);
        }

        if (selectedItem.getPhotoDetail().equals("NO_RESOURCE_FOUND")){
            checkStudioDetails.setSelected(false);
        }else {
            checkStudioDetails.setSelected(true);
        }

        if (selectedItem.getProfCetificate().equals("NO_RESOURCE_FOUND")){
            checkProffessionalCetificate.setSelected(false);
        }else {
            checkProffessionalCetificate.setSelected(true);
        }

        if (selectedItem.getOriginalBC().equals("NO_RESOURCE_FOUND")){
            checkOriginalBC.setSelected(false);
        }else {
            checkOriginalBC.setSelected(true);
        }

        if (selectedItem.getMerrageCetificate().equals("NO_RESOURCE_FOUND")){
            checkMerrageCetificate.setSelected(false);
        }else {
            checkMerrageCetificate.setSelected(true);
        }
    }

    @FXML
    void removeApplication(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            PassportService passportService = ProxyHandeler.getInstance().getSuperService(ProxyHandeler.ServiceTypes.PASSPORT);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    loadAllPasport();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        },10000,10000);

        try {
            loadAllPasport();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadAllPasport() throws Exception {
        PassportService passportService = ProxyHandeler.getInstance().getSuperService(ServiceFactory.ServiceTypes.PASSPORT);
        List<PassportDTO> allNotValidPassport = passportService.getAllNotValidPassport();
        tblPassportDetails.setItems(FXCollections.observableArrayList(allNotValidPassport));
        tblPassportDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("applicationID"));
        tblPassportDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("clientNic"));
        tblPassportDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        tblPassportDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("documentType"));
        tblPassportDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("stateOfPassport"));
    }

    @Override
    public void update(String message) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    try {
                        loadAllPasport();
                    } catch (Exception e) {
                        NotificationController.createError("Error",e.getMessage());
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        PassportService passportService = ProxyHandeler.getInstance().getSuperService(ProxyHandeler.ServiceTypes.PASSPORT);
        return passportService.reserved(id);
    }

    @Override
    public boolean released(Object id) throws Exception {
        PassportService passportService = ProxyHandeler.getInstance().getSuperService(ProxyHandeler.ServiceTypes.PASSPORT);
        return passportService.released(id);
    }
}
