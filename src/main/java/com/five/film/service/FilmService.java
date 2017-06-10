package com.five.film.service;

import com.five.film.model.Film;
import com.five.user.model.MyMessage;

import java.sql.Timestamp;

/**
 * Created by msi on 2017/6/6.
 */
public interface FilmService {
    Object getFilm(int citycode);
    Film findById(int id);
    void reload();
    Object getFilmByCinema(int cinemaId);
}
