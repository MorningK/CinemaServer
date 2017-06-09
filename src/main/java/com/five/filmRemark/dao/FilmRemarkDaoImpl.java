package com.five.filmRemark.dao;

import com.five.filmRemark.model.FilmRemark;
import com.five.filmRemark.repository.FilmRemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
@CacheConfig(cacheNames = "filmRemark")
public class FilmRemarkDaoImpl implements FilmRemarkDao {

    @Autowired
    private FilmRemarkRepository filmRemarkRepository;

    @Override
    @Caching(put = {
    @CachePut(key = "#p0.getFilmId()"),
    @CachePut(key = "#p0.getUserId()")
    })
    public void save(FilmRemark filmRemark) {
        filmRemarkRepository.save(filmRemark);
    }

    @Override
    @Cacheable
    public FilmRemark[] findByFilmId(int filmId) {
        return filmRemarkRepository.findByFilmId(filmId);
    }

    @Override
    @Cacheable
    public FilmRemark[] findByUserId(int userId) {
        return filmRemarkRepository.findByUserId(userId);
    }
}
