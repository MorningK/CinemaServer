package com.five.hallSitting.dao;

import com.five.hallSitting.model.HallSitting;

/**
 * Created by haoye on 17-6-6.
 */
public interface HallSittingDao {
    HallSitting findById(int hallSittingId);
    HallSitting save(HallSitting hallSitting);
}
