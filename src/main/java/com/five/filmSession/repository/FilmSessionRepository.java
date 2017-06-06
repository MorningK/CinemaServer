package com.five.filmSession.repository;

import com.five.filmSession.model.FilmSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface FilmSessionRepository extends JpaRepository<FilmSession, Integer> {
    FilmSession findById(int id);

    @Query(value = "select f from FilmSession f where f.filmId=:fid AND f.cinemaId=:cid AND f.beginTime >= :bt AND f.endTime <= :et")
    List<FilmSession> findByFilmIdAndCinemaIdAndBeginTimeGreaterThanAndEndTimeLessThen(@Param("fid") int filmId, @Param("cid") int cinemaId,
                                                                                       @Param("bt") Timestamp bt, @Param("et")  Timestamp et);

    @Query(value = "select f from FilmSession f where f.beginTime >= :bt AND f.endTime <= :et")
    List<FilmSession> findByBeginTimeGreaterThenAndEndTimeLessThen(@Param("bt") Timestamp bt,@Param("et") Timestamp et);
}
