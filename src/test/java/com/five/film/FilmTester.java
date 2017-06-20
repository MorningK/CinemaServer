package com.five.film;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.cinema.dao.CinemaDao;
import com.five.cinema.model.Cinema;
import com.five.cinema.service.CinemaService;
import com.five.cinemaRemark.model.CinemaRemark;
import com.five.film.Util.FilmUtil;
import com.five.film.dao.FilmDao;
import com.five.film.model.Film;
import com.five.film.repository.FilmRepository;
import com.five.film.service.FilmService;
import com.five.filmSession.dao.FilmSessionDao;
import com.five.filmSession.model.FilmSession;
import com.five.hallSitting.dao.HallSittingDao;
import com.five.hallSitting.model.HallSitting;
import com.five.user.model.MyMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.exp;

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
    private FilmDao filmDao;

    @Autowired
    private CinemaDao cinemaDao;

    @Autowired
    private FilmSessionDao filmSessionDao;

    @Autowired
    private HallSittingDao hallSittingDao;

    private List<Film> films = new ArrayList<Film>();
    private List<FilmSession> filmSessions = new ArrayList<>();
    private List<HallSitting> hallSittings = new ArrayList<>();
    private List<Cinema> cinemas = new ArrayList<>();
    private Map<Integer, Integer> exptFilms = new HashMap<>();
    private Map<Integer, List<Film>> divideByCitycode = new HashMap<>();
    private Map<Integer, List<Film>> divideByCinemaId = new HashMap<>();


    @Before
    public void prepareData() {
        /*
        * Insert 10 random films into database
        * */
        films = DataCreator.prepareFilm(10);
        for (int i = 0; i < films.size(); i++) {
            filmDao.save(films.get(i));
        }

        cinemas = DataCreator.prepareCinema(5);
        for (Cinema cinema : cinemas) {
            cinemaDao.save(cinema);
        }

        filmSessions = DataCreator.prepareFilmSession(25, 5, 10);
        for (FilmSession filmSession : filmSessions) {
            filmSessionDao.save(filmSession);
            Timestamp today = FilmUtil.getTheStartOfDay(new Date());
            Timestamp end = FilmUtil.getTheEndOfDay(new Date());
            if (filmSession.getBeginTime().getTime() >= today.getTime()&&filmSession.getBeginTime().getTime() <= end.getTime()) {
                exptFilms.put(filmSession.getFilmId(), 1);
                int tempcity = cinemas.get(filmSession.getCinemaId() - 1).getCitycode();
                if (divideByCitycode.get(tempcity) == null) {
                    List<Film> templ = new ArrayList<>();
                    templ.add(films.get(filmSession.getFilmId() - 1));
                    divideByCitycode.put(tempcity, templ);
                } else {
                    boolean flag = true;
                    Film tempf = films.get(filmSession.getFilmId() - 1);
                    for (Film film : divideByCitycode.get(tempcity)) {
                        if (film.getId() == tempf.getId()) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) divideByCitycode.get(tempcity).add(tempf);
                }
                int tempcid = filmSession.getCinemaId();
                if (divideByCinemaId.get(tempcid) == null) {
                    List<Film> templ = new ArrayList<>();
                    templ.add(films.get(filmSession.getFilmId() - 1));
                    divideByCinemaId.put(tempcid, templ);
                } else {
                    boolean flag = true;
                    Film tempf = films.get(filmSession.getFilmId() - 1);
                    for (Film film : divideByCinemaId.get(tempcid)) {
                        if (film.getId() == tempf.getId()) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) divideByCinemaId.get(tempcid).add(tempf);
                }
            }

        }

        hallSittings = DataCreator.prepareHallSitting(25, 10,10);
        for (HallSitting hallSitting : hallSittings) {
            hallSittingDao.save(hallSitting);
        }
//        filmService.reload();
    }

    private Object[] filmToArray(Film a) {
        return new Object[] {
                a.getId(),
                a.getName(),
                a.getActor(),
                a.getCategory(),
                a.getDirector(),
                a.getLanguage(),
//                a.getPublishTime().getTime(),
                a.getLastTime(),
                a.getNation(),
                a.getScore(),
                a.getSummary()
        };
    }

    @Test
    public void queryTest() throws Exception {
        for (int i = 0; i < films.size(); i++) {
            Film expe = films.get(i);
            Film actual = filmService.findById(expe.getId());
            Assert.assertArrayEquals(filmToArray(expe), filmToArray(actual));
        }
    }

    @Test
    public void getFilmTest() throws Exception {
        for (Integer citycode : divideByCitycode.keySet()) {
            MyMessage myMessage = (MyMessage) filmService.getFilm(citycode);
            Assert.assertEquals(1, myMessage.getStatus());
            if (myMessage.getStatus() == 1) {
                List<Film> actul = (List<Film>)myMessage.getMessage();
                List<Film> expt = divideByCitycode.get(citycode);
                Assert.assertEquals(expt.size(), actul.size());
                for (int i = 0 ; i < expt.size(); i++) {
                    Assert.assertArrayEquals(filmToArray(expt.get(i)), filmToArray(actul.get(i)));
                }
            }
        }

    }


    @Test
    public void getFilmByCinemaIdTest() throws Exception {
        for (Integer cid : divideByCinemaId.keySet()) {
            MyMessage myMessage = (MyMessage) filmService.getFilmByCinema(cid);
            Assert.assertEquals(1, myMessage.getStatus());
            if (myMessage.getStatus() == 1) {
                List<Film> actul = (List<Film>)myMessage.getMessage();
                List<Film> expt = divideByCinemaId.get(cid);
                Assert.assertEquals(expt.size(), actul.size());
                for (int i = 0 ; i < expt.size(); i++) {
                    Assert.assertArrayEquals(filmToArray(expt.get(i)), filmToArray(actul.get(i)));
                }
            }
        }
    }
}
