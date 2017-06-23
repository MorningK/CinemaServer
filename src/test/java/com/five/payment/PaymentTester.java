package com.five.payment;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.filmSession.dao.FilmSessionDao;
import com.five.filmSession.model.FilmSession;
import com.five.hallSitting.dao.HallSittingDao;
import com.five.hallSitting.model.HallSitting;
import com.five.order.dao.OrderDao;
import com.five.order.model.Reservation;
import com.five.order.service.OrderService;
import com.five.order.utils.ClockThread;
import com.five.order.utils.ClockThreadPool;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2017/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class PaymentTester {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserService userService;
    @Autowired
    private HallSittingDao hallSittingDao;
    @Autowired
    private FilmSessionDao filmSessionDao;
    @Autowired
    private PaymentService paymentService;


    private List<User> users = new ArrayList<>();
    private List<HallSitting> hallSittings = new ArrayList<>();
    private List<FilmSession> filmSessions = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    @Before
    public void prepareData() {
        users = DataCreator.prepareUser(5);
        for (User user:users) {
            userService.doRegister(user);
        }

        hallSittings = DataCreator.prepareHallSitting(5, 5, 5);
        for (HallSitting hallSitting : hallSittings) {
            hallSittingDao.save(hallSitting);
        }

        filmSessions = DataCreator.prepareFilmSession(5, 5, 5);
        for (FilmSession filmSession : filmSessions) {
            filmSessionDao.save(filmSession);
        }

        reservations = DataCreator.prepareOrder(10, 5, 5, 5, 5);
        int i = 1;
        for (Reservation reservation : reservations) {
            orderService.makeOrder(reservation.getUserId(), reservation.getFilmSessionId(), reservation.getSitting(), reservation.getPrice());
            reservation.setId(i);
            i++;
        }

    }

    @Test
    public void findByUserIdTest() {
        for (User user : users) {
            int userId = user.getId();
            Wallet wallet = paymentService.findByUserId(userId);
            Assert.assertNotNull(wallet);
            Assert.assertEquals(userId, wallet.getUserId());
        }
        Wallet noWallet = paymentService.findByUserId(99);
        Assert.assertNull(noWallet);
    }


    @Test
    public void queryWalletTest() {
        for (User user : users) {
            int userId = user.getId();
            MyMessage message = (MyMessage) paymentService.queryWallet(userId);
            Assert.assertEquals(1, message.getStatus());
            Wallet wallet = (Wallet)message.getMessage();
            Assert.assertNotNull(wallet);
            Assert.assertEquals(userId, wallet.getUserId());
        }
        MyMessage nomessage = (MyMessage) paymentService.queryWallet(99);
        Assert.assertEquals(0,nomessage.getStatus());
    }

    @Test
    public void payOrderTest() {
        for (Reservation reservation : reservations) {
            double price = reservation.getPrice();
            double oldbalance = paymentService.findByUserId(reservation.getUserId()).getBalance();
            Assert.assertEquals(Reservation.NOTPAID, orderService.findById(reservation.getId()).getStatus());
            MyMessage message = (MyMessage) paymentService.payOrder(reservation.getUserId(), reservation.getId());
            if (message.getStatus() == 0) {
                System.out.println(message.getMessage());
            } else {
                Assert.assertEquals(1, message.getStatus());
                double newbalance = paymentService.findByUserId(reservation.getUserId()).getBalance();
                Assert.assertEquals(oldbalance-price, newbalance, 0.000000001);
                Reservation act = orderService.findById(reservation.getId());
                Assert.assertEquals(Reservation.PAID, act.getStatus());
            }
        }
    }

    @Test
    public void updateWallet() {
        for (User user : users) {
            int userId = user.getId();
            double oldb = paymentService.findByUserId(userId).getBalance();
            MyMessage message = (MyMessage) paymentService.updateWallet(userId, 10);
            Assert.assertEquals(1, message.getStatus());
            double newb = paymentService.findByUserId(userId).getBalance();
            Assert.assertEquals(oldb+10,newb,0.000000001);
        }
    }

}
