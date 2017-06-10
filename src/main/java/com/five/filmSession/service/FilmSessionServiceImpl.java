package com.five.filmSession.service;

import com.five.filmSession.dao.FilmSessionDao;
import com.five.filmSession.model.FilmSession;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.five.film.Util.FilmUtil.getTheEndOfDay;

/**
 * Created by msi on 2017/6/6.
 */
@Service
@Transactional
public class FilmSessionServiceImpl implements FilmSessionService {

    @Autowired
    private FilmSessionDao filmSessionDao;

    @Override
    public Object getFilmSession(int filmId, int cinemaId, String time) {
        Timestamp bt = Timestamp.valueOf(time);
        Date d = bt;
        Timestamp et= getTheEndOfDay(d);
        List<FilmSession> filmsessions = filmSessionDao.findByFilmAndCinemaAndTime(filmId, cinemaId, bt, et);
        if (filmsessions == null || filmsessions.size() == 0) {
            return new MyMessage(0, "没有找到场次");
        } else {
            return new MyMessage(1, filmsessions);
        }
    }

    @Override
    public FilmSession findById(int id) {
        return filmSessionDao.findById(id);
    }

    @Override
    public List<Integer> findFilmIdByTime(Timestamp bt, Timestamp et) {
        return filmSessionDao.findFilmIdByTime(bt, et);
    }

    @Override
    public List<Integer> findFilmIdByCinemaAndTime(int cinemaId, Timestamp bt, Timestamp et) {
        return filmSessionDao.findByCinemaIdAndTime(cinemaId, bt, et);
    }

    @Override
    public List<Integer> findFilmIdByCinemasAntTime(List<Integer> cinemaId, Timestamp bt, Timestamp et) {
        return filmSessionDao.findByCinemasAntTime(cinemaId, bt, et);
    }
}
