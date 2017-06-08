package com.five.cinemaPic.service;

import com.five.user.model.MyMessage;

/**
 * Created by msi on 2017/6/7.
 */
public interface CinemaPicService {
    public Object getCoverByCinemaId(int cinemaId);
    public Object getInsideByCinemaId(int cinemaId);
    void reload();
}
