package com.five.globalSearch.service;

import com.five.globalSearch.dao.GlobalSearchDao;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haoye on 17-6-21.
 */
@Service
public class GlobalSearchServiceImpl implements GlobalSearchService {

    @Autowired
    private GlobalSearchDao globalSearchdDao;

    public MyMessage search(String text) {
        List list1 = globalSearchdDao.cinemaSearch(text);
        List list2 = globalSearchdDao.filmSearch(text);
        if (list1 == null || list1.isEmpty()) {
            if (list2 == null) {
                return new MyMessage(0, "查询得到0条信息");
            } else {
                return new MyMessage(1, list2);
            }
        } else {
            if (list2 != null) {
                for (int i = 0; i < list2.size(); i++) {
                    list1.add(list2.get(i));
                }

            }
            return new MyMessage(1, list1);
        }
    }

}
