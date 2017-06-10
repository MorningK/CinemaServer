package com.five.hallSitting.dao;

import com.five.hallSitting.model.HallSitting;
import com.five.hallSitting.repository.HallSittingRepository;
import com.five.hallSitting.service.HallSittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
@CacheConfig(cacheNames = "hallSitting")
public class HallSittingDaoImpl implements HallSittingDao {

    @Autowired
    private HallSittingRepository hallSittingRepository;

    @Override
    @Cacheable(cacheNames = "hallSitting", key="#result.getId()", condition = "#result != null")
    public HallSitting findById(int hallSittingId) {
        return hallSittingRepository.findOne(hallSittingId);
    }

    @Override
    @CachePut(cacheNames = "hallSitting", key = "#result.getId()", condition = "#result != null")
    public HallSitting save(HallSitting hallSitting) {
        return hallSittingRepository.save(hallSitting);
    }

}
