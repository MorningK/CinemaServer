package com.five.order.controller;

import com.five.hallSitting.model.Sits;
import com.five.order.service.OrderService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by haoye on 17-6-6.
 */
@RestController
@RequestMapping("/reservation")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public MyMessage makeReservation(HttpSession session, int filmSessionId, String orderSit, double price) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        MyMessage messaage = orderService.makeOrder(userId, filmSessionId, orderSit, price);
        return messaage;
    }

    @RequestMapping(value = "/getMyOrder", method = RequestMethod.GET)
    public MyMessage getMyOrder(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        MyMessage message = orderService.findOrderByUserId(userId);
        return message;
    }


}
