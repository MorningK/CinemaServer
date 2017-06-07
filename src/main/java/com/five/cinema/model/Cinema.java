package com.five.cinema.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by msi on 2017/6/6.
 */
@Entity
public class Cinema implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private double longtitude;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private int citycode;

    public Cinema() {}

    public Cinema(String name, String address, String phone, double longtitude, double latitude, int cityCode) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.citycode = cityCode;
    }



    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "\"id\":"+id+
                ", \"name\":\""+name+"\""+
                ", \"address\":\""+address+"\""+
                ", \"phone\":\""+phone+"\""+
                ", \"longtitude\":"+Double.toString(longtitude)+
                ", \"latitude\":"+Double.toString(latitude)+
                ", \"CityCode\":"+ Integer.toString(citycode)+
                "}";
    }

    public int getCitycode() {
        return citycode;
    }

    public void setCitycode(int citycode) {
        this.citycode = citycode;
    }
}
