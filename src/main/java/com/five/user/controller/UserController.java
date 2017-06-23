package com.five.user.controller;

import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserService;
import com.five.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by haoye on 17-6-6.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public MyMessage register(User user) {
        MyMessage message = userService.doRegister(user);
        return message;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MyMessage login(String username, String password, HttpSession session) {
        MyMessage message = userService.doLogin(username, password, session);
        System.out.println(session.getAttribute("userId"));
        return message;
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public @ResponseBody String active(@RequestParam(value="code", required=true)String code) {
        if (userService.active(code)) {
            return "激活成功";
        }
        return "激活失败";
    }

    @RequestMapping(value="/user/findUser", method = RequestMethod.GET)
    public MyMessage findUser(int userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return new MyMessage(0, "用户不存在");
        }
        return new MyMessage(1, user.getUsername());
    }


    @RequestMapping(value="/user/changePassword", method = RequestMethod.POST)
    public MyMessage changePassword(HttpSession session, String oldPass, String newPass) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return userService.changePassword(userId, oldPass, newPass);
    }

    @RequestMapping(value="/user/forgetPassword", method = RequestMethod.POST)
    public MyMessage forgetPassword(String username) {
        return userService.forgetPassword(username);
    }

    @RequestMapping(value="/user/confirmCode", method = RequestMethod.POST)
    public MyMessage confirmCode(String username, String code) {
        return userService.confirmCode(username, code);
    }

    @RequestMapping(value="/user/resetPassword", method = RequestMethod.POST)
    public MyMessage resetPassword(String username, String password, String code) {
        return userService.resetPassword(username, password, code);
    }
}
