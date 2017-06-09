package com.five.filmRemark.repository;

import com.five.filmRemark.model.FilmRemark;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoye on 17-6-6.
 */

public interface FilmRemarkRepository extends JpaRepository<FilmRemark, Integer> {
//    @Cacheable
    FilmRemark[] findByUserId(int userId);

//    @Cacheable
    FilmRemark[] findByFilmId(int filmId);
}
