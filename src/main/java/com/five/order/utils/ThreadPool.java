package com.five.order.utils;

import java.util.HashMap;

/**
 * Created by haoye on 17-6-6.
 */
public class ThreadPool {

    public static ThreadPool threadPoolClass = new ThreadPool();
    public static ThreadPool getInstance() {
        return threadPoolClass;
    }

    private HashMap<Integer, ClockThread> threadPool;

    public ThreadPool() {}

    public void put(Integer orderId, ClockThread clockThread) {
        threadPool.put(orderId, clockThread);
    }

    public void remove(Integer orderId) {
        threadPool.remove(orderId);
    }
}
