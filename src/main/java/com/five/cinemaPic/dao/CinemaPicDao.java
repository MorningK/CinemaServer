package com.five.cinemaPic.dao;

import com.five.cinemaPic.model.CinemaPic;

import java.util.List;

/**
 * Created by msi on 2017/6/7.
 */
public interface CinemaPicDao {
    public List<CinemaPic> getPicByCinemaIdAndType(int cinemaId, int type);
//    public List<CinemaPic> getInsideByCinemaId(int cinemaId);
    void reload();
}
