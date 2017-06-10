package com.five.cinema.dao;

import com.five.cinema.model.Cinema;
import com.five.cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@Repository
@CacheConfig(cacheNames = "cinema")
public class CinemaDaoImpl implements CinemaDao {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    @Cacheable(cacheNames = "cinema", key = "#p0", condition = "#result != null and #result.size() > 0")
    public List<Cinema> findByLocation(int citycode) {
        return cinemaRepository.findByCitycode(citycode);
    }

    @Override
    @Caching(put = {
    @CachePut(cacheNames = "cinema", key = "#p0.getId()", condition = "#p0 != null"),
    }, evict = {
    @CacheEvict(cacheNames = "cinema", key = "#result.getCitycode()", condition = "#result != null" )
    })
    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    @Cacheable(cacheNames = "cinema", key = "#result.getId()", condition = "#result != null")
    public Cinema findById(int id) {
        return cinemaRepository.findOne(id);
    }

    @Override
    @CacheEvict(cacheNames = "cinema", allEntries = true)
    public void reload() {

    }

    @Override
    public List<Integer> findIdByCitycode(int citycode) {
        return cinemaRepository.findIdByCitycode(citycode);
    }

}
