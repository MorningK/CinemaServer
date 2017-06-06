package com.five.cinemaRemark.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by msi on 2017/6/7.
 */
@Entity
public class CinemaRemark {
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
    private long time;

    public CinemaRemark() {}

    public CinemaRemark(int userId, int cinemaId, String content) {
        this.userId = userId;
        this.cinemaId = cinemaId;
        this.content = content;
        this.time = System.currentTimeMillis();
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
