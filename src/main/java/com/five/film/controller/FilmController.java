package com.five.film.controller;

import com.five.film.service.FilmService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by msi on 2017/6/6.
 */
@RestController
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/film")
    public MyMessage getFilm() {
        return filmService.getFilm();
    }

}
