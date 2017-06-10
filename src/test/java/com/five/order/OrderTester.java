package com.five.order;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.filmSession.dao.FilmSessionDao;
import com.five.filmSession.model.FilmSession;
import com.five.hallSitting.dao.HallSittingDao;
import com.five.hallSitting.model.HallSitting;
import com.five.order.dao.OrderDao;
import com.five.order.model.Reservation;
import com.five.order.service.OrderService;
import com.five.user.dao.UserDao;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.constraints.AssertTrue;
import javax.xml.crypto.Data;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2017/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class OrderTester {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private HallSittingDao hallSittingDao;
    @Autowired
    private FilmSessionDao filmSessionDao;

    private List<User> users = new ArrayList<>();
    private List<HallSitting> hallSittings = new ArrayList<>();
    private List<FilmSession> filmSessions = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    private Object[] toObjects(Reservation reservation) {
        return new Object[] {
                reservation.getPrice(),
                reservation.getFilmSessionId(),
//                reservation.getId(),
                reservation.getSitting(),
                reservation.getStatus(),
                reservation.getUserId()
        };
    }

    @Before
    public void prepareData() {
        users = DataCreator.prepareUser(5);
        for (User user:users) {
            userDao.save(user);
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

    }

    @Test
    public void makeOrderTest() {
        for (Reservation reservation : reservations) {
            MyMessage message = orderService.makeOrder(reservation.getUserId(), reservation.getFilmSessionId(), reservation.getSitting(), reservation.getPrice());
            Assert.assertEquals(1,message.getStatus());
        }
        int i = 1;
        for (Reservation reservation : reservations) {
            Reservation act = orderService.findById(i);
            Assert.assertEquals(Reservation.NOTPAID, act.getStatus());
//            Assert.assertEquals(0,message.getStatus());
            i++;
        }
        for (Reservation reservation : reservations) {
            MyMessage message = orderService.makeOrder(reservation.getUserId(), reservation.getFilmSessionId(), reservation.getSitting(), reservation.getPrice());
            Assert.assertEquals(0,message.getStatus());
        }
    }

    @Test
    public void orderOutOfDateTest() {
        for (Reservation reservation : reservations) {
            orderService.makeOrder(reservation.getUserId(), reservation.getFilmSessionId(), reservation.getSitting(), reservation.getPrice());
        }
        int i = 0;
        for (Reservation reservation : reservations) {
            Reservation act = orderService.findById(i+1);
            orderService.orderOutOfDate(act);
            i++;
        }
        i = 0;
        for (Reservation reservation : reservations) {
            Reservation act = orderService.findById(i+1);
            Assert.assertEquals(Reservation.OUTOFDATE, act.getStatus());
            i++;
        }
    }
//
    @Test
    public void UpdateStatusById() {
        for (Reservation reservation : reservations) {
            orderService.makeOrder(reservation.getUserId(), reservation.getFilmSessionId(), reservation.getSitting(), reservation.getPrice());
        }
        int i = 0;
        for (Reservation reservation : reservations) {
            int ans = orderService.UpdateStatusById(i + 1, Reservation.PAID);
            Assert.assertEquals(1, ans);
            Reservation act = orderService.findById(i+1);
            Assert.assertEquals(Reservation.PAID, act.getStatus());
            i++;
        }
    }
//
    @Test
    public void findByIdTest() {
        for (Reservation reservation : reservations) {
            orderService.makeOrder(reservation.getUserId(), reservation.getFilmSessionId(), reservation.getSitting(), reservation.getPrice());
        }
        int i = 0;
        for (Reservation reservation : reservations) {
            Reservation act = orderService.findById(i+1);
            Assert.assertArrayEquals(toObjects(reservation), toObjects(act));
            i++;
        }
    }
}
