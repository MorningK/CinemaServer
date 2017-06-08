package com.five.cinema.service;

import com.five.cinema.dao.CinemaDao;
import com.five.cinema.model.Cinema;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "cinema")
public class CinemaServieceImpl implements CinemaService  {

    @Autowired
    private CinemaDao cinemaDao;

    @Override
    public Object getCinemas(int citycode, double longtitude, double latitude, int currentpage) {
        String total = "", head = "{\"Cinema\":[", tail = "]}";
        List<Cinema> cinemas = cinemaDao.findByLocation(citycode, longtitude, latitude, currentpage);
        if (cinemas == null) return new MyMessage(0, "没有找到电影院");
        if (total.length() == 0) {
            return new MyMessage(0, "没有找到电影院");
        } else {
            return new MyMessage(1,cinemas);
        }
    }

    @Override
    public Cinema findById(int id) {
        return cinemaDao.findById(id);
    }

    @Override
    @CacheEvict(cacheNames = "cinema", allEntries = true)
    public void reload() {

    }
}
