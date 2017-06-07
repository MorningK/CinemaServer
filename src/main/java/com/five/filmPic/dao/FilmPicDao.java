package com.five.filmPic.dao;

import com.five.filmPic.model.FilmPic;

/**
 * Created by haoye on 17-6-7.
 */
public interface FilmPicDao {
    FilmPic[] findByFilmIdAndType(int filmId, int type);
}
