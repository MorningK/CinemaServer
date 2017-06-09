package com.five.cinemaRemark.dao;

import com.five.cinemaRemark.model.CinemaRemark;

import java.util.List;

/**
 * Created by msi on 2017/6/7.
 */
public interface CinemaRemarkDao {
    CinemaRemark save(CinemaRemark cinemaRemarkRemark);
    List<CinemaRemark> findByCinemaId(int cinemaId);
    List<CinemaRemark> findByUserId(int userId);
    void reload();
}
