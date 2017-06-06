package com.five.payment.service;

import com.five.user.model.MyMessage;

/**
 * Created by msi on 2017/6/6.
 */
public interface PaymentService {
    MyMessage payOrder(int orderId);
}
