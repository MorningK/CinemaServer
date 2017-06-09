package com.five.film.dao;

import com.five.film.model.Film;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface FilmDao {
    Film findById(int id);
    void reload();
    Film save(Film film);
}
