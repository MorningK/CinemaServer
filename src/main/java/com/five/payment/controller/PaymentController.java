package com.five.payment.controller;

import com.five.order.service.OrderService;
import com.five.payment.service.PaymentService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by msi on 2017/6/6.
 */
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "/sysupay", method = RequestMethod.POST)
    public Object payOrder(int orderId, HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return paymentService.payOrder(userId, orderId);
    }

    @RequestMapping(value = "/createWallet", method = RequestMethod.POST)
    public Object createWallet(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return paymentService.addWalletForNewUserById(userId, 100);
    }

    @RequestMapping(value = "/queryWallet", method = RequestMethod.POST)
    public Object queryWallet(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return paymentService.queryWallet(userId);
    }


    //session should have two attributes : userId and balance
    //userId : the id of user
    //balance : 更新的金额.如果充值了30，则balance为30.如果消费了30,则balance为-30.
    @RequestMapping(value = "/updateWallet", method = RequestMethod.POST)
    public Object updateWallet(double balance, HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return paymentService.updateWallet(userId, balance);
    }
}
