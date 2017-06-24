package com.five.user.utils;


import com.five.user.model.User;
import com.five.user.service.UserService;

/**
 * Created by msi on 2017/6/23.
 */
public class CodeThread implements Runnable {
    private String username;
    private UserService userService;

    public CodeThread(String username, UserService userService) {
        this.username = username;
        this.userService = userService;
    }

    @Override
    public void run() {
        userService.checkCodeOutOfTime(username);
    }
}
