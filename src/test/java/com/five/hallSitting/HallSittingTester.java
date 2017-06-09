package com.five.hallSitting;

import com.five.CinemaApplication;
import com.five.hallSitting.dao.HallSittingDao;
import com.five.hallSitting.model.HallSitting;
import com.five.hallSitting.service.HallSittingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by msi on 2017/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class HallSittingTester {

    @Autowired
    private HallSittingService hallSittingService;

    @Autowired
    private HallSittingDao hallSittingDao;

    @Before
    public void prepareData() {

    }

    @Test
    public void querySitTest() {

    }
}
