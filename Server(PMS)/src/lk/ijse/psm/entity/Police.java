package lk.ijse.psm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Police implements Serializable {

    @Id
    private String polId;
    private String name;
    private String address;
    private String telephone;
    private double salary;
    private String username;
    private String password;

    public Police() {
    }

    public Police(String polId, String name, String address, String telephone, double salary, String username, String password) {
        this.polId = polId;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }

    public String getPolId() {
        return polId;
    }

    public void setPolId(String polId) {
        this.polId = polId;
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
}
