package com.five;

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

    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
    private ArrayList<Cinema> cinemasWithSameCityCode = new ArrayList<Cinema>();

    @Before
    public void prepareData() {
        /*
        * Insert 10 random cinema into database
        * */
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "cinema" + Integer.toString(i);
            String address = "address" + Integer.toString(i);
            String phone = Integer.toString(i);
            double lo = random.nextDouble();
            double la = random.nextDouble();
            int cityCode = random.nextInt(10);
            Cinema cinema = new Cinema(name, address,phone, lo, la, cityCode);
            Cinema afterc = cinemaRepository.save(cinema);
            cinemas.add(afterc);
        }

        /*
        * Insert 10 cinema with same citycode
        * */
        for (int i = 10; i < 10; i++) {
            String name = "cinema" + Integer.toString(i);
            String address = "address" + Integer.toString(i);
            String phone = Integer.toString(i);
            double lo = 0.5+0.1*i;
            double la = 0.5+0.1*i;
            int cityCode = 11;
            Cinema cinema = new Cinema(name, address,phone, lo, la, cityCode);
            Cinema afterc = cinemaRepository.save(cinema);
            cinemasWithSameCityCode.add(afterc);
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
