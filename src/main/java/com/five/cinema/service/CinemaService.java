package com.five.cinema.service;

import com.five.cinema.model.Cinema;
import com.five.user.model.MyMessage;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by msi on 2017/6/6.
 */
public interface CinemaService {
    Object getCinemas(int citycode, double longtitude, double latitude, int currentpage);
    Cinema findById(int id);
    void reload();
    List<Integer> findIdByCitycode(int citycode);
}
