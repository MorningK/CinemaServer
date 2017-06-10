package com.five.cinema.service;

import com.five.cinema.dao.CinemaDao;
import com.five.cinema.dao.CinemaDaoImpl;
import com.five.cinema.model.Cinema;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@Service
@Transactional
public class CinemaServieceImpl implements CinemaService  {

    @Autowired
    private CinemaDao cinemaDao;

    @Override
    public Object getCinemas(int citycode, double longtitude, double latitude, int currentpage) {
        List<Cinema> cinemas = cinemaDao.findByLocation(citycode);
        if (cinemas == null) return new MyMessage(0, "没有找到电影院");
        Collections.sort(cinemas, new CompareLocation(longtitude, latitude));
        int endpos = (currentpage+1)*10;
        if (cinemas.size() < currentpage*10 || cinemas.size() == 0) return new MyMessage(0, "没有找到电影院");
        else {
            if (cinemas.size() < endpos) {
                endpos = cinemas.size();
            }
            return new MyMessage(1,cinemas.subList(currentpage*10, endpos));
        }
    }

    @Override
    public Cinema findById(int id) {
        return cinemaDao.findById(id);
    }

    @Override
    public void reload() {
        cinemaDao.reload();
    }

    @Override
    public List<Integer> findIdByCitycode(int citycode) {
        return cinemaDao.findIdByCitycode(citycode);
    }

    private final class CompareLocation implements Comparator<Cinema> {
        double lo, la;

        public CompareLocation(double longtitude, double latitude) {
            lo = longtitude;
            la = latitude;
        }

        @Override
        public int compare(Cinema o1, Cinema o2) {
            double lo1 = o1.getLongtitude(), lo2 = o2.getLongtitude();
            double la1 = o1.getLatitude(), la2 = o2.getLatitude();
            double dis1 = dis(lo, la, lo1, la1), dis2 = dis(lo, la, lo2, la2);
            if (dis1 > dis2) return 1;
            else if (dis1 == dis2) return 0;
            else return -1;
        }

        public double dis(double x1, double y1, double x2, double y2) {
            return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        }
    }


}


