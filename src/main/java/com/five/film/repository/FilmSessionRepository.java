package com.five.film.repository;

import com.five.film.model.Film;
import com.five.film.model.FilmSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface FilmSessionRepository extends JpaRepository<FilmSession, Integer> {
    FilmSession findById(int id);

    List<FilmSession> findByFilmIdAndCinemaIdAndBeginTimeGreaterThanAndEndTimeLessThen(int filmId, int cinemaId, Timestamp bt, Timestamp et);

    //@Query(value = "select f from FilmSession f where f.beginTime >= :bt AND f.endTime <= :et")
    List<FilmSession> findByBeginTimeGreaterThenAndEndTimeLessThen(Timestamp bt, Timestamp et);
}
