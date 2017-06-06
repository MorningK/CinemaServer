package com.five.order.service;

import com.five.hallSitting.service.HallSittingService;
import com.five.order.dao.OrderDao;
import com.five.order.model.Reservation;
import com.five.order.utils.ClockThread;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by haoye on 17-6-6.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private HallSittingService hallSittingService;
//    @Autowired
//    private FilmSessionService filmSessionService;
    @Autowired
    private UserService userService;

    @Override
    public MyMessage makeOrder(int userId, int filmSessionId, String orderSit, double price) {
        User user = userService.findById(userId);
        if (user == null) {
            return new MyMessage(0, "用户不存在");
        }
        // TODO 获取hallSittingId
        int hallSittingId = 3;

        if (!hallSittingService.isSitEmpty(orderSit, hallSittingId)) {
            return new MyMessage(0, "座位不为空");
        }

        hallSittingService.addSitting(orderSit, hallSittingId);
        Reservation order = orderDao.save(userId, filmSessionId, orderSit, price);
        // TODO no checked
        ClockThread clockThread = new ClockThread(order, this);
        return new MyMessage(1, "下单成功");
    }

    @Override
    public void orderOutOfDate(Reservation order) {
        order.setStatus(Reservation.OUTOFDATE);
        orderDao.orderOutOfDate(order);
    }

    @Override
    public MyMessage payOrder(int orderId) {
        int result = orderDao.payById(orderId);
        System.out.println(result);
        if (result > 0) return new MyMessage(1, "支付成功");
        else return new MyMessage(0, "支付失败");
    }

}
