package com.five.user.utils;

import com.five.order.model.Reservation;
import com.five.order.service.OrderService;
import com.five.order.utils.ClockThread;
import com.five.order.utils.ClockThreadPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoye on 17-6-9.
 */
public class MailThreadPool {

    public static MailThreadPool mailThreadPool = new MailThreadPool();
    public static MailThreadPool getInstance() {
        return mailThreadPool;
    }
    private List<MailUtil> threadPoolList = new ArrayList<>();
    private List<Boolean> inUse = new ArrayList<>();

    public MailThreadPool() {}

    public synchronized int createThread(String email, String subject, String content) {
        if (!hasEmpty()) {
            MailUtil newOne = new MailUtil(email, subject, content);
            threadPoolList.add(newOne);
            inUse.add(true);
            return threadPoolList.size() - 1;
        } else {
            int emptyPos = getEmptyPos();
            MailUtil oldGetNew = threadPoolList.get(emptyPos);
            oldGetNew.init(email, subject, content);
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

    public MailUtil getThread(int i) {
        return threadPoolList.get(i);
    }
}
