package lk.ijse.pms.controller.recption;

import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.pms.custom.IDGenerator;
import lk.ijse.pms.custom.NotificationController;
import lk.ijse.pms.custom.Validation;
import lk.ijse.pms.dto.ClientDTO;
import lk.ijse.pms.dto.PassportDTO;
import lk.ijse.pms.observer.Observer;
import lk.ijse.pms.proxy.ProxyHandeler;
import lk.ijse.pms.reservation.Reservation;
import lk.ijse.pms.service.custom.ClientService;
import lk.ijse.pms.service.custom.PassportService;

import javax.imageio.ImageIO;

public class FirstSectionController implements Initializable, Reservation, Observer, Serializable {

    @FXML
    private JFXRadioButton radioNorml;

    @FXML
    private ToggleGroup serviceType;

    @FXML
    private JFXRadioButton radioOneDay;

    @FXML
    private JFXComboBox<String> cmboDocumentType;

    @FXML
    private JFXTextField txtPayVal;

    @FXML
    private Label lblEase;

    @FXML
    private Label lblPrize;

    @FXML
    private JFXComboBox<String> comboCurrency;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtFullName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtBC_NoDistrict;

    @FXML
    private JFXRadioButton radioMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton radioFemale;

    @FXML
    private JFXRadioButton radioOther;

    @FXML
    private Label lblApplicationID;

    @FXML
    private JFXDatePicker dateDOB;

    @FXML
    private JFXTextField txtProfession;

    @FXML
    private JFXTextField txtMobileNo;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtDuelCitizen;

    @FXML
    private JFXCheckBox checkDuelCitizen;

    @FXML
    private JFXComboBox<String> comboStudioName;

    @FXML
    private JFXTextField txtCurrentPassport;

    @FXML
    private JFXCheckBox checkCurrentPassport;

    @FXML
    private JFXTextField txtPhotoID;

    @FXML
    private JFXTextField txtOriginalBC;

    @FXML
    private JFXTextField txtMerrageCertificate;

    @FXML
    private JFXCheckBox checkMerrageCertificate;

    @FXML
    private JFXTextField txtProfCetificate;

    @FXML
    private JFXCheckBox checkProffessionalCetificate;

    @FXML
    private JFXButton btnMerrageCetificate;

    @FXML
    private JFXButton btnProfCetificate;

    @FXML
    private JFXButton btnPassportUpload;

    @FXML
    private ImageView imgViewPhoto;

    @FXML
    private JFXTextField txtPhotoLocation;

    private static String photo = "";

