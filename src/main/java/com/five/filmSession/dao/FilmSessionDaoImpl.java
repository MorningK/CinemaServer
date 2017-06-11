package com.five.filmSession.dao;

import com.five.filmSession.model.FilmSession;
import com.five.filmSession.repository.FilmSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
@Repository
@CacheConfig(cacheNames = "filmSession")
public class FilmSessionDaoImpl implements FilmSessionDao {

    @Autowired
    private FilmSessionRepository filmSessionRepository;


    @Override
//    @Cacheable(cacheNames = "filmSession", key = "#result.getId()", condition = "#result != null")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public FilmSession findById(int id) {
        return filmSessionRepository.findOne(id);
    }


    @Override
//    @Cacheable(cacheNames = "filmSession")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<Integer> findFilmIdByTime(Timestamp bt, Timestamp et) {
        return filmSessionRepository.findByBeginTimeGreaterThenAndEndTimeLessThen(bt, et);
    }

    @Override
//    @Cacheable(cacheNames = "filmSession")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<FilmSession> findByFilmAndCinemaAndTime(int filmId, int cinemaId, Timestamp bt, Timestamp et) {
        return filmSessionRepository.findByFilmIdAndCinemaIdAndBeginTimeGreaterThanAndEndTimeLessThen(filmId, cinemaId, bt, et);
    }

    @Override
    @Caching(put = {
            @CachePut(key = "'filmSession.findById'+#result.getId()", condition = "#result != null")
    },evict = {
            @CacheEvict(key = "'filmSession.findFilmIdByTime'+#p0+#p1", condition = "#result != null"),
            @CacheEvict(key = "'filmSession.findByFilmAndCinemaAndTime'+#p0+#p1+#p2+#p3", condition = "#result != null"),
            @CacheEvict(key = "'filmSession.findByCinemaIdAndTime'+#p0+#p1+#p2", condition = "#result != null"),
//            @CacheEvict(key = "'filmSession.findByCinemasAntTime'+#result.getId()", condition = "#result != null")
    })

    public FilmSession save(FilmSession filmSession) {
        return filmSessionRepository.save(filmSession);
    }

    @Override
//    @Cacheable(cacheNames = "filmSession")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<Integer> findByCinemaIdAndTime(int cinemaId, Timestamp bt, Timestamp et) {
        return filmSessionRepository.findByCinemaIdAndTime(cinemaId, bt, et);
    }

    @Override
//    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<Integer> findByCinemasAntTime(List<Integer> cinemaId, Timestamp bt, Timestamp et) {
        return filmSessionRepository.findByCinemasAntTime(cinemaId, bt, et);
    }

}
