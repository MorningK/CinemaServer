package com.five.cinemaRemark.repository;

import com.five.cinemaRemark.model.CinemaRemark;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msi on 2017/6/7.
 */
@CacheConfig(cacheNames = "cinemaRemark")
public interface CinemaRemarkRepository extends JpaRepository<CinemaRemark, Integer> {
    @Cacheable
    CinemaRemark[] findByUserId(int userId);

    @Cacheable
    CinemaRemark[] findByCinemaId(int cinemaId);
}