    @FXML
    void birthCetificateUpload(ActionEvent event) {

        FileChooser fileChooser =  new FileChooser();
        fileChooser.setTitle("Select Birth Certificate");

        boolean image_files = fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        Stage stage = new Stage();
        stage.centerOnScreen();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null ){
            txtOriginalBC.setText(file.getPath());
        }

    }

    @FXML
    void createPassport(ActionEvent event) throws Exception {

        RadioButton service = (RadioButton) serviceType.getSelectedToggle();
        String serviceType = service.getText();
        String docType = cmboDocumentType.getSelectionModel().getSelectedItem();

        double paymentValue = Double.parseDouble(txtPayVal.getText());
        LocalDate now = LocalDate.now();
        String today = now.toString();

        String nicNo = txtNic.getText();
        String surname = txtFullName.getText();
        String address = txtAddress.getText();
        String dob = dateDOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String bcNo = txtBC_NoDistrict.getText();
        RadioButton genderRadio = (RadioButton) gender.getSelectedToggle();
        String gender = genderRadio.getText();
        String profession = txtProfession.getText();
        String email = txtEmail.getText();
        String mobile = txtMobileNo.getText();
        String dCID = txtDuelCitizen.getText();

        String applicationId = lblApplicationID.getText();
        String currentpassport = txtCurrentPassport.getText();
        String originalBirthCetificate = txtOriginalBC.getText();
        String photoDetail = txtPhotoID.getText() + comboStudioName.getSelectionModel().getSelectedItem().toString();
        String merrageCeitificate = txtMerrageCertificate.getText();
        String professionalCetificate = txtProfCetificate.getText();


        ClientDTO clientDTO = new ClientDTO(nicNo, surname, address, dob, bcNo, gender, profession, email, mobile, dCID);
        System.out.println(clientDTO);

        PassportDTO passportDTO = new PassportDTO(
                applicationId,
                serviceType,
                docType,
                "NOT REGISTERD",
                originalBirthCetificate,
                photoDetail,
                photo,
                merrageCeitificate,
                professionalCetificate,
                "Waiting For Approvemnet",
                "NO_ERRORS",
                false,
                today,
                paymentValue,
                nicNo
        );
        System.out.println(passportDTO);

        ClientService clientService = ProxyHandeler.getInstance().getSuperService(ProxyHandeler.ServiceTypes.CLIENT);
        PassportService passportService = ProxyHandeler.getInstance().getSuperService(ProxyHandeler.ServiceTypes.PASSPORT);


        boolean clientAdded = clientService.addClient(clientDTO);
        boolean passportAdded = passportService.addPassport(passportDTO);

        if (clientAdded && passportAdded){
            NotificationController.createSuccesful("Succesfull", "added");
        }else {
            NotificationController.createError("Error", "Something went wrong");
        }
    }

    @FXML
    void currntPassportUpload(ActionEvent event) {
        FileChooser fileChooser =  new FileChooser();
        fileChooser.setTitle("Select Current Passport Copy");

        boolean image_files = fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        Stage stage = new Stage();
        stage.centerOnScreen();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null ){
            txtCurrentPassport.setText(file.getPath());
        }
    }

    @FXML
    void merrageCetificateUpload(ActionEvent event) {
        FileChooser fileChooser =  new FileChooser();
        fileChooser.setTitle("Select Merrage Certificate");

        boolean image_files = fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        Stage stage = new Stage();
        stage.centerOnScreen();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null ){
            txtMerrageCertificate.setText(file.getPath());
        }
    }

    @FXML
    void profCetificateUpload(ActionEvent event) {
        FileChooser fileChooser =  new FileChooser();
        fileChooser.setTitle("Select Professional Certificate");

        boolean image_files = fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        Stage stage = new Stage();
        stage.centerOnScreen();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null ){
            txtProfCetificate.setText(file.getPath());
        }
    }

    @FXML
    void resetAll(ActionEvent event) throws Exception {

        setDefault();

    }

    @FXML
    void resetApplicentDetails(ActionEvent event) throws Exception {
        resetClientDetails();
    }

    @FXML
    void resetCitizenandProffession(ActionEvent event) {
        resetCitizenDetials();
    }

    @FXML
    void resetDocuments(ActionEvent event) {
        resetDocumentDetails();
    }

    @FXML
    void resetServiceType(ActionEvent event) {
        resetDocumentTypeDetails();
    }

    @FXML
    void stateChanged(InputMethodEvent event) {

    }

    @FXML
    void addressOnAction(ActionEvent event) {
        if (Validation.isEmpety(txtAddress.getText())){
            NotificationController.createError("Error","Incorrect Address");
        }else {
            txtBC_NoDistrict.requestFocus();
            txtBC_NoDistrict.selectAll();
        }
    }

    @FXML
    void fullNameOnAction(ActionEvent event) {
        if (Validation.isEmpety(txtFullName.getText())){
            NotificationController.createError("Error","Incorrect Name");
        }else {
            txtAddress.requestFocus();
            txtAddress.selectAll();
        }
    }

    @FXML
    void nicOnAction(ActionEvent event) {
        if (Validation.isEmpety(txtNic.getText())){
            NotificationController.createError("Error","Incorrect National ID Card No");
        }else {
            txtFullName.requestFocus();
            txtFullName.selectAll();
        }
    }

    @FXML
    void countEase(KeyEvent event) {
        if (!Validation.numbersOnly(txtPayVal.getText())){
            NotificationController.createError("Invalid Input","Input again");
            txtPayVal.requestFocus();
            txtPayVal.selectAll();
        }else {
            countEaseVal();
        }
    }

    private void countEaseVal() {
        double pspPrize = Double.parseDouble(lblPrize.getText());
        double inputVal = Double.parseDouble(txtPayVal.getText());
        lblEase.setText(String.valueOf((inputVal - pspPrize)));
    }

    @FXML
    void countEaseOnAction(ActionEvent event) {

        double payed = Double.parseDouble(txtPayVal.getText());
        double papPrize = Double.parseDouble(lblPrize.getText());

        if (!(payed == 0) && !(payed < papPrize)){
            txtNic.requestFocus();
            txtNic.selectAll();

        }else {
            txtPayVal.requestFocus();
            txtPayVal.selectAll();
            NotificationController.createError("Incompleted Transaction","Check amount");
        }
    }

    @FXML
    void studioAcnoladgeUpload(ActionEvent event) throws Exception {

        FileChooser fileChooser =  new FileChooser();
        fileChooser.setTitle("Select Professional Certificate");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        Stage stage = new Stage();
        stage.centerOnScreen();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null ){
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
            imgViewPhoto.setImage(writableImage);
            photo = file.getPath();
        }else {
            imgViewPhoto.setImage(new Image("/lk/ijse/pms/assest/default-non-user-no-photo.jpg"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cmboDocumentType.getItems().setAll(
                "All Countries",
                "Ordinary passports - All Countries",
                "Official passports - All countries",
                "Official - All Countries",
                "Emergency Certificates"
        );
        comboStudioName.getItems().setAll(
                "NEW SHALIKA STUDIO",
                "SURENDRA VIDEO & DIGITAL STUDIO",
                "RAMANAN DIGITAL STUDIO (R2 STUDIO)",
                "THE LION FOTO","ODISROO STUDIO",
                "WEST END STUDIO",
                "NADEEKA COLOUR STUDIO AND COMMUNICATION",
                "SUMITH WEDDINGS STUDIO DIGITAL COLOR LAB & COM."
        );
        comboCurrency.getItems().setAll("LKR","$US","Euro");

        cmboDocumentType.getSelectionModel().selectFirst();
        comboStudioName.getSelectionModel().selectFirst();
        comboCurrency.getSelectionModel().selectFirst();

        setDisablDocument();

        imgViewPhoto.setImage(new Image("/lk/ijse/pms/assest/default-non-user-no-photo.jpg"));

        radioFemale.setUserData("female");
        radioMale.setUserData("male");
        radioOther.setUserData("other");
        radioNorml.setUserData("Normal");
        radioOneDay.setUserData("OneDay");

        try {
            setDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkCurrentPassport.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkCurrentPassport.isSelected()){
                    txtCurrentPassport.setDisable(false);
                    btnPassportUpload.setDisable(false);
                }else {
                    txtCurrentPassport.setDisable(true);
                    btnPassportUpload.setDisable(true);
                }
            }
        });

        checkMerrageCertificate.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkMerrageCertificate.isSelected()){
                    txtMerrageCertificate.setDisable(false);
                    btnMerrageCetificate.setDisable(false);
                }else {
                    txtMerrageCertificate.setDisable(true);
                    btnMerrageCetificate.setDisable(true);
                }
            }
        });

        checkProffessionalCetificate.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkProffessionalCetificate.isSelected()){
                    txtProfCetificate.setDisable(false);
                    btnProfCetificate.setDisable(false);
                }else {
                    txtProfCetificate.setDisable(true);
                    btnProfCetificate.setDisable(true);
                }
            }
        });

        checkDuelCitizen.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (checkDuelCitizen.isSelected()){
                    txtDuelCitizen.setDisable(false);
                }else {
                    txtDuelCitizen.setDisable(true);
                }
            }
        });

        cmboDocumentType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("All Countries")){
                    lblPrize.setText("20000");
                    txtPayVal.requestFocus();
                    txtPayVal.selectAll();
                    countEaseVal();
                }else if (newValue.equals("Ordinary passports - All Countries")){
                    lblPrize.setText("34000");
                    txtPayVal.requestFocus();
                    txtPayVal.selectAll();
                    countEaseVal();
                }else if (newValue.equals("Official passports - All countries")){
                    lblPrize.setText("45000");
                    txtPayVal.requestFocus();
                    txtPayVal.selectAll();
                    countEaseVal();
                }else if (newValue.equals("Official - All Countries")){
                    lblPrize.setText("56000");
                    txtPayVal.requestFocus();
                    txtPayVal.selectAll();
                    countEaseVal();
                }else if (newValue.equals("Emergency Certificates")){
                    lblPrize.setText("60000");
                    txtPayVal.requestFocus();
                    txtPayVal.selectAll();
                    countEaseVal();
                }else  {
                    lblPrize.setText("000");
                    txtPayVal.requestFocus();
                    txtPayVal.selectAll();
                    countEaseVal();
                }
            }
        });
    }

    private void setDisablDocument() {
        checkCurrentPassport.setSelected(false);
        txtCurrentPassport.setDisable(true);
        btnPassportUpload.setDisable(true);
        checkMerrageCertificate.setSelected(false);
        checkProffessionalCetificate.setSelected(false);
        checkDuelCitizen.setSelected(false);
        txtDuelCitizen.setDisable(true);
    }

    private void setDefault() throws Exception {

        resetDocumentTypeDetails();
        resetClientDetails();
        resetCitizenDetials();
        resetDocumentDetails();

    }

    private void resetDocumentTypeDetails() {
        radioNorml.setSelected(true);
        txtPayVal.setText("00.00");
        cmboDocumentType.getSelectionModel().selectFirst();
        comboCurrency.getSelectionModel().selectFirst();
    }

    private void resetClientDetails() throws Exception {
        lblApplicationID.setText(IDGenerator.getNewID("passport","applicationID","PSRT-012"));
        txtNic.setText("");
        txtFullName.setText("");
        txtAddress.setText("");
        txtBC_NoDistrict.setText("");
        dateDOB.setValue(LocalDate.now());
        radioMale.setSelected(true);
    }

    private void resetCitizenDetials() {
        txtProfession.setText("NO_JOB");
        txtEmail.setText("");
        txtMobileNo.setText("");
        checkDuelCitizen.setSelected(false);
        txtDuelCitizen.setText("NO-DUEL-CITIZENSHIP");
    }

    private void resetDocumentDetails() {
        checkCurrentPassport.setSelected(false);
        txtCurrentPassport.setText("No location found");
        txtOriginalBC.setText("No location found");
        txtPhotoID.setText("No location found");
        comboStudioName.getSelectionModel().selectFirst();
        checkMerrageCertificate.setSelected(false);
        txtMerrageCertificate.setText("No location found");
        checkProffessionalCetificate.setSelected(false);
        txtProfCetificate.setText("No location found");
        imgViewPhoto.setImage(new Image("/lk/ijse/pms/assest/default-non-user-no-photo.jpg"));
    }


    @Override
    public void update(String message) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    try {
                        NotificationController.createSuccesful("Alert", message);
                    }catch (Exception e){
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
