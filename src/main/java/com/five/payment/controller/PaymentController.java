package com.five.payment.controller;

import com.five.order.service.OrderService;
import com.five.payment.service.PaymentService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by msi on 2017/6/6.
 */
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/sysupay")
    public MyMessage payOrder(int orderId) {
        MyMessage message = paymentService.payOrder(orderId);
        return message;
    }
}
