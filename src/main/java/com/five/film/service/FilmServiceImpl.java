package com.five.film.service;

import com.five.film.dao.FilmDao;
import com.five.film.dao.FilmSessionDao;
import com.five.film.model.FilmSession;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by msi on 2017/6/6.
 */
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmSessionDao filmSessionDao;

    @Autowired
    private FilmDao filmDao;

    @Override
    public MyMessage getFilm() {
        Date d = new Date();
        Timestamp bt = getTheStartOfDay(d);
        Timestamp et = getTheEndOfDay(d);
        List<FilmSession> sessions = filmSessionDao.findByTime(bt, et);
        Set<String> filmids = new HashSet<>();
        for (int i = 0; i < sessions.size(); i++) {
            if (!filmids.contains(Integer.toString(sessions.get(i).getFilmId()))) {
                filmids.add(Integer.toString(sessions.get(i).getFilmId()));
            }
        }
        String total = "", head = "{\"Film\":[", tail = "]}";
        for (String id : filmids) {
            int iid = Integer.parseInt(id);
            total += filmDao.findById(iid).toString();
        }
        if (total.length() == 0) {
            return new MyMessage(0, "没有找到电影");
        } else {
            return new MyMessage(1, head+total+tail);
        }
    }

    @Override
    public MyMessage getFilmSession(int filmId, int cinemaId, String time) {
        Timestamp bt = Timestamp.valueOf(time);
        Date d = bt;
        Timestamp et= getTheEndOfDay(d);
        List<FilmSession> filmsessions = filmSessionDao.findByFilmAndCinemaAndTime(filmId, cinemaId, bt, et);

        String total = "", head = "{\"FilmSession\":[", tail = "]}";
        for(FilmSession filmsession : filmsessions) {
            total += filmsession.toString();
        }
        if (total.length() == 0) {
            return new MyMessage(0, "没有找到电影");
        } else {
            return new MyMessage(1, head+total+tail);
        }
    }

    private Timestamp getTheStartOfDay(Date d) {
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTime().getTime());
    }

    private Timestamp getTheEndOfDay(Date d) {
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTime().getTime());
    }
}
