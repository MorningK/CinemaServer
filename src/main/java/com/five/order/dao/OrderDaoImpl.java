package com.five.order.dao;

import com.five.order.model.Reservation;
import com.five.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Reservation save(int userId, int filmSessionId, String orderSit, double price) {
        Reservation order = new Reservation(orderSit, price, filmSessionId, userId);
        return orderRepository.save(order);
    }



    @Override
    public void orderOutOfDate(Reservation order) {
        orderRepository.save(order);
    }

    @Override
    public int UpdateStatusById(int id, int status) {
        return orderRepository.findById(id, status);
    }

    @Override
    public Reservation findById(int id) {
        return orderRepository.findOne(id);
    }
}
