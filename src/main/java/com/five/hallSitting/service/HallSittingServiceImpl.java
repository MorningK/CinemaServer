package com.five.hallSitting.service;

import com.five.hallSitting.dao.HallSittingDao;
import com.five.hallSitting.model.HallSitting;
import com.five.hallSitting.model.OneSit;
import com.five.hallSitting.model.Sits;
import com.five.hallSitting.utils.SitUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by haoye on 17-6-6.
 */
@Service
@Transactional
public class HallSittingServiceImpl implements HallSittingService {

    @Autowired
    private HallSittingDao hallSittingDao;

    public Sits getCurrentSit(int hallSittingId) {
        HallSitting hallSitting = hallSittingDao.findById(hallSittingId);
        System.out.println(hallSittingId);
        if (hallSitting == null) {
            System.out.println("null");
            return null;
        }
//        HallSitting hallSitting = new HallSitting();
//        String sits = "[\n" +
//                "    {\n" +
//                "        \"x\": 1,\n" +
//                "        \"y\": 2,\n" +
//                "        \"valid\": true\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"x\": 1,\n" +
//                "        \"y\": 2,\n" +
//                "        \"valid\": true\n" +
//                "    }\n" +
//                "]";
//        String sits2 = "[{\"x\": 1,\"y\": 2,\"valid\": true}, {\"x\": 1,\"y\": 3,\"valid\": true}{\"x\": 1,\"y\": 1,\"valid\": false}]";
//        hallSitting.setSit(sits);

//        insert into hall_sitting(sit) values("[{\"x\": 1,\"y\": 2,\"valid\": true}, {\"x\": 1,\"y\": 3,\"valid\": true},{\"x\": 1,\"y\": 1,\"valid\": false}]")

        String sitStr = hallSitting.getSit();
        return SitUtil.convertClass(sitStr);
    }

    @Override
    public boolean isSitEmpty(String sitsStr, int hallSittingId) {
        Sits sits = getCurrentSit(hallSittingId);
        Sits newSits = SitUtil.convertClass(sitsStr);
        for (OneSit newOneSit : newSits.getSits()) {

            for (OneSit oneSit : sits.getSits()) {
                if (oneSit.getX() ==  newOneSit.getX() && oneSit.getY() == newOneSit.getY()) {
                    if (oneSit.isValid()) {
                        break;
                    }
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public boolean addSitting(String sitStr, int hallSittingId) {
        Sits newSits = SitUtil.convertClass(sitStr);
        int count = 0;
        int okLength = newSits.getSits().length;

        HallSitting hallSitting = hallSittingDao.findById(hallSittingId);
        String currentSitStr = hallSitting.getSit();
        OneSit[] currentSits = SitUtil.convertClass(currentSitStr).getSits();
        for (OneSit newSit : newSits.getSits()) {
            for (int i = 0; i < currentSits.length; i++) {
                if (newSit.getX() == currentSits[i].getX() && newSit.getY() == currentSits[i].getY()) {
                    currentSits[i].setValid(false);
                    count++;
                    break;
                }
            }
        }
        JSONArray array=JSONArray.fromObject(currentSits);
        hallSitting.setSit(array.toString());
        hallSittingDao.save(hallSitting);

        return count == okLength;
    }
}
