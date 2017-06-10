package com.five.film.service;

import com.five.cinema.service.CinemaService;
import com.five.film.dao.FilmDao;
import com.five.film.model.Film;
import com.five.filmSession.service.FilmSessionService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    private CinemaService cinemaService;

    @Autowired
    private FilmDao filmDao;

    @Override
    public Object getFilm(int citycode) {
        List<Integer> cids = cinemaService.findIdByCitycode(citycode);
        Date d = new Date();
        Timestamp bt = getTheStartOfDay(d);
        Timestamp et = getTheEndOfDay(d);
        List<Integer> filmIds = filmSessionService.findFilmIdByCinemasAntTime(cids, bt, et);
        List<Film> films = new ArrayList<>();
        for (Integer id : filmIds) {
            films.add(filmDao.findById(id));
        }
        if (films.size() == 0) {
            return new MyMessage(0, "没有找到电影");
        } else {
            return new MyMessage(1, films);
        }
    }

    @Override
    public Film findById(int id) {
        return filmDao.findById(id);
    }

    @Override
    public void reload() {
        filmDao.reload();
    }

    @Override
    public Object getFilmByCinema(int cinemaId) {
        Date d = new Date();
        Timestamp bt = getTheStartOfDay(d);
        Timestamp et = getTheEndOfDay(d);
        List<Integer> filmIds = filmSessionService.findFilmIdByCinemaAndTime(cinemaId, bt, et);
        List<Film> films = new ArrayList<>();
        for (Integer id : filmIds) {
            films.add(filmDao.findById(id));
        }
        if (films.size() == 0) {
            return new MyMessage(0, "没有找到电影");
        } else {
            return new MyMessage(1, films);
        }
    }


}
