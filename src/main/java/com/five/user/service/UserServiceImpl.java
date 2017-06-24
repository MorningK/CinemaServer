package com.five.user.service;

import com.five.payment.model.Wallet;
import com.five.payment.service.PaymentService;
import com.five.user.dao.UserDao;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.five.user.utils.CodeUtil.codeMap;
import static com.five.user.utils.CodeUtil.generateRandomSixCode;
import static com.five.user.utils.CodeUtil.secondCode;

/**
 * Created by haoye on 17-6-6.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final String serverIp = "172.18.69.249";

    @Autowired
    private UserDao userDao;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private LocalIp localIp;

    private MailThreadPool mailThreadPool = MailThreadPool.getInstance();

    public MyMessage doRegister(User user) {
        if (user == null) {
            return new MyMessage(0, "user 不能为空");
        }
        String username = user.getUsername();
        String password  = user.getPassword();
        String email = user.getEmail();
        if (username == null) {
            return new MyMessage(0, "用户名不能为空");
        } else if (password == null) {
            return new MyMessage(0, "密码不能为空");
        } else if (email == null) {
            return new MyMessage(0, "邮箱不能为空");
        }
//        List<User> usersByUsername = userDao.findByUsername(user.getUsername());
        userDao.refreshOne(user);
        User userByUsername = userDao.findByUsername(username);
        User userByEmail = userDao.findbyEmail(email);
        if (userByUsername != null) { // 没有找到，可以注册
            return new MyMessage(0, "用户名已存在");
        } else if (userByEmail != null) {
            return new MyMessage(0, "邮箱已存在");
        }

        String code = CodeUtil.generateUniqueCode();
        String content = "";
        try {
            content = "<html><head></head><body><h1>请点击连接激活</h1><h3><a href='http://"
                    + serverIp + "/active?code="
                    + code + "'>http://" + serverIp + "/active?code=" + code + "</href></h3></body></html>";

        } catch (Exception e) {
            e.printStackTrace();
        }
        int pos = mailThreadPool.createThread(email, "Five电影售票激活邮件", content);
        MailUtil mailUtil = mailThreadPool.getThread(pos);
        new Thread(mailUtil).start();
        user.setCode(code);

        User registeredUser = userDao.save(user);

        MyMessage message = (MyMessage) paymentService.addWalletForNewUserById(registeredUser.getId(), 100);
        return new MyMessage(1, "注册成功," + message.getMessage());
    }

    public MyMessage doLogin(String username, String password, HttpSession session) {
        User users = userDao.findByUsername(username);
        MyMessage message = new MyMessage();
        if (users == null) {
            message.setMessage("用户不存在");
        } else if (!users.getPassword().equals(password)) {
            message.setMessage("密码错误");
        } else {
            if (users.isState() == User.ACTIVE) {
                message.setStatus(1);
                message.setMessage("登陆成功");
                session.setAttribute("userId", users.getId());
            } else {
                message.setMessage("未激活");
            }
        }
        return message;
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }

    @Override
    public void reload() {
        userDao.reload();
    }

    @Override
    public boolean active(String code) {
        User user = userDao.findByCode(code);
        if (null != user) {
            user.setState(User.ACTIVE);
            user.setCode(null);
            userDao.save(user);
            return true;
        }
        return false;
    }

    @Override
    public MyMessage changePassword(int userId, String oldPass, String newPass) {
        User user = userDao.findById(userId);
        if (user == null) {
            return new MyMessage(0, "无此用户");
        }
        if (!user.getPassword().equals(oldPass)) {
            return new MyMessage(0, "密码错误");
        }
        user.setPassword(newPass);
        userDao.save(user);
        return new MyMessage(1, "修改成功");
    }

    @Override
    public MyMessage forgetPassword(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return new MyMessage(0, "无此用户");
        }
        if (!user.isState()) {
            return new MyMessage(0,"用户未激活");
        }
        String randomCode = "";
        if (CodeUtil.codeMap.containsKey(user.getUsername())) {
            randomCode = CodeUtil.codeMap.get(user.getUsername());
        } else {
            randomCode = generateRandomSixCode();
            CodeUtil.codeMap.put(user.getUsername(), randomCode);
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.schedule(new CodeThread(user.getUsername()), 3, TimeUnit.MINUTES);
        }
        String content = "Welcom to use our service.\n Your code is " + randomCode + ".\n有效时间：3分钟。\n请尽快使用。";
        String subject = "Five电影售票验证码";
        int pos = mailThreadPool.createThread(user.getEmail(), subject, content);
        MailUtil mailUtil = mailThreadPool.getThread(pos);
        new Thread(mailUtil).start();

        return new MyMessage(1, "验证码已发送到邮箱");
    }

    @Override
    public MyMessage confirmCode(String username, String code) {
        if (CodeUtil.codeMap.containsKey(username)) {
            String actualCode = codeMap.get(username);
            if (actualCode.equals(code)) {
                String newCode = "";
                if (secondCode.containsKey(username)) {
                    newCode = secondCode.get(username);
                } else {
                    newCode = generateRandomSixCode();
                    secondCode.put(username, newCode);
                }
                return new MyMessage(1, newCode);
            } else {
                return new MyMessage(0,"验证码不正确");
            }
        } else {
            return new MyMessage(0,"此用户没有申请忘记密码");
        }
    }

    @Override
    public MyMessage resetPassword(String username, String password, String code) {
        if (secondCode.containsKey(username)) {
            if (secondCode.get(username).equals(code)) {
                User user = userDao.findByUsername(username);
                user.setPassword(password);
                userDao.save(user);
                return new MyMessage(1,"密码修改成功");
            } else {
                return new MyMessage(0,"次级验证码验证失败");
            }
        } else {
            return new MyMessage(0, "用户未验证成功");
        }
    }


}
