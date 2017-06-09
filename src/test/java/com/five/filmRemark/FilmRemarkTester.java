package com.five.filmRemark;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.film.model.Film;
import com.five.film.repository.FilmRepository;
import com.five.filmRemark.dao.FilmRemarkDao;
import com.five.filmRemark.model.FilmRemark;
import com.five.filmRemark.service.FilmRemarkService;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserService;
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
public class FilmRemarkTester {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FilmRemarkDao filmRemarkDao;

    @Autowired
    private FilmRemarkService filmRemarkService;

    private List<User> users = new ArrayList<>();
    private List<Film> films = new ArrayList<>();
    private List<FilmRemark> filmRemarks = new ArrayList<>();
    private HashMap<Integer, List<Integer>> userCRs = new HashMap<>();
    private HashMap<Integer, List<Integer>> filmCRs = new HashMap<>();

    private Object[] toObjectArray(FilmRemark filmRemark) {
        return new Object[] {
//                filmRemark.getId(),
                filmRemark.getFilmId(),
                filmRemark.getContent(),
//                filmRemark.getTime(),
                filmRemark.getUserId()
        };
    }

    @Before
    public void prepareData() {
        films = DataCreator.prepareFilm(5);
        for (int i = 0; i < films.size(); i++) {
            filmRepository.save(films.get(i));
        }

        users = DataCreator.prepareUser(5);
        for (int i = 0; i < users.size(); i++) {
            userService.doRegister(users.get(i));
        }

        filmRemarks = DataCreator.prepareFilmRemark(25, 5, 5);
        for (int i = 0; i < filmRemarks.size(); i++) {
            FilmRemark filmRemark = filmRemarks.get(i);
            filmRemarkService.postFilmRemark(filmRemark.getUserId(), filmRemark.getFilmId(), filmRemark.getContent());
            int userId = filmRemark.getUserId(), filmId = filmRemark.getFilmId();
            if (userCRs.get(userId) == null) {
                List<Integer> tempCR = new ArrayList<>();
                tempCR.add(i);
                userCRs.put(userId, tempCR);
            } else userCRs.get(userId).add(i);
            if (filmCRs.get(filmId) == null) {
                List<Integer> tempCR = new ArrayList<>();
                tempCR.add(i);
                filmCRs.put(filmId, tempCR);
            } else filmCRs.get(filmId).add(i);
        }

    }


    @Test
    public void queryByUserIdTest() {
        for (User user : users) {
            MyMessage message = (MyMessage)filmRemarkService.getFilmRemarkByUserId(user.getId());
            if (userCRs.get(user.getId()) == null) {
                Assert.assertEquals(0, message.getStatus());
            } else {
                Assert.assertEquals(1, message.getStatus());
                List<FilmRemark> filmRemarka =
                        (List<FilmRemark>)(message.getMessage());
                List<Integer> ids = userCRs.get(user.getId());
                Assert.assertEquals(ids.size(), filmRemarka.size());
                for (int i = 0; i < ids.size(); i++) {
                    Assert.assertArrayEquals(toObjectArray(filmRemarks.get(ids.get(i))), toObjectArray(filmRemarka.get(i)));
                }
            }
        }
    }

    @Test
    public void queryByFilmIdTest() {
        for (Film film : films) {
            MyMessage message = (MyMessage)filmRemarkService.getFilmRemarkByFilmId(film.getId());
            if (filmCRs.get(film.getId()) == null) {
                Assert.assertEquals(0, message.getStatus());
            } else {
                Assert.assertEquals(1, message.getStatus());
                List<FilmRemark> filmRemarka =
                        (List<FilmRemark>)(message.getMessage());
                List<Integer> ids = filmCRs.get(film.getId());
                Assert.assertEquals(ids.size(), filmRemarka.size());
                for (int i = 0; i < ids.size(); i++) {
                    Assert.assertArrayEquals(toObjectArray(filmRemarks.get(ids.get(i))), toObjectArray(filmRemarka.get(i)));
                }
            }
        }
    }
}
