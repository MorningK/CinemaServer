package com.five.order.utils;

import com.five.order.model.Reservation;
import com.five.order.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoye on 17-6-6.
 */
public class ClockThreadPool {

    public static ClockThreadPool clockThreadPoolClass = new ClockThreadPool();
    public static ClockThreadPool getInstance() {
        return clockThreadPoolClass;
    }

    private Map<Integer, ClockThread> threadPoolMap = new HashMap<>();
    private List<ClockThread> threadPoolList = new ArrayList<>();
    private List<Boolean> inUse = new ArrayList<>();

    public ClockThreadPool() {}

    public synchronized int createThread(Reservation order, OrderService orderService) {
        if (!hasEmpty()) {
            ClockThread newOne = new ClockThread();
            newOne.init(order, orderService);
            threadPoolList.add(newOne);
            inUse.add(true);
            return threadPoolList.size() - 1;
        } else {
            int emptyPos = getEmptyPos();
            ClockThread oldGetNew = threadPoolList.get(emptyPos);
            oldGetNew.init(order, orderService);
            inUse.remove(emptyPos);
            inUse.add(emptyPos, true);
            return emptyPos;
        }
    }

    public synchronized void destoryThread(int pos) {
        inUse.remove(pos);
        inUse.add(pos, false);
    }

    private synchronized boolean hasEmpty() {
        for (Boolean i : inUse) {
            if (i == false) return true;
        }
        return false;
    }

    private synchronized int getEmptyPos() {
        for (int i = 0; i < inUse.size(); i++) {
            if (inUse.get(i) == false) return i;
        }
        return -1;
    }

    public ClockThread getThread(int i) {
        return threadPoolList.get(i);
    }

    public synchronized void put(Integer orderId, ClockThread clockThread) {
        threadPoolMap.put(orderId, clockThread);
    }

    public synchronized void remove(Integer orderId) {
        threadPoolMap.remove(orderId);
    }

    public synchronized ClockThread get(Integer orderId) {
        return threadPoolMap.get(orderId);
    }
}
