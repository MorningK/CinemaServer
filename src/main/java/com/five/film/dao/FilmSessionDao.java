package com.five.film.dao;

import com.five.film.model.FilmSession;
import org.springframework.data.repository.query.Param;

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
