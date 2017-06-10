package com.five.filmSession.controller;

import com.five.filmSession.service.FilmSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by msi on 2017/6/6.
 */
@RestController
public class FilmSessionController {
    @Autowired
    private FilmSessionService filmSessionServiceService;

    @RequestMapping(value = "/filmsession", method = RequestMethod.POST)
    public Object getFilmSession(int filmId, int cinemaId, String time) {
        return filmSessionServiceService.getFilmSession(filmId, cinemaId, time);
    }

}
