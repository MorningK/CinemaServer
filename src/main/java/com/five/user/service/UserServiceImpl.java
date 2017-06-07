package com.five.user.service;

import com.five.payment.model.Wallet;
import com.five.payment.service.PaymentService;
import com.five.user.dao.UserDao;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 * Created by haoye on 17-6-6.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PaymentService paymentService;

    public MyMessage doRegister(User user) {
        User user1 = userDao.findByUsername(user.getUsername());
        if (user1 == null) { // 没有找到，可以注册
            User registeredUser = userDao.save(user);

            MyMessage message = (MyMessage) paymentService.addWalletForNewUserById(registeredUser.getId(), 100);

            return new MyMessage(1, "注册成功," + message.getMessage());
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

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

}
