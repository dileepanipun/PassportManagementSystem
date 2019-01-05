package lk.ijse.psm.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Passport implements Serializable {

    @Id
    private String applicationID;
    private String serviceType;
    private String documentType;
    private String passportID;
    private String originalBC;
    private String photoDetail;
    private String photoLocation;
    private String merrageCetificate;
    private String profCetificate;
    private String stateOfPassport;
    private String errorLog;
    private boolean policeApprove;
    private String clientNic;

    public Passport() {
    }

    public Passport(String applicationID, String serviceType, String documentType, String passportID, String originalBC, String photoDetail, String photoLocation, String merrageCetificate, String profCetificate, String stateOfPassport, String errorLog, boolean policeApprove, String clientNic) {
        this.applicationID = applicationID;
        this.serviceType = serviceType;
        this.documentType = documentType;
        this.passportID = passportID;
        this.originalBC = originalBC;
        this.photoDetail = photoDetail;
        this.photoLocation = photoLocation;
        this.merrageCetificate = merrageCetificate;
        this.profCetificate = profCetificate;
        this.stateOfPassport = stateOfPassport;
        this.errorLog = errorLog;
        this.policeApprove = policeApprove;
        this.clientNic = clientNic;
    }

    public String getClientNic() {
        return clientNic;
    }

    public void setClientNic(String clientNic) {
        this.clientNic = clientNic;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public String getOriginalBC() {
        return originalBC;
    }

    public void setOriginalBC(String originalBC) {
        this.originalBC = originalBC;
    }

    public String getPhotoDetail() {
        return photoDetail;
    }

    public void setPhotoDetail(String photoDetail) {
        this.photoDetail = photoDetail;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    public String getMerrageCetificate() {
        return merrageCetificate;
    }

    public void setMerrageCetificate(String merrageCetificate) {
        this.merrageCetificate = merrageCetificate;
    }

    public String getProfCetificate() {
        return profCetificate;
    }

    public void setProfCetificate(String profCetificate) {
        this.profCetificate = profCetificate;
    }

    public String getStateOfPassport() {
        return stateOfPassport;
    }

    public void setStateOfPassport(String stateOfPassport) {
        this.stateOfPassport = stateOfPassport;
    }

    public String getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(String errorLog) {
        this.errorLog = errorLog;
    }

    public boolean isPoliceApprove() {
        return policeApprove;
    }

    public void setPoliceApprove(boolean policeApprove) {
        this.policeApprove = policeApprove;
    }
}
