package com.five.filmRemark.dao;

import com.five.film.repository.FilmRepository;
import com.five.filmRemark.model.FilmRemark;

import java.util.List;

/**
 * Created by haoye on 17-6-6.
 */
public interface FilmRemarkDao {
    FilmRemark save(FilmRemark filmRemark);
    List<FilmRemark> findByFilmId(int filmId);
    List<FilmRemark> findByUserId(int userId);
}
