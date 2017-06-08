package com.five.film;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.film.model.Film;
import com.five.film.repository.FilmRepository;
import com.five.film.service.FilmService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by msi on 2017/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class FilmTester {
    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    private List<Film> films = new ArrayList<Film>();

    @Before
    public void prepareData() {
        /*
        * Insert 10 random films into database
        * */
        films = DataCreator.prepareFilm(10);
        for (int i = 0; i < films.size(); i++) {
            filmRepository.save(films.get(i));
        }
        filmService.reload();
    }

    private Object[] filmToArray(Film a) {
        return new Object[] {
                a.getId(),
                a.getName(),
                a.getActor(),
                a.getCategory(),
                a.getDirector(),
                a.getLanguage(),
                a.getPublishTime().getTime(),
                a.getLastTime(),
                a.getNation(),
                a.getScore(),
                a.getSummary()
        };
    }

    @Test
    public void queryTest() throws Exception {
//        cinemaRepository.reload();
        for (int i = 0; i < films.size(); i++) {
            Film actual = films.get(i);
            Film expe = filmService.findById(actual.getId());
            Assert.assertArrayEquals(filmToArray(expe), filmToArray(actual));
    }
    }

}
