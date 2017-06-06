package com.five.order.dao;

import com.five.hallSitting.model.Sits;
import com.five.order.model.Reservation;

/**
 * Created by haoye on 17-6-6.
 */
public interface OrderDao {
    Reservation save(int userId, int filmSessionId, String orderSit, double price);
    public void orderOutOfDate(Reservation order);
}
