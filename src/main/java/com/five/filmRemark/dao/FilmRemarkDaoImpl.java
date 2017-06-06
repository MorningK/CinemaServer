package com.five.filmRemark.dao;

import com.five.filmRemark.model.FilmRemark;
import com.five.filmRemark.repository.FilmRemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
public class FilmRemarkDaoImpl implements FilmRemarkDao {

    @Autowired
    private FilmRemarkRepository filmRemarkRepository;

    @Override
    public void save(FilmRemark filmRemark) {
        filmRemarkRepository.save(filmRemark);
    }

    @Override
    public FilmRemark[] findByFilmId(int filmId) {
        return filmRemarkRepository.findByFilmId(filmId);
    }

    @Override
    public FilmRemark[] findByUserId(int userId) {
        return filmRemarkRepository.findByUserId(userId);
    }
}
