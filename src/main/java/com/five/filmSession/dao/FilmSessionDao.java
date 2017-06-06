package com.five.filmSession.dao;

import com.five.filmSession.model.FilmSession;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface FilmSessionDao {
    FilmSession findById(int id);
    List<FilmSession> findByTime(Timestamp bt, Timestamp et);
    List<FilmSession> findByFilmAndCinemaAndTime(int filmId, int cinemaId, Timestamp bt, Timestamp et);
}
