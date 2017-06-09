package com.five.filmPic;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.film.model.Film;
import com.five.filmPic.model.FilmPic;
import com.five.film.model.Film;
import com.five.filmPic.model.FilmPic;
import com.five.film.dao.FilmDao;
import com.five.film.model.Film;
import com.five.filmPic.dao.FilmPicDao;
import com.five.filmPic.model.FilmPic;
import com.five.filmPic.repository.FilmPicRepository;
import com.five.filmPic.service.FilmPicService;
import com.five.user.model.MyMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by msi on 2017/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class FilmPicTester {

    @Autowired
    private FilmPicService filmPicService;

    @Autowired
    private FilmPicDao filmPicDao;

    @Autowired
    private FilmPicRepository filmPicRepository;
    
    @Autowired
    private FilmDao filmDao;

    private List<FilmPic> filmPics = new ArrayList<>();
    private List<Film> films = new ArrayList<>();
    private HashMap<Integer, List<FilmPic> > covers = new HashMap<>();
    private HashMap<Integer, List<FilmPic> > insides = new HashMap<>();

    private Object[] toObjectArray(FilmPic filmPic) {
        return new Object[] {
                filmPic.getId(),
                filmPic.getFilmId(),
                filmPic.getPath(),
                filmPic.getType()
        };
    }

    @Before
    public void prepareData() {
        films = DataCreator.prepareFilm(10);
        for (Film film : films) {
            filmDao.save(film);
        }
        filmPics = DataCreator.prepareFilmPic(100, 10);
        for (FilmPic filmPic : filmPics) {
            filmPicRepository.save(filmPic);
            if (filmPic.getType() == FilmPic.COVER) {
                if (covers.get(filmPic.getFilmId()) == null) {
                    List<FilmPic> temp = new ArrayList<>();
                    temp.add(filmPic);
                    covers.put(filmPic.getFilmId(), temp);
                } else {
                    covers.get(filmPic.getFilmId()).add(filmPic);
                }
            } else {
                if (insides.get(filmPic.getFilmId()) == null) {
                    List<FilmPic> temp = new ArrayList<>();
                    temp.add(filmPic);
                    insides.put(filmPic.getFilmId(), temp);
                } else {
                    insides.get(filmPic.getFilmId()).add(filmPic);
                }
            }

        }
    }

    @Test
    public void queryCoverByFilmIdTest() {
        for (Film film : films) {
            List<FilmPic> expts = covers.get(film.getId());
            MyMessage message = (MyMessage)filmPicService.getFilmCover(film.getId());
            if (expts == null) {
                Assert.assertEquals(0, message.getStatus());
            } else {
                List<FilmPic> filmPics = (List<FilmPic>)message.getMessage();
                int count = 0;
                for (FilmPic filmPic : filmPics) {
                    for (int i = 0; i < expts.size(); i++) {
                        if (expts.get(i).getId() == filmPic.getId()) {
                            Assert.assertArrayEquals(toObjectArray(expts.get(i)), toObjectArray(filmPic));
                            count++;
                            break;
                        }
                    }
                }
                Assert.assertEquals(count, filmPics.size());
            }
        }
    }

    @Test
    public void queryInsideByFilmIdTest() {
        for (Film film : films) {
            List<FilmPic> filmPics = (List<FilmPic>)((MyMessage)filmPicService.getFilmStill(film.getId())).getMessage();
            if (filmPics == null) {
                Assert.assertNull(insides.get(film.getId()));
            } else {
                int count = 0;
                for (FilmPic filmPic : filmPics) {
                    List<FilmPic> expts = insides.get(film.getId());
                    for (int i = 0; i < expts.size(); i++) {
                        if (expts.get(i).getId() == filmPic.getId()) {
                            Assert.assertArrayEquals(toObjectArray(expts.get(i)), toObjectArray(filmPic));
                            count++;
                            break;
                        }
                    }
                }
                Assert.assertEquals(count, filmPics.size());
            }
        }
    }
}
