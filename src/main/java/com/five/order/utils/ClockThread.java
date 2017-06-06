package com.five.order.utils;

import com.five.order.model.Reservation;
import com.five.order.service.OrderService;

/**
 * Created by haoye on 17-6-6.
 */
public class ClockThread implements Runnable{

    private boolean isPaid = false;
    private Reservation order;
    private OrderService orderService;

    public ClockThread(Reservation order, OrderService orderService) {
        this.order = order;
        this.orderService = orderService;
    }

    public void paid() {
        isPaid = true;
    }

    @Override
    public void run() {
        try {
            ThreadPool threadPool = ThreadPool.getInstance();
            threadPool.put(order.getId(), this);
            Thread.sleep(1000 * 60 * 10);
            if (!isPaid) {
                orderService.orderOutOfDate(order);
            }
            threadPool.remove(order.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
