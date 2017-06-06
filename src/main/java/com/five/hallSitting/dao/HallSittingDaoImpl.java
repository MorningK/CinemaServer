package com.five.hallSitting.dao;

import com.five.hallSitting.model.HallSitting;
import com.five.hallSitting.repository.HallSittingRepository;
import com.five.hallSitting.service.HallSittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
public class HallSittingDaoImpl implements HallSittingDao {

    @Autowired
    private HallSittingRepository hallSittingRepository;

    @Override
    public HallSitting findById(int hallSittingId) {
        return hallSittingRepository.findOne(hallSittingId);
    }

    @Override
    public void save(HallSitting hallSitting) {
        hallSittingRepository.save(hallSitting);
    }

}
