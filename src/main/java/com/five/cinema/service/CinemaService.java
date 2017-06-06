package com.five.cinema.service;

import com.five.cinema.model.Cinema;
import com.five.user.model.MyMessage;

/**
 * Created by msi on 2017/6/6.
 */
public interface CinemaService {
    MyMessage getCinemas(int citycode, double longtitude, double latitude);
    MyMessage getCinemas(int citycode, double longtitude, double latitude, int currentpage);
    Cinema findById(int id);
}
