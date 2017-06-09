package com.five.cinemaRemark.repository;

import com.five.cinemaRemark.model.CinemaRemark;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by msi on 2017/6/7.
 */
//@CacheConfig(cacheNames = "cinemaRemark")
public interface CinemaRemarkRepository extends JpaRepository<CinemaRemark, Integer> {
//    @Cacheable
    List<CinemaRemark> findByUserId(int userId);

//    @Cacheable
    List<CinemaRemark> findByCinemaId(int cinemaId);
}
