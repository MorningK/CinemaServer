package com.five.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by msi on 2017/6/6.
 */
@Entity
public class Wallet implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false, unique = true)
    private int userId;

    public Wallet() {}

    public Wallet(int userId, double balance) {
        this.balance = balance;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Wallet:{" +
                "\"balance\":"+Double.toString(balance)+
                "}";
    }
}
