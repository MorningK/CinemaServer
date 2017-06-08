package com.five.cinemaRemark;

import com.five.CinemaApplication;
import com.five.cinema.model.Cinema;
import com.five.cinema.repository.CinemaRepository;
import com.five.cinema.service.CinemaService;
import com.five.cinemaRemark.model.CinemaRemark;
import com.five.cinemaRemark.repository.CinemaRemarkRepository;
import com.five.user.model.User;
import com.five.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2017/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class CinemaRemarkTester {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CinemaRemarkRepository cinemaRemarkRepository;

    private List<User> users = new ArrayList<>();
    private List<Cinema> cinemas = new ArrayList<>();
    private List<CinemaRemark> cinemaRemarks = new ArrayList<>();

    @Before
    public void prepareData() {

    }

    @Test
    public void queryByUserIdTest() {

    }
}
