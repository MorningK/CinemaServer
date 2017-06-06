package com.five.film.service;

import com.five.film.dao.FilmDao;
import com.five.filmSession.dao.FilmSessionDao;
import com.five.filmSession.model.FilmSession;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.five.film.Util.FilmUtil.getTheEndOfDay;
import static com.five.film.Util.FilmUtil.getTheStartOfDay;

/**
 * Created by msi on 2017/6/6.
 */
@Service
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




}
