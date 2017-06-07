package com.five.payment.controller;

import com.five.order.service.OrderService;
import com.five.payment.service.PaymentService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by msi on 2017/6/6.
 */
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/sysupay")
    public Object payOrder(int orderId) {
        MyMessage message = paymentService.payOrder(orderId);
        return message;
    }

    @PostMapping("/createWallet")
    public Object createWallet(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return paymentService.addWalletForNewUserById(userId, 100);
    }

    @PostMapping("/queryWallet")
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
    @PostMapping("/updateWallet")
    public Object updateWallet(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        Object balanceObj = session.getAttribute("balance");
        if (balanceObj == null) {
            return new MyMessage(0, "No money?");
        }
        double balance = (double)balanceObj;
        return paymentService.updateWallet(userId, balance);
    }
}
