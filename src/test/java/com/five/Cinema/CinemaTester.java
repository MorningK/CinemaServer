package com.five.Cinema;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.cinema.dao.CinemaDao;
import com.five.cinema.model.Cinema;
import com.five.cinema.repository.CinemaRepository;
import com.five.cinema.service.CinemaService;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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

    private List<Cinema> cinemas = null;
    private List<Cinema> cinemasWithSameCityCode = null;

    public CinemaTester() {
//        System.out.println("constttttt");

    }

    @Before
    public void prepareData() {
        /*
        * Insert 10 random cinema into database
        * */
        if (cinemas == null) {
            System.out.println("prepare cinemas");
            cinemas = DataCreator.prepareCinema(10);
            for (int i = 0; i < cinemas.size(); i++) {
                cinemaDao.save(cinemas.get(i));
            }
        }

        if (cinemasWithSameCityCode == null) {
            System.out.println("prepare cwcs");
            cinemasWithSameCityCode = DataCreator.prepareCinemaWithSameCity(200, 11, 0.5, 0.5);
            for (int i = 0; i < cinemasWithSameCityCode.size(); i++) {
                cinemaDao.save(cinemasWithSameCityCode.get(i));
            }
        }

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
    public void queryByIdTest() throws Exception {
        for (int i = 0; i < cinemas.size(); i++) {
            Cinema actual = cinemas.get(i);
            Cinema expe = cinemaService.findById(actual.getId());
            Assert.assertArrayEquals(cinemaToArray(expe), cinemaToArray(actual));
        }
    }

    @Test
    public void queryByLocationTest() throws Exception {
        MyMessage message = (MyMessage)(cinemaService.getCinemas(11, 0.5,0.5,0));
        Assert.assertEquals(1, message.getStatus());
        if (message.getStatus() == 1) {
            List<Cinema> expel = (List<Cinema>)(message.getMessage());
            for (int i = 0; i < cinemasWithSameCityCode.size(); i++) {
                Cinema actual = cinemasWithSameCityCode.get(i);
                Cinema expe = expel.get(i);
                Assert.assertArrayEquals(cinemaToArray(expe), cinemaToArray(actual));
            }
        }
    }
}
