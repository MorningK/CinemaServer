package com.five.order.service;

import com.five.order.model.Reservation;
import com.five.user.model.MyMessage;

/**
 * Created by haoye on 17-6-6.
 */
public interface OrderService {
    MyMessage makeOrder(int userId, int filmSessionId, String orderSit, double price);
    void orderOutOfDate(Reservation order);

    int UpdateStatusById(int id, int status);
    Reservation findById(int id);

    MyMessage findOrderByUserId(int userId);
}
