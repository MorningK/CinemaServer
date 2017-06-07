package com.five.filmSession.controller;

import com.five.film.service.FilmService;
import com.five.filmSession.service.FilmSessionService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by msi on 2017/6/6.
 */
@RestController
public class FilmSessionController {
    @Autowired
    private FilmSessionService filmSessionServiceService;

    @PostMapping("/filmsession")
    public Object getFilmSession(int filmId, int cinemaId, String time) {
        return filmSessionServiceService.getFilmSession(filmId, cinemaId, time);
    }
}
