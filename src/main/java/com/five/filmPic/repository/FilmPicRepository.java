package com.five.filmPic.repository;

import com.five.filmPic.model.FilmPic;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by haoye on 17-6-7.
 */
//@CacheConfig(cacheNames = "filmPic")
public interface FilmPicRepository extends JpaRepository<FilmPic, Integer> {
//    @Cacheable
    List<FilmPic> findByFilmIdAndType(int filmId, int type);
}
