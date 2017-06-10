package com.five.cinemaPic.dao;

import com.five.cinemaPic.model.CinemaPic;
import com.five.cinemaPic.repository.CinemaPicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

/**
 * Created by msi on 2017/6/7.
 */
@Repository
@CacheConfig(cacheNames = "cinemaPic")
public class CinemaPicDaoImpl implements CinemaPicDao {

    @Autowired
    private CinemaPicRepository cinemaPicRepository;

    @Override
//    @Cacheable(cacheNames = "cinemaPic")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<CinemaPic> getPicByCinemaIdAndType(int cinemaId, int type) {
        return cinemaPicRepository.findByCinemaIdAndType(cinemaId, type);
    }

    @Override
//    @CacheEvict(cacheNames = {"cinemaPic"}, allEntries = true)
    public void reload() {
    }
}
