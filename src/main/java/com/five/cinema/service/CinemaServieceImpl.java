package com.five.cinema.service;

import com.five.cinema.dao.CinemaDao;
import com.five.cinema.model.Cinema;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public MyMessage getCinemas(int citycode, double longtitude, double latitude) {
        String total = "", head = "{\"Cinema\":[", tail = "]}";
        List<Cinema> cinemas = cinemaDao.findByLocation(citycode, longtitude, latitude, 0);
        for (int i = 0; i < cinemas.size(); i++) {
            String temp = cinemas.get(i).toString();
            total += temp;
            if (i != cinemas.size() - 1) total += ",";
        }
        if (total.length() == 0) {
            return new MyMessage(0, "没有找到电影院");
        } else {
            return new MyMessage(1,head+total+tail);
        }
    }

    @Override
    public MyMessage getCinemas(int citycode, double longtitude, double latitude, int currentpage) {
        String total = "", head = "{\"Cinema\":[", tail = "]}";
        List<Cinema> cinemas = cinemaDao.findByLocation(citycode, longtitude, latitude, currentpage);
        for (int i = 0; i < cinemas.size(); i++) {
            String temp = cinemas.get(i).toString();
            total += temp;
            if (i != cinemas.size() - 1) total += ",";
        }
        if (total.length() == 0) {
            return new MyMessage(0, "没有找到电影院");
        } else {
            return new MyMessage(1,head+total+tail);
        }
    }

    @Override
    public Cinema findById(int id) {
        return cinemaDao.findById(id);
    }
}
