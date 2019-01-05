package lk.ijse.pms.dto;

import java.io.Serializable;

public class PoliceDTO implements Serializable {

    private String polID;
    private String name;
    private String address;
    private String telephone;
    private double salary;
    private String username;
    private String password;

    public PoliceDTO(String polID, String name, String address, String telephone, double salary, String username, String password) {
        this.polID = polID;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }

    public String getPolID() {
        return polID;
    }

    public void setPolID(String polID) {
        this.polID = polID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PoliceDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
