package com.five.order.dao;

import com.five.order.model.Reservation;
import com.five.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
@CacheConfig(cacheNames = "reservation")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @CachePut(cacheNames = "reservation", key = "#result.getId()", condition = "#result != null")
    public Reservation save(int userId, int filmSessionId, String orderSit, double price) {
        Reservation order = new Reservation(orderSit, price, filmSessionId, userId);
        return orderRepository.save(order);
    }

    @Override
    @CachePut(cacheNames = "reservation", key = "#p0.getId()")
    public void orderOutOfDate(Reservation order) {
        orderRepository.save(order);
    }

    @Override
    @CachePut(cacheNames = "reservation", key = "#p0.getId()")
    public int UpdateStatusById(int id, int status) {
        return orderRepository.updateById(id, status);
    }

    @Override
    @Cacheable(cacheNames = "reservation", key = "#result.getId()", condition = "#result != null")
    public Reservation findById(int id) {
        return orderRepository.findOne(id);
    }
}
