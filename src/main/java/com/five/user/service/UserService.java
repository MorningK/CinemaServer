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
    public boolean active(String code);
    public MyMessage changePassword(int userId, String oldPass, String newPass);
    public MyMessage forgetPassword(String username);
    public MyMessage confirmCode(String username, String code);
    public MyMessage resetPassword(String username, String password, String code);
}
