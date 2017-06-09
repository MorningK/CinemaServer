package com.five.cinemaPic;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.cinema.model.Cinema;
import com.five.cinema.repository.CinemaRepository;
import com.five.cinemaPic.dao.CinemaPicDao;
import com.five.cinemaPic.model.CinemaPic;
import com.five.cinemaPic.repository.CinemaPicRepository;
import com.five.cinemaPic.service.CinemaPicService;
import com.five.user.model.MyMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

/**
 * Created by msi on 2017/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class CinemaPicTester {
    @Autowired
    private CinemaPicService cinemaPicService;

    @Autowired
    private CinemaPicDao cinemaPicDao;

    @Autowired
    private CinemaPicRepository cinemaPicRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    private List<CinemaPic> cinemaPicList = new ArrayList<>();
    private List<Cinema> cinemas = new ArrayList<>();
    private HashMap<Integer, List<CinemaPic> > covers = new HashMap<>();
    private HashMap<Integer, List<CinemaPic> > insides = new HashMap<>();

    private Object[] toObjectArray(CinemaPic cinemaPic) {
        return new Object[] {
                cinemaPic.getId(),
                cinemaPic.getCinemaId(),
                cinemaPic.getPath(),
                cinemaPic.getType()
        };
    }

    @Before
    public void prepareData() {
        /*
        * Insert 10 random samples into database
        * */
        cinemas = DataCreator.prepareCinema(10);
        for (Cinema cinema : cinemas) {
            cinemaRepository.save(cinema);
        }
        cinemaPicList = DataCreator.prepareCinemaPic(100, 10);
        for (CinemaPic cinemaPic : cinemaPicList) {
            cinemaPicRepository.save(cinemaPic);
            if (cinemaPic.getType() == CinemaPic.CP_COVER) {
                if (covers.get(cinemaPic.getCinemaId()) == null) {
                    List<CinemaPic> temp = new ArrayList<>();
                    temp.add(cinemaPic);
                    covers.put(cinemaPic.getCinemaId(), temp);
                } else {
                    covers.get(cinemaPic.getCinemaId()).add(cinemaPic);
                }
            } else {
                if (insides.get(cinemaPic.getCinemaId()) == null) {
                    List<CinemaPic> temp = new ArrayList<>();
                    temp.add(cinemaPic);
                    insides.put(cinemaPic.getCinemaId(), temp);
                } else {
                    insides.get(cinemaPic.getCinemaId()).add(cinemaPic);
                }
            }

        }

        cinemaPicService.reload();
    }

    @Test
    public void queryCoverByCinemaIdTest() {
        for (Cinema cinema : cinemas) {
            List<CinemaPic> expts = covers.get(cinema.getId());
            MyMessage message = (MyMessage)cinemaPicService.getCoverByCinemaId(cinema.getId());
            if (expts == null) {
                Assert.assertEquals(0, message.getStatus());
            } else {
                List<CinemaPic> cinemaPics = (List<CinemaPic>)message.getMessage();
                int count = 0;
                for (CinemaPic cinemaPic : cinemaPics) {
                    for (int i = 0; i < expts.size(); i++) {
                        if (expts.get(i).getId() == cinemaPic.getId()) {
                            Assert.assertArrayEquals(toObjectArray(expts.get(i)), toObjectArray(cinemaPic));
                            count++;
                            break;
                        }
                    }
                }
                Assert.assertEquals(count, cinemaPics.size());
            }
        }
    }

    @Test
    public void queryInsideByCinemaIdTest() {
        for (Cinema cinema : cinemas) {
            List<CinemaPic> cinemaPics = (List<CinemaPic>)((MyMessage)cinemaPicService.getInsideByCinemaId(cinema.getId())).getMessage();
            if (cinemaPics == null) {
                Assert.assertNull(insides.get(cinema.getId()));
            } else {
                int count = 0;
                for (CinemaPic cinemaPic : cinemaPics) {
                    List<CinemaPic> expts = insides.get(cinema.getId());
                    for (int i = 0; i < expts.size(); i++) {
                        if (expts.get(i).getId() == cinemaPic.getId()) {
                            Assert.assertArrayEquals(toObjectArray(expts.get(i)), toObjectArray(cinemaPic));
                            count++;
                            break;
                        }
                    }
                }
                Assert.assertEquals(count, cinemaPics.size());
            }
        }
    }
}
