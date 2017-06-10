package com.five.cinemaRemark.dao;

import com.five.cinemaRemark.model.CinemaRemark;
import com.five.cinemaRemark.repository.CinemaRemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by msi on 2017/6/7.
 */
@Repository
@CacheConfig(cacheNames = "cinemaRemark")
public class CinemaRemarkDaoImpl implements CinemaRemarkDao {

    @Autowired
    private CinemaRemarkRepository cinemaRemarkRepository;

    @Override
//    @Caching(evict = {
//    @CacheEvict(cacheNames = "cinemaRemark", key = "#p0.getCinemaId()"),
//    @CacheEvict(cacheNames = "cinemaRemark", key = "#p0.getUserId()")
//    })
    @CachePut(keyGenerator = "wiselyKeyGenerator")
    public CinemaRemark save(CinemaRemark cinemaRemark) {
        return cinemaRemarkRepository.save(cinemaRemark);
    }

    @Override
//    @Cacheable(cacheNames = "cinemaRemark", key = "#p0", condition = "#result != null and #result.size() > 0")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<CinemaRemark> findByCinemaId(int cinemaId) {
        return cinemaRemarkRepository.findByCinemaId(cinemaId);
    }

    @Override
//    @Cacheable(cacheNames = "cinemaRemark", key = "#p0", condition = "#result != null and #result.size() > 0")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<CinemaRemark> findByUserId(int userId) {
        return cinemaRemarkRepository.findByUserId(userId);
    }

    @Override
//    @CacheEvict(cacheNames = "cinemaRemark", allEntries = true)
    public void reload() {

    }
}
