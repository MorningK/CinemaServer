package com.five.user;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.payment.model.Wallet;
import com.five.payment.service.PaymentService;
import com.five.user.dao.UserDao;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2017/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class UserTester {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PaymentService paymentService;

    private List<User> users = new ArrayList<>();

    @Before
    public void prepareData() {
        /*
        * Register 10 user
        * */
        users = DataCreator.prepareUser(1);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
//            userDao.save(user);
            MyMessage message = userService.doRegister(user);
            if (message.getStatus() != 1) {
                System.out.println(message.getMessage());
            }
        }
//        userService.reload();
    }

    private Object[] userToArray(User a) {
        return new Object[] {
                a.getId(),
                a.getUsername(),
                a.getPassword()
        };
    }

    @Test
    public void cacheTest() throws Exception {
        User user1 = users.get(0);
//        User q1 = userService.findByUsername(user1.getUsername());
        User q1 = userService.findById(user1.getId());
        System.out.println("first : " + q1.getUsername());

        User q2 = userService.findById(user1.getId());
        System.out.println("second : " + q2.getUsername());
    }

    @Test
    public void queryByUsernameTest() throws Exception {
        for (int i = 0; i < users.size(); i++) {
            User expt = users.get(i);
            User actul = userService.findByUsername(expt.getUsername());
            Assert.assertArrayEquals(userToArray(expt), userToArray(actul));
        }
//        userService.reload();
    }

    @Test
    public void queryByUserIdTest() throws Exception {
        for (int i = 0; i < users.size(); i++) {
            User expt = users.get(i);
            User actul = userService.findById(expt.getId());
            Assert.assertArrayEquals(userToArray(expt), userToArray(actul));
        }
    }

    @Test
    public void walletIsExistTest() throws Exception {
        for (int i = 0; i < users.size(); i++) {
            User expt = users.get(i);
            Wallet wallet = paymentService.findByUserId(expt.getId());
            Assert.assertNotNull(wallet);
            Assert.assertEquals(expt.getId(), wallet.getUserId());
        }
    }

    @Test
    public void registerDuplicateTest() throws Exception {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            MyMessage message = userService.doRegister(user);
            Assert.assertEquals(0, message.getStatus());
        }
    }

    @Test
    public void loginTest() throws Exception {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            HttpSession session = new MockHttpSession();
            MyMessage message = userService.doLogin(user.getUsername(), user.getPassword(), session);
            Assert.assertEquals(1, message.getStatus());
            Assert.assertEquals(user.getId(), session.getAttribute("userId"));
        }
    }

}
