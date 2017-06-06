package com.five.user.model;

import javax.servlet.http.HttpSession;

/**
 * Created by haoye on 17-6-6.
 */
// status 1 is ok, not 1 error
public class MyMessage {

    private int status;
    private String message;

    public MyMessage() {}

    public MyMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
