package com.five.user.service;

import com.five.user.dao.UserDao;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by haoye on 17-6-6.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public MyMessage doRegister(User user) {
        User user1 = userDao.findByUsername(user.getUsername());
        if (user1 == null) { // 没有找到，可以注册
            userDao.save(user);
            return new MyMessage(1, "注册成功");
        }
        return new MyMessage(0, "用户名已存在");
    }

    public MyMessage doLogin(String username, String password, HttpSession session) {
        User user = userDao.findByUsername(username);
        MyMessage message = new MyMessage();
        message.setStatus(0);
        if (user == null) {
            message.setMessage("用户不存在");
        } else if (!user.getPassword().equals(password)) {
            message.setMessage("密码错误");
        } else {
            message.setStatus(1);
            message.setMessage("登陆成功");
            session.setAttribute("userId", user.getId());
        }
        return message;
    }

}
