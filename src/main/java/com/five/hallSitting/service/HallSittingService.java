package com.five.hallSitting.service;

import com.five.hallSitting.model.Sits;

/**
 * Created by haoye on 17-6-6.
 */
public interface HallSittingService {
    public Sits getCurrentSit(int hallSittingId);
    public boolean isSitEmpty(String sitsStr, int hallSittingId);
    public boolean addSitting(String sitStr, int hallSittingId);
}
