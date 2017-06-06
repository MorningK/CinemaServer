package com.five.film.cotroller;

import com.five.film.service.FilmService;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

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

    @PostMapping("/filmsession")
    public MyMessage getFilmSession(int filmId, int cinemaId, String time) {
        return filmService.getFilmSession(filmId, cinemaId, time);
    }

}
