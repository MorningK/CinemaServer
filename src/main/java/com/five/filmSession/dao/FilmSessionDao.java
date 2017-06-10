package com.five.filmSession.dao;

import com.five.filmSession.model.FilmSession;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface FilmSessionDao {
    FilmSession findById(int id);
    List<Integer> findFilmIdByTime(Timestamp bt, Timestamp et);
    List<FilmSession> findByFilmAndCinemaAndTime(int filmId, int cinemaId, Timestamp bt, Timestamp et);
    FilmSession save(FilmSession filmSession);
    List<Integer> findByCinemaIdAndTime(int cinemaId, Timestamp bt, Timestamp et);
    List<Integer> findByCinemasAntTime(List<Integer> cinemaId, Timestamp bt, Timestamp et);

}
