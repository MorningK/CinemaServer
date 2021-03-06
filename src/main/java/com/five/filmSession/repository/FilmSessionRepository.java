package com.five.filmSession.repository;

import com.five.film.model.Film;
import com.five.filmSession.model.FilmSession;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
//@CacheConfig(cacheNames = "filmSession")
public interface FilmSessionRepository extends JpaRepository<FilmSession, Integer> {
//    @Cacheable
    FilmSession findOne(Integer id);

//    @Cacheable
    @Query(value = "select f from FilmSession f where f.filmId=:fid AND f.cinemaId=:cid AND f.beginTime >= :bt AND f.beginTime <= :et")
    List<FilmSession> findByFilmIdAndCinemaIdAndBeginTimeGreaterThanAndEndTimeLessThen(@Param("fid") int filmId, @Param("cid") int cinemaId,
                                                                                       @Param("bt") Timestamp bt, @Param("et")  Timestamp et);
//    @Cacheable
    @Query(value = "select distinct f.filmId from FilmSession f where  f.beginTime >= :bt AND f.beginTime <= :et")
    List<Integer> findByBeginTimeGreaterThenAndEndTimeLessThen(@Param("bt") Timestamp bt,@Param("et") Timestamp et);

    @Query(value = "select distinct f.filmId from FilmSession f where f.cinemaId=:cid AND  f.beginTime >= :bt AND f.beginTime <= :et")
    List<Integer> findByCinemaIdAndTime(@Param("cid") int cinemaId, @Param("bt") Timestamp bt, @Param("et")  Timestamp et);

    @Query(value = "select distinct f.filmId from FilmSession f where f.cinemaId in :cid AND f.beginTime >= :bt AND f.beginTime <= :et")
    List<Integer> findByCinemasAntTime(@Param("cid") List<Integer> cinemaId, @Param("bt") Timestamp bt, @Param("et")  Timestamp et);
}
