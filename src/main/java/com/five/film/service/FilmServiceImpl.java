package com.five.film.service;

import com.five.film.dao.FilmDao;
import com.five.film.model.Film;
import com.five.filmSession.dao.FilmSessionDao;
import com.five.filmSession.model.FilmSession;
import com.five.filmSession.service.FilmSessionService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.five.film.Util.FilmUtil.getTheEndOfDay;
import static com.five.film.Util.FilmUtil.getTheStartOfDay;

/**
 * Created by msi on 2017/6/6.
 */
@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmSessionService filmSessionService;

    @Autowired
    private FilmDao filmDao;

    
    @Override
    public MyMessage getFilm() {
        Date d = new Date();
        Timestamp bt = getTheStartOfDay(d);
        Timestamp et = getTheEndOfDay(d);
        List<Integer> filmIds = filmSessionService.findFilmIdByTime(bt, et);
        String total = "", head = "{\"Film\":[", tail = "]}";
        for (Integer id : filmIds) {
            total += filmDao.findById(id).toString();
        }
        if (total.length() == 0) {
            return new MyMessage(0, "没有找到电影");
        } else {
            return new MyMessage(1, head+total+tail);
        }
    }

    @Override
    public Film findById(int id) {
        return filmDao.findById(id);
    }


}
