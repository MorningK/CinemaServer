package com.five.user.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by haoye on 17-6-6.
 */
@Entity
public class User implements Serializable {

    public static final boolean NOT_ACTIVE = false;
    public static final boolean ACTIVE = true;

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private boolean state; // 激活状态, 初始为0,1为已激活
    @Column
    private String code; // 随机激活码

    @Column
    private String firstCode; //验证码
    @Column
    private String secondCode; //次级验证码

    public User() {
        state = NOT_ACTIVE;
    }

    public User(String username, String password, String email, String code) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.code = code;
        state = NOT_ACTIVE;
        this.firstCode = null;
        this.secondCode = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                ", code='" + code + '\'' +
                '}';
    }


    public String getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode;
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }
}
