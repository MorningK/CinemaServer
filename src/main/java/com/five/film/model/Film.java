package com.five.film.model;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by msi on 2017/6/6.
 */

@Entity
@Indexed
public class Film implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Field
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private Timestamp publishTime;

    @Column(nullable = false)
    private double lastTime;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String language;

    public Film() {}

    public Film(String name, String summary, double score, String category, String nation, Timestamp publishTime, double lastTime, String actor, String director, String language) {
        this.name = name;
        this.summary = summary;
        this.score = score;
        this.category = category;
        this.nation = nation;
        this.publishTime = publishTime;
        this.lastTime = lastTime;
        this.actor = actor;
        this.director = director;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public double getLastTime() {
        return lastTime;
    }

    public void setLastTime(double lastTime) {
        this.lastTime = lastTime;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(publishTime);
        return "{" +
                "\"id\":"+Integer.toString(id)+
                ", \"name\":\""+name+"\""+
                ", \"summary\":\""+summary+"\""+
                ", \"score\":"+Double.toString(score)+
                ", \"category\":\""+category+"\""+
                ", \"nation\":\""+nation+"\""+
                ", \"publishTime\":\""+time+"\""+
                ", \"lastTime\":"+Double.toString(lastTime)+
                ", \"actor\":\""+actor+"\""+
                ", \"director\":\""+director+"\""+
                ", \"language\":\""+language+"\""+
                "}";
    }
}
