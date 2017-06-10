package com.five.filmRemark.dao;

import com.five.filmRemark.model.FilmRemark;
import com.five.filmRemark.repository.FilmRemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
@CacheConfig(cacheNames = "filmRemark")
public class FilmRemarkDaoImpl implements FilmRemarkDao {

    @Autowired
    private FilmRemarkRepository filmRemarkRepository;

    @Override
//    @Caching(evict = {
//    @CacheEvict(value = "filmRemark", key = "#p0.getFilmId()"),
//    @CacheEvict(value = "filmRemark", key = "#p0.getUserId()")
//    })
    @CachePut(keyGenerator = "wiselyKeyGenerator")
    public FilmRemark save(FilmRemark filmRemark) {
        return filmRemarkRepository.save(filmRemark);
    }

    @Override
//    @Cacheable(value = "filmRemark")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<FilmRemark> findByFilmId(int filmId) {
        return filmRemarkRepository.findByFilmId(filmId);
    }

    @Override
//    @Cacheable(value = "filmRemark")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<FilmRemark> findByUserId(int userId) {
        return filmRemarkRepository.findByUserId(userId);
    }
}
