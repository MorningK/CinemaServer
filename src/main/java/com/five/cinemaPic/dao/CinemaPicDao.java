package com.five.cinemaPic.dao;

import com.five.cinemaPic.model.CinemaPic;

import java.util.List;

/**
 * Created by msi on 2017/6/7.
 */
public interface CinemaPicDao {
    public List<CinemaPic> getCoverByCinemaId(int cinemaId);
    public List<CinemaPic> getInsideByCinemaId(int cinemaId);
}
