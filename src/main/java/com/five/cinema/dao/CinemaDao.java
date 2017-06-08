package com.five.cinema.dao;

import com.five.cinema.model.Cinema;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface CinemaDao {
    List<Cinema> findByLocation(int citycode, double longtitude, double latitude, int currentpage);
    void save(Cinema cinema);
    Cinema findById(int id);
    void reload();
}
