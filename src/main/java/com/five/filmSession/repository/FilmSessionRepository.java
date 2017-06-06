package com.five.filmSession.repository;

import com.five.filmSession.model.FilmSession;
import org.springframework.data.jpa.repository.JpaRepository;

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
