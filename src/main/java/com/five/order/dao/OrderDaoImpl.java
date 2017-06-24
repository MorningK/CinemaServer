package com.five.order.dao;

import com.five.order.model.Reservation;
import com.five.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
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
    @Caching(put = {
            @CachePut(key = "'reservation.findById'+#result.getId()", condition = "#result != null"),
            @CachePut(key = "'reservation.findByUserId'+#result.getUserId()", condition = "#result != null")
    })
    public Reservation save(int userId, int filmSessionId, String orderSit, double price) {
        Reservation order = new Reservation(orderSit, price, filmSessionId, userId);
        return orderRepository.save(order);
    }

    @Override
//    @CachePut(cacheNames = "reservation", key = "#p0.getId()")
    @Caching(evict = {
            @CacheEvict(key = "'reservation.findById'+#p0.getId()"),
            @CacheEvict(key = "'reservation.findByUserId'+#p0.getUserId()")
    })
    public void updateReservation(Reservation order) {
        orderRepository.save(order);
    }

    @Override
//    @CachePut(cacheNames = "reservation", key = "#p0")
    @Caching(evict = {
            @CacheEvict(key = "'reservation.findById'+#p0.getId()", condition = "#result != 0"),
            @CacheEvict(key = "'reservation.findByUserId'+#p0.getUserId()", condition = "#result != 0")
    })
    public int UpdateStatusById(Reservation reservation, int status) {
        return orderRepository.updateById(reservation.getId(), status);
    }

    @Override
//    @Cacheable(cacheNames = "reservation", key = "#result.getId()", condition = "#result != null")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public Reservation findById(int id) {
        return orderRepository.findOne(id);
    }

    @Override
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public Reservation[] findByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }
}
