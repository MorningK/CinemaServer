package com.five.user.service;

import com.five.payment.model.Wallet;
import com.five.payment.service.PaymentService;
import com.five.user.dao.UserDao;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        List<User> users = userDao.findByUsername(user.getUsername());
        if (users.size() == 0) { // 没有找到，可以注册
            User registeredUser = userDao.save(user);

            MyMessage message = (MyMessage) paymentService.addWalletForNewUserById(registeredUser.getId(), 100);

            return new MyMessage(1, "注册成功," + message.getMessage());
        }
        return new MyMessage(0, "用户名已存在");
    }

    public MyMessage doLogin(String username, String password, HttpSession session) {
        List<User> users = userDao.findByUsername(username);
        MyMessage message = new MyMessage();
        message.setStatus(0);
        if (users.size() > 1) return new MyMessage(0,"用户名重复，请更改用户名");
        User user = users.get(0);
        if (users.size() == 0) {
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

    @Override
    public User findByUsername(String username) {
        List<User> users = userDao.findByUsername(username);
        if (users.size() == 0) { // 没有找到，可以注册
            return null;
        } else if (users.size() > 1) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public void reload() {
        userDao.reload();
    }

}
