package com.five.filmPic.dao;

import com.five.filmPic.model.FilmPic;
import com.five.filmPic.repository.FilmPicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by haoye on 17-6-7.
 */

@Repository
@CacheConfig(cacheNames = "filmPic")
public class FilmPicDaoImpl implements FilmPicDao {

    @Autowired
    private FilmPicRepository filmPicRepository;

    @Override
//    @Cacheable(cacheNames = "filmPic", condition = "#result != null and #result.size() > 0")
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public List<FilmPic> findByFilmIdAndType(int filmId, int type) {
        return filmPicRepository.findByFilmIdAndType(filmId, type);
    }
}
