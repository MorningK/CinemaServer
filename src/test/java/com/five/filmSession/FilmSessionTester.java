package com.five.filmSession;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.film.Util.FilmUtil;
import com.five.film.model.Film;
import com.five.filmSession.dao.FilmSessionDao;
import com.five.filmSession.model.FilmSession;
import com.five.filmSession.service.FilmSessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by msi on 2017/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class FilmSessionTester {

    @Autowired
    private FilmSessionService filmSessionService;

    @Autowired
    private FilmSessionDao filmSessionDao;



    private Map<Long, List<FilmSession>> filmSessions = new HashMap<>();
    private Map<Long, List<Integer> > films = new HashMap<>();

    private Object[] toObjectArray(FilmSession filmSession) {
        return new Object[] {
                filmSession.getId(),
                filmSession.getCinemaId(),
//                filmSession.getBeginTime(),
                filmSession.getClassification(),
//                filmSession.getEndTime(),
                filmSession.getFilmId(),
                filmSession.getHall(),
                filmSession.getHallSittingId(),
                filmSession.getPrice()
        };
    }

    @Before
    public void prepareFilmSession() {
        List<FilmSession> tfilmSessions = DataCreator.prepareFilmSession(25, 5, 10);
        for (FilmSession filmSession : tfilmSessions) {
            filmSessionDao.save(filmSession);
            Timestamp today = FilmUtil.getTheStartOfDay(new Date(filmSession.getBeginTime().getTime()));
            if (filmSessions.get(today.getTime()) == null) {
                List<FilmSession> fff = new ArrayList<FilmSession>();
                fff.add(filmSession);
                filmSessions.put(today.getTime(), fff);
            } else filmSessions.get(today.getTime()).add(filmSession);

            if (films.get(today.getTime()) == null) {
                List<Integer> ttt = new ArrayList<>();
                ttt.add(filmSession.getFilmId());
                films.put(today.getTime(), ttt);
            } else if (!(films.get(today.getTime()).contains(filmSession.getFilmId()))) {
                films.get(today.getTime()).add(filmSession.getFilmId());
            }
        }
    }

    @Test
    public void queryById() {
        for (Long id : filmSessions.keySet()) {
            for (FilmSession filmSession : filmSessions.get(id)) {
                FilmSession actul = filmSessionService.findById(filmSession.getId());
                Assert.assertArrayEquals(toObjectArray(filmSession), toObjectArray(actul));
            }
        }
    }

    @Test
    public void queryByTime() {
        for (Long id : filmSessions.keySet()) {
            List<Integer> expt = films.get(id);
            Date d = new Date(id);
            Timestamp bt = FilmUtil.getTheStartOfDay(d), st = FilmUtil.getTheEndOfDay(d);
            List<Integer> filmIds = filmSessionDao.findFilmIdByTime(bt, st);
            Assert.assertEquals(expt.size(), filmIds.size());
            int count = 0;
            for (Integer i : expt) {
                if (filmIds.contains(i)) {
                    count++;
                }
            }
            Assert.assertEquals(expt.size(), count);
        }
    }

    @Test
    public void getFilmSessionTest() {
        //this test write in FilmTester
    }
}
