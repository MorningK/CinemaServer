package com.five.user.service;

import com.five.user.model.MyMessage;
import com.five.user.model.User;

import javax.servlet.http.HttpSession;

/**
 * Created by haoye on 17-6-6.
 */
public interface UserService {
    public MyMessage doRegister(User user);
    public MyMessage doLogin(String username, String password, HttpSession session);
    public User findById(int id);
    public User findByUsername(String username);
    void reload();
}
