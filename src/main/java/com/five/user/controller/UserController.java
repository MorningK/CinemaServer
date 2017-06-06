package com.five.user.controller;

import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by haoye on 17-6-6.
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public MyMessage register(User user) {
        MyMessage message = userService.doRegister(user);
        return message;
    }

    @PostMapping("/login")
    public MyMessage login(String username, String password, HttpSession session) {
        MyMessage message = userService.doLogin(username, password, session);
        System.out.println(session.getAttribute("userId"));
        return message;
    }

}
