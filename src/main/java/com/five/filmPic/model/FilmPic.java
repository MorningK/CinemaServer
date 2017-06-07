package com.five.filmPic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by haoye on 17-6-7.
 */
@Entity
public class FilmPic implements Serializable {

    public static final int COVER = 1;
    public static final int STILL = 2;

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private int filmId;

    public FilmPic() {}

    public FilmPic(String path, int type, int filmId) {
        this.path = path;
        this.type = type;
        this.filmId = filmId;
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

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public String toString() {
        return "FilmPic{" +
                ", path='" + path + '\'' +
                ", type=" + type +
                ", filmId=" + filmId +
                '}';
    }
}
