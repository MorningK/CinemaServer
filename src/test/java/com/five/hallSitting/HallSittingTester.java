package com.five.hallSitting;

import com.five.CinemaApplication;
import com.five.Util.DataCreator;
import com.five.hallSitting.dao.HallSittingDao;
import com.five.hallSitting.model.HallSitting;
import com.five.hallSitting.model.OneSit;
import com.five.hallSitting.model.Sits;
import com.five.hallSitting.service.HallSittingService;
import com.five.hallSitting.utils.SitUtil;
import net.sf.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private List<HallSitting> hallSittings = new ArrayList<>();
    private List<Sits> sits = new ArrayList<>();

    @Before
    public void prepareData() {
        hallSittings = DataCreator.prepareHallSitting(10, 5, 5);
        for (HallSitting hallSitting : hallSittings) {
            hallSittingDao.save(hallSitting);
        }

    }

    @Test
    public void getCurrentSitTest() {
        for (HallSitting hallSitting : hallSittings) {
            String expt = hallSitting.getSit();
            Sits sits = SitUtil.convertClass(expt);
            String expts = JSONArray.fromObject(sits).toString();
            JSONArray jsonArray = JSONArray.fromObject(hallSittingService.getCurrentSit(hallSitting.getId()));
            String actual = jsonArray.toString();
            Assert.assertEquals(expts, actual);
        }
    }

    @Test
    public void addSitAndIsEmptyTest() {
        Sits sits = SitUtil.convertClass(hallSittings.get(0).getSit());
        List<OneSit> oneSits = new ArrayList<>();
        OneSit[] oneSits1 = sits.getSits();
        Random random = new Random();
        for (int i = 0; i < oneSits1.length; i++) {
            if (random.nextDouble() >0.5) {
                oneSits.add(oneSits1[i]);
            }
        }
        OneSit[] newonesits = new OneSit[oneSits.size()];
        for (int i = 0; i < oneSits.size(); i++) {
            newonesits[i] = oneSits.get(i);
        }
        String sitStr = JSONArray.fromObject(newonesits).toString();
        boolean ok = hallSittingService.addSitting(sitStr, 1);
        Assert.assertEquals(true, ok);
        Assert.assertEquals(true, hallSittingService.isSitEmpty(sitStr, 2));
        Assert.assertEquals(false, hallSittingService.isSitEmpty(sitStr, 1));
    }
}
