package com.five.film.dao;

import com.five.film.model.Film;
import com.five.film.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

/**
 * Created by msi on 2017/6/6.
 */
@Repository
@CacheConfig(cacheNames = "film")
public class FilmDaoImpl implements FilmDao {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public Film findById(int id) {
        return filmRepository.findById(id);
    }

    @Override
    @CacheEvict(cacheNames = "film", allEntries = true)
    public void reload() {

    }
}
