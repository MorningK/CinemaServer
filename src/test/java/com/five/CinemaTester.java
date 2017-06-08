package com.five;

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

    private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();

    @Before
    public void before() {
        /*
        * Insert 10 cinema into database
        * */
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "cinema" + Integer.toString(i);
            String address = "address" + Integer.toString(i);
            String phone = Integer.toString(i);
            double lo = random.nextDouble();
            double la = random.nextDouble();
            int cityCode = random.nextInt();
            Cinema cinema = new Cinema(name, address,phone, lo, la, cityCode);
            cinemas.add(cinemaRepository.save(cinema));
        }
    }
    @Test
    public void queryTest() throws Exception {
        for (int i = 0; i < cinemas.size(); i++) {
            Assert.assertEquals(cinemas.get(i), cinemaService.findById(cinemas.get(i).getId()));
        }
    }
}
