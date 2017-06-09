package com.five.film.repository;

import com.five.film.model.Film;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msi on 2017/6/6.
 */
//@CacheConfig(cacheNames = "film")
public interface FilmRepository extends JpaRepository<Film, Integer> {
//    @Cacheable
    Film findById(int id);
}
