package com.five.film.controller;

import com.five.film.model.Film;
import com.five.film.service.FilmService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by msi on 2017/6/6.
 */
@RestController
public class FilmController {
    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/film", method = RequestMethod.POST)
    public MyMessage getFilm(int citycode) {
        return filmService.getFilm(citycode);
    }


    @RequestMapping(value = "/filmByCinemaId", method = RequestMethod.POST)
    public MyMessage getFilmByCitycode(int cinemaId) {
        return filmService.getFilmByCinema(cinemaId);
    }

    @RequestMapping(value = "/findFilm", method = RequestMethod.GET)
    public MyMessage findFilm(int filmId) {
        Film film = filmService.findById(filmId);
        if (film == null) {
            return new MyMessage(0, "电影不存在");
        }
        return new MyMessage(1, film);
    }
}
