package com.five.filmPic.controller;

import com.five.filmPic.service.FilmPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haoye on 17-6-7.
 */
@RestController
public class FilmPicController {

    @Autowired
    private FilmPicService filmPicService;

    @RequestMapping(value = "/filmPic/cover/{filmId}", method = RequestMethod.GET)
    public Object getFilmCover(@PathVariable("filmId")int filmId) {
        return filmPicService.getFilmCover(filmId);
    }

    @RequestMapping(value = "/filmPic/still/{filmId}", method = RequestMethod.GET)
    public Object getFilmStill(@PathVariable("filmId")int filmId) {
        return filmPicService.getFilmStill(filmId);
    }
}
