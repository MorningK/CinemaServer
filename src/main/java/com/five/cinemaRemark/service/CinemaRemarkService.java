package com.five.cinemaRemark.service;

import com.five.user.model.MyMessage;

/**
 * Created by msi on 2017/6/7.
 */
public interface CinemaRemarkService {
    MyMessage postCinemaRemark(int userId, int cinemaId, String content);
    Object getCinemaRemarkByCinemaId(int cinemaId);
    Object getCinemaRemarkByUserId(int userId);
}
