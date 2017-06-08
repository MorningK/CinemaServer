package com.five.Cinema;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.cinema.dao.CinemaDao;
import com.five.cinema.model.Cinema;
import com.five.cinema.repository.CinemaRepository;
import com.five.cinema.service.CinemaService;
import com.five.user.model.User;
import com.five.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by msi on 2017/6/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class CinemaTester {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private CinemaDao cinemaDao;

    private List<Cinema> cinemas = new ArrayList<Cinema>();
    private List<Cinema> cinemasWithSameCityCode = new ArrayList<Cinema>();

    @Before
    public void prepareData() {
        /*
        * Insert 10 random cinema into database
        * */
        cinemas = DataCreator.prepareCinema(10);
        for (int i = 0; i < 10; i++) {
            cinemaRepository.save(cinemas.get(i));
        }

        /*
        * Insert 10 cinema with same citycode
        * */
        cinemasWithSameCityCode = DataCreator.prepareCinemaWithSameCity(10, 21);
        for (int i = 0; i < 10; i++) {
            cinemaRepository.save(cinemasWithSameCityCode.get(i));
        }
        cinemaService.reload();
    }

    public Object[] cinemaToArray(Cinema a) {
        return new Object[] {
                a.getId(),
                a.getAddress(),
                a.getCitycode(),
                a.getLatitude(),
                a.getLongtitude(),
                a.getName(),
                a.getPhone()
        };
    }

    @Test
    public void queryTest() throws Exception {
//        cinemaRepository.reload();
        for (int i = 0; i < cinemas.size(); i++) {
            Cinema actual = cinemas.get(i);
            Cinema expe = cinemaService.findById(actual.getId());
            Assert.assertArrayEquals(cinemaToArray(expe), cinemaToArray(actual));
        }
    }

    @Test
    public void locationTest() throws Exception {
        List<Cinema> expel = cinemaDao.findByLocation(11, 0.5,0.5,0);
        for (int i = 0; i < cinemasWithSameCityCode.size(); i++) {
            Cinema actual = cinemasWithSameCityCode.get(i);
            Cinema expe = expel.get(i);
            Assert.assertArrayEquals(cinemaToArray(expe), cinemaToArray(actual));
        }
    }
}
