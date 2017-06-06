package com.five.film.dao;

import com.five.film.model.FilmSession;
import com.five.film.repository.FilmSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public class FilmSessionDaoImpl implements FilmSessionDao {

    @Autowired
    private FilmSessionRepository filmSessionRepository;


    @Override
    public FilmSession findById(int id) {
        return filmSessionRepository.findById(id);
    }

    @Override
    public List<FilmSession> findByTime(Timestamp bt, Timestamp et) {
        return filmSessionRepository.findByBeginTimeGreaterThenAndEndTimeLessThen(bt, et);
    }

    @Override
    public List<FilmSession> findByFilmAndCinemaAndTime(int filmId, int cinemaId, Timestamp bt, Timestamp et) {
        return filmSessionRepository.findByFilmIdAndCinemaIdAndBeginTimeGreaterThanAndEndTimeLessThen(filmId, cinemaId, bt, et);
    }

}
