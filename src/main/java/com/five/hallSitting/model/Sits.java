package com.five.hallSitting.model;

import java.util.Arrays;

/**
 * Created by haoye on 17-6-6.
 */
public class Sits {

    private boolean isFull;
    private OneSit[] sits;

    public Sits(boolean isFull, OneSit[] sits) {
        this.isFull = isFull;
        this.sits = sits;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public OneSit[] getSits() {
        return sits;
    }

    public void setSits(OneSit[] sits) {
        this.sits = sits;
    }

}
