package com.five.filmPic.controller;

import com.five.filmPic.service.FilmPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haoye on 17-6-7.
 */
@RestController
public class FilmPicController {

    @Autowired
    private FilmPicService filmPicService;

    @GetMapping("/filmPic/cover/{filmId}")
    public Object getFilmCover(@PathVariable("filmId")int filmId) {
        return filmPicService.getFilmCover(filmId);
    }

    @GetMapping("/filmPic/still/{filmId}")
    public Object getFilmStill(@PathVariable("filmId")int filmId) {
        return filmPicService.getFilmStill(filmId);
    }
}
