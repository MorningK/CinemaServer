package com.five.filmSession.dao;

import com.five.filmSession.model.FilmSession;
import com.five.filmSession.repository.FilmSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@Repository
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
