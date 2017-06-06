package com.five.order.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by haoye on 17-6-6.
 */
@Entity
public class Reservation {

    public static final int NOTPAID = 1;
    public static final int PAID = 2;
    public static final int OUTOFDATE = 3;

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private Date time;
    @Column(nullable = false)
    private String sitting;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private int filmSessionId;
    @Column(nullable = false)
    private int userId;

    public Reservation() {}

    public Reservation(String sitting, double price, int filmSessionId, int userId) {
        this.time = new Date();
        this.sitting = sitting;
        this.price = price;
        this.status = NOTPAID;
        this.filmSessionId = filmSessionId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSitting() {
        return sitting;
    }

    public void setSitting(String sitting) {
        this.sitting = sitting;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFilmSessionId() {
        return filmSessionId;
    }

    public void setFilmSessionId(int filmSessionId) {
        this.filmSessionId = filmSessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "time=" + time +
                ", sitting='" + sitting + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", filmSessionId=" + filmSessionId +
                ", userId=" + userId +
                '}';
    }
}
