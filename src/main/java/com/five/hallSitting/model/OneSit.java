package com.five.hallSitting.model;

/**
 * Created by haoye on 17-6-6.
 */
public class OneSit {

    private int x;
    private int y;
    private boolean valid;

    public OneSit(int x, int y, boolean valid) {
        this.x = x;
        this.y = y;
        this.valid = valid;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
