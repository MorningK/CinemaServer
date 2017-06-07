package com.five.filmPic.dao;

import com.five.filmPic.model.FilmPic;
import com.five.filmPic.repository.FilmPicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by haoye on 17-6-7.
 */
@Repository
public class FilmPicDaoImpl implements FilmPicDao {

    @Autowired
    private FilmPicRepository filmPicRepository;

    @Override
    public FilmPic[] findByFilmIdAndType(int filmId, int type) {
        return filmPicRepository.findByFilmIdAndType(filmId, type);
    }
}
