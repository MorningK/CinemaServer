package com.five.filmSession.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by msi on 2017/6/6.
 */
@Entity
public class FilmSession implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String hall;
    @Column(nullable = false)
    private Timestamp beginTime;
    @Column(nullable = false)
    private Timestamp endTime;
    @Column(nullable = false)
    private String classification;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int cinemaId;
    @Column(nullable = false)
    private int hallSittingId;
    @Column(nullable = false)
    private int filmId;

    public FilmSession() {}

    public FilmSession(String hall, Timestamp beginTime, Timestamp endTime, String classification, double price, int cinemaId, int hallSittingId, int filmId) {
        this.hall = hall;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.classification = classification;
        this.price = price;
        this.cinemaId = cinemaId;
        this.hallSittingId = hallSittingId;
        this.filmId = filmId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getHallSittingId() {
        return hallSittingId;
    }

    public void setHallSittingId(int hallSittingId) {
        this.hallSittingId = hallSittingId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public String toString() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String btime = format.format(beginTime), etime = format.format(endTime);
        return "FilmSession{" +
                "\"id\":"+id+
                ", \"hall\":\""+hall+"\""+
                ", \"beginTime\":\""+btime+"\""+
                ", \"endTime\":\""+etime+"\""+
                ", \"classification\":\""+classification+"\""+
                ", \"price\":"+Double.toString(price)+
                ", \"cinemaId\":"+ Integer.toString(cinemaId)+
                ", \"hallSittingId\":"+Integer.toString(hallSittingId)+
                ", \"filmId\":"+Integer.toString(filmId)+
                "}";
    }
}
