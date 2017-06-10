package com.five.cinema.dao;

import com.five.cinema.model.Cinema;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface CinemaDao {
    List<Cinema> findByLocation(int citycode);
    Cinema save(Cinema cinema);
    Cinema findById(int id);
    void reload();
    List<Integer> findIdByCitycode(int citycode);
}
