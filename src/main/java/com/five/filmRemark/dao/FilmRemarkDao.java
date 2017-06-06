package com.five.filmRemark.dao;

import com.five.filmRemark.model.FilmRemark;

/**
 * Created by haoye on 17-6-6.
 */
public interface FilmRemarkDao {
    void save(FilmRemark filmRemark);
    FilmRemark[] findByFilmId(int filmId);
    FilmRemark[] findByUserId(int userId);
}
