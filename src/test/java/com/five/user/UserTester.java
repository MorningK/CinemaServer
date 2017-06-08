package com.five.user;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.cinema.dao.CinemaDao;
import com.five.cinema.model.Cinema;
import com.five.cinema.repository.CinemaRepository;
import com.five.cinema.service.CinemaService;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

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
    private PaymentService paymentService;

    private List<User> users = new ArrayList<>();

    @Before
    public void prepareData() {
        /*
        * Register 10 user
        * */
        Random random = new Random();
        users = DataCreator.prepareUser(10);
        for (int i = 0; i < 10; i++) {
            User user = users.get(i);
            MyMessage message = userService.doRegister(user);
            if (message.getStatus() != 1) {
                System.out.println(message.getMessage());
            }
        }
        userService.reload();
    }

    private Object[] userToArray(User a) {
        return new Object[] {
                a.getId(),
                a.getUsername(),
                a.getPassword()
        };
    }



    @Test
    public void queryByUsernameTest() throws Exception {
        for (int i = 0; i < users.size(); i++) {
            User expt = users.get(i);
            User actul = userService.findByUsername(expt.getUsername());
            Assert.assertArrayEquals(userToArray(expt), userToArray(actul));
        }
        userService.reload();
    }


    /*todo
    * java.lang.ClassCastException: java.util.ArrayList cannot be cast to com.five.user.model.User
    *
    * userService.findById() --> userRepository.findById() will return a List<User> not a User
    * The reason is @Cacheable
    * The same as other findById(int id)
    * */
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
