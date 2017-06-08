package com.five.cinemaPic.dao;

import com.five.cinemaPic.model.CinemaPic;
import com.five.cinemaPic.repository.CinemaPicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

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
    public List<CinemaPic> getCoverByCinemaId(int cinemaId) {
        return cinemaPicRepository.findByCinemaIdAndType(cinemaId, CinemaPic.CP_COVER);
    }

    @Override
    public List<CinemaPic> getInsideByCinemaId(int cinemaId) {
        return cinemaPicRepository.findByCinemaIdAndType(cinemaId, CinemaPic.CP_INSIDE);
    }

    @Override
    @CacheEvict(cacheNames = "cinemaPic", allEntries = true)
    public void reload() {

    }
}
