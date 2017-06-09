package com.five.hallSitting.repository;

import com.five.hallSitting.model.HallSitting;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoye on 17-6-6.
 */
//@CacheConfig(cacheNames = "hallSitting")
public interface HallSittingRepository extends JpaRepository<HallSitting, Integer> {
//    @Cacheable
    HallSitting findOne(Integer id);
}
