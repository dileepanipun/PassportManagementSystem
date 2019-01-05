package lk.ijse.psm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Payment implements Serializable {

    @Id
    @GeneratedValue
    private int payid;
    private String date;
    private double prize;

    public Payment() {
    }

    public Payment(String date, double prize) {
        this.date = date;
        this.prize = prize;
    }

    public int getPayid() {
        return payid;
    }

    public void setPayid(int payid) {
        this.payid = payid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }
}
