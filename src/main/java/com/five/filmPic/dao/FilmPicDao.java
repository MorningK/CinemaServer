package com.five.filmPic.dao;

import com.five.film.model.Film;
import com.five.filmPic.model.FilmPic;

import java.util.List;

/**
 * Created by haoye on 17-6-7.
 */
public interface FilmPicDao {
    List<FilmPic> findByFilmIdAndType(int filmId, int type);
    FilmPic save(FilmPic filmPic);
}
