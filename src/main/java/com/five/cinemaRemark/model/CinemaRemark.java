package com.five.cinemaRemark.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by msi on 2017/6/7.
 */
@Entity
public class CinemaRemark implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private int cinemaId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Timestamp time;

    public CinemaRemark() {}

    public CinemaRemark(int userId, int cinemaId, String content) {
        this.userId = userId;
        this.cinemaId = cinemaId;
        this.content = content;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
