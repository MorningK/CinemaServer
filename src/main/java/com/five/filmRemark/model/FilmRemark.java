package com.five.filmRemark.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by haoye on 17-6-6.
 */
@Entity
public class FilmRemark implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private int filmId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Timestamp time;

    public FilmRemark() {}

    public FilmRemark(int userId, int filmId, String content) {
        this.userId = userId;
        this.filmId = filmId;
        this.content = content;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
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

    @Override
    public String toString() {
        return "filmRemark{" +
                "userId=" + userId +
                ", filmId=" + filmId +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
