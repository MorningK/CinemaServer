package com.five.hallSitting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haoye on 17-6-6.
 */
@Entity
public class HallSitting {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String sit; // 已经有人的座位
//    @Column(nullable = false)
//    private int hallSitId;

    public HallSitting() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSit() {
        return sit;
    }

    public void setSit(String sit) {
        this.sit = sit;
    }

//    public int getHallSitId() {
//        return hallSitId;
//    }
//
//    public void setHallSitId(int hallSitId) {
//        this.hallSitId = hallSitId;
//    }

    @Override
    public String toString() {
        return "HallSitting{" +
                "sit='" + sit + '}';
    }
}
