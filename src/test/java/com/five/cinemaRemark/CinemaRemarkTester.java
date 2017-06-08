package com.five.cinemaRemark;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.cinema.model.Cinema;
import com.five.cinema.repository.CinemaRepository;
import com.five.cinema.service.CinemaService;
import com.five.cinemaRemark.model.CinemaRemark;
import com.five.cinemaRemark.repository.CinemaRemarkRepository;
import com.five.cinemaRemark.service.CinemaRemarkService;
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

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private CinemaRemarkService cinemaRemarkService;

    private List<User> users = new ArrayList<>();
    private List<Cinema> cinemas = new ArrayList<>();
    private List<CinemaRemark> cinemaRemarks = new ArrayList<>();
    private HashMap<Integer, List<Integer>> userCRs = new HashMap<>();
    private HashMap<Integer, List<Integer>> cinemaCRs = new HashMap<>();

    private Object[] toObjectArray(CinemaRemark cinemaRemark) {
        return new Object[] {
                cinemaRemark.getId(),
                cinemaRemark.getCinemaId(),
                cinemaRemark.getContent(),
                cinemaRemark.getTime(),
                cinemaRemark.getUserId()
        };
    }

    @Before
    public void prepareData() {
        cinemas = DataCreator.prepareCinema(10);
        for (int i = 0; i < 10; i++) {
            cinemaRepository.save(cinemas.get(i));
        }

        users = DataCreator.prepareUser(100);
        for (int i = 0; i < 10; i++) {
            userService.doRegister(users.get(i));
        }

        List<CinemaRemark> cinemaRemarks = DataCreator.prepareCinemaRemark(1000, 100, 10);
        for (int i = 0; i < cinemaRemarks.size(); i++) {
            CinemaRemark cinemaRemark = cinemaRemarks.get(i);
            cinemaRemarkRepository.save(cinemaRemark);
            int userId = cinemaRemark.getUserId(), cinemaId = cinemaRemark.getCinemaId();
            if (userCRs.get(userId) == null) {
                List<Integer> tempCR = new ArrayList<>();
                tempCR.add(i);
                userCRs.put(userId, tempCR);
            } else userCRs.get(userId).add(i);
            if (cinemaCRs.get(cinemaId) == null) {
                List<Integer> tempCR = new ArrayList<>();
                tempCR.add(i);
                cinemaCRs.put(cinemaId, tempCR);
            } else cinemaCRs.get(cinemaId).add(i);
        }

    }

    @Test
    public void queryByUserIdTest() {
        for (User user : users) {
            CinemaRemark[] cinemaRemarka =
                    (CinemaRemark[])(((MyMessage)cinemaRemarkService.getCinemaRemarkByUserId(user.getId())).getMessage());
            if (userCRs.get(user.getId()) == null) {
                Assert.assertEquals(0, cinemaRemarka.length);
            } else {
                List<Integer> ids = userCRs.get(user.getId());
                Assert.assertEquals(ids, cinemaRemarka.length);
                for (int i = 0; i < ids.size(); i++) {
                    Assert.assertArrayEquals(toObjectArray(cinemaRemarks.get(ids.get(i))), toObjectArray(cinemaRemarka[i]));
                }
            }
        }
    }

    @Test
    public void queryByCinemaIdTest() {
        for (Cinema cinema : cinemas) {
            CinemaRemark[] cinemaRemarka =
                    (CinemaRemark[])(((MyMessage)cinemaRemarkService.getCinemaRemarkByCinemaId(cinema.getId())).getMessage());
            if (cinemaCRs.get(cinema.getId()) == null) {
                Assert.assertEquals(0, cinemaRemarka.length);
            } else {
                List<Integer> ids = cinemaCRs.get(cinema.getId());
                Assert.assertEquals(ids, cinemaRemarka.length);
                for (int i = 0; i < ids.size(); i++) {
                    Assert.assertArrayEquals(toObjectArray(cinemaRemarks.get(ids.get(i))), toObjectArray(cinemaRemarka[i]));
                }
            }
        }
    }
}
