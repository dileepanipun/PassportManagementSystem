package lk.ijse.pms.dto;

import java.io.Serializable;

public class ClientDTO implements Serializable {

    private String nic;
    private String fullName;
    private String address;
    private String dob;
    private String bODetails;
    private String gender;
    private String proffession;
    private String email;
    private String telephone;
    private String dCitizen;

    public ClientDTO(String nic, String fullName, String address, String dob, String bODetails, String gender, String proffession, String email, String telephone, String dCitizen) {
        this.nic = nic;
        this.fullName = fullName;
        this.address = address;
        this.dob = dob;
        this.bODetails = bODetails;
        this.gender = gender;
        this.proffession = proffession;
        this.email = email;
        this.telephone = telephone;
        this.dCitizen = dCitizen;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getbODetails() {
        return bODetails;
    }

    public void setbODetails(String bODetails) {
        this.bODetails = bODetails;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProffession() {
        return proffession;
    }

    public void setProffession(String proffession) {
        this.proffession = proffession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getdCitizen() {
        return dCitizen;
    }

    public void setdCitizen(String dCitizen) {
        this.dCitizen = dCitizen;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "nic='" + nic + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", bODetails='" + bODetails + '\'' +
                ", gender='" + gender + '\'' +
                ", proffession='" + proffession + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dCitizen='" + dCitizen + '\'' +
                '}';
    }
}
