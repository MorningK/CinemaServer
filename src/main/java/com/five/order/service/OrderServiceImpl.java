package com.five.order.service;

import com.five.filmSession.service.FilmSessionService;
import com.five.hallSitting.service.HallSittingService;
import com.five.order.dao.OrderDao;
import com.five.order.model.Reservation;
import com.five.order.utils.ClockThread;
import com.five.order.utils.ClockThreadPool;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by haoye on 17-6-6.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private HallSittingService hallSittingService;
    @Autowired
    private FilmSessionService filmSessionService;

    @Autowired
    private UserService userService;

    private ClockThreadPool clockThreadPool = ClockThreadPool.getInstance();

    @Override
    public MyMessage makeOrder(int userId, int filmSessionId, String orderSit, double price) {
        if (orderSit.equals("[]")) {
            return new MyMessage(0, "请选择座位");
        }

        User user = userService.findById(userId);
        if (user == null) {
            return new MyMessage(0, "用户不存在");
        }

        int hallSittingId = filmSessionService.findById(filmSessionId).getHallSittingId();

        if (!hallSittingService.isSitEmpty(orderSit, hallSittingId)) {
            return new MyMessage(0, "座位不为空");
        }

        hallSittingService.addSitting(orderSit, hallSittingId);
        Reservation order = orderDao.save(userId, filmSessionId, orderSit, price);

//        ClockThread clockThread = new ClockThread(order, this);
        int pos = clockThreadPool.createThread(order, this);
        ClockThread clockThread = clockThreadPool.getThread(pos);
        clockThread.setPos(pos);

        new Thread(clockThread).start();
        return new MyMessage(1, order.getId());
    }

    @Override
    public void orderOutOfDate(Reservation order) {
        order.setStatus(Reservation.OUTOFDATE);
        orderDao.updateReservation(order);
    }

    @Override
    public void orderPaid(Reservation order) {
        order.setStatus(Reservation.PAID);
        orderDao.updateReservation(order);
    }

    @Override
    public int UpdateStatusById(int id, int status) {
        return orderDao.UpdateStatusById(findById(id), status);
    }

    @Override
    public Reservation findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public MyMessage findOrderByUserId(int userId) {
        Reservation[] reservation = orderDao.findByUserId(userId);
        return new MyMessage(1, reservation);
    }


}
