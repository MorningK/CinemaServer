package com.five.film.controller;

import com.five.film.service.FilmService;
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

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public Object getFilm() {
        return filmService.getFilm();
    }

}
