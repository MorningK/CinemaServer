package com.five.globalSearch.dao;

import java.util.List;

/**
 * Created by haoye on 17-6-21.
 */
public interface GlobalSearchDao {
    public List cinemaSearch(String text);
    public List filmSearch(String text);
}
