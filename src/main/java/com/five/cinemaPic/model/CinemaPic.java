package com.five.cinemaPic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by msi on 2017/6/7.
 */
@Entity
public class CinemaPic implements Serializable {

    public static int CP_COVER = 0;
    public static int CP_INSIDE = 1;

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private int cinemaId;

    public CinemaPic(String path, int type, int cinemaId) {
        this.path = path;
        this.type = type;
        this.cinemaId = cinemaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
