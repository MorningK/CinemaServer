package com.five.cinemaRemark.dao;

import com.five.cinemaRemark.model.CinemaRemark;

/**
 * Created by msi on 2017/6/7.
 */
public interface CinemaRemarkDao {
    void save(CinemaRemark cinemaRemarkRemark);
    CinemaRemark[] findByCinemaId(int cinemaId);
    CinemaRemark[] findByUserId(int userId);
    void reload();
}
