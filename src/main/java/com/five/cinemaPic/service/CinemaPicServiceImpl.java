package com.five.cinemaPic.service;

import com.five.cinema.model.Cinema;
import com.five.cinema.service.CinemaService;
import com.five.cinemaPic.dao.CinemaPicDao;
import com.five.cinemaPic.model.CinemaPic;
import com.five.user.model.MyMessage;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by msi on 2017/6/7.
 */
@Service
@Transactional
public class CinemaPicServiceImpl implements CinemaPicService {

    @Autowired
    private CinemaPicDao cinemaPicDao;

    @Autowired
    private CinemaService cinemaService;

    @Override
    public Object getCoverByCinemaId(int cinemaId) {
        Cinema cinema = cinemaService.findById(cinemaId);
        if (cinema == null) return new MyMessage(0, "电影院不存在");
        List<CinemaPic> cinemaPics = cinemaPicDao.getPicByCinemaIdAndType(cinemaId, CinemaPic.CP_COVER);
        if (cinemaPics == null) return new MyMessage(0,"找不到封面");
        if (cinemaPics.size() == 0) return new MyMessage(0,"找不到封面");
        else return new MyMessage(1, cinemaPics);
    }

    @Override
    public Object getInsideByCinemaId(int cinemaId) {
        Cinema cinema = cinemaService.findById(cinemaId);
        if (cinema == null) return new MyMessage(0, "电影院不存在");
        List<CinemaPic> cinemaPics = cinemaPicDao.getPicByCinemaIdAndType(cinemaId, CinemaPic.CP_INSIDE);
        if (cinemaPics == null) return new MyMessage(0,"找不到内部照片");
        if (cinemaPics.size() == 0) return new MyMessage(0,"找不到内部照片");
        else return new MyMessage(1, cinemaPics);
    }

    @Override
    public void reload() {
        cinemaPicDao.reload();
    }
}
