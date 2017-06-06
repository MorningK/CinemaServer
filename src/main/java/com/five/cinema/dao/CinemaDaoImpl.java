package com.five.cinema.dao;

import com.five.cinema.model.Cinema;
import com.five.cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public class CinemaDaoImpl implements CinemaDao {

    @Autowired
    private CinemaRepository cinemaRepository;

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

    @Override
    public List<Cinema> findByLocation(int citycode, double longtitude, double latitude, int currentpage) {
        List<Cinema> cinemas = cinemaRepository.findByCitycode(citycode);
        Collections.sort(cinemas, new CompareLocation(longtitude, latitude));
        return cinemas.subList(currentpage*10, currentpage*10+10);
    }

    @Override
    public void save(Cinema cinema) {
        cinemaRepository.save(cinema);
    }
}
