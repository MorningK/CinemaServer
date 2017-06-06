package com.five.cinemaRemark.dao;

import com.five.cinemaRemark.model.CinemaRemark;
import com.five.cinemaRemark.repository.CinemaRemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by msi on 2017/6/7.
 */
@Repository
public class CinemaRemarkDaoImpl implements CinemaRemarkDao {

    @Autowired
    private CinemaRemarkRepository cinemaRemarkRepository;

    @Override
    public void save(CinemaRemark cinemaRemark) {
        cinemaRemarkRepository.save(cinemaRemark);
    }

    @Override
    public CinemaRemark[] findByCinemaId(int cinemaId) {
        return cinemaRemarkRepository.findByCinemaId(cinemaId);
    }

    @Override
    public CinemaRemark[] findByUserId(int userId) {
        return cinemaRemarkRepository.findByUserId(userId);
    }
}
