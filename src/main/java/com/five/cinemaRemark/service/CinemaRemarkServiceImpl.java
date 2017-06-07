package com.five.cinemaRemark.service;

import com.five.cinemaRemark.dao.CinemaRemarkDao;
import com.five.cinemaRemark.model.CinemaRemark;
import com.five.cinema.model.Cinema;
import com.five.cinema.service.CinemaService;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by msi on 2017/6/7.
 */
@Service
@Transactional
public class CinemaRemarkServiceImpl implements CinemaRemarkService {

    @Autowired
    private CinemaRemarkDao cinemaRemarkDao;

    @Autowired
    private UserService userService;

    @Autowired
    private CinemaService cinemaService;

    @Override
    public MyMessage postCinemaRemark(int userId, int cinemaId, String content) {
        User user = userService.findById(userId);
        if (user == null) return new MyMessage(0, "用户不存在");


        Cinema cinema = cinemaService.findById(cinemaId);
        if (cinema == null) return new MyMessage(0, "电影院不存在");

        CinemaRemark cinemaRemark = new CinemaRemark(userId, cinemaId, content);
        cinemaRemarkDao.save(cinemaRemark);
        return new MyMessage(0, "评论成功");
    }

    @Override
    public Object getCinemaRemarkByCinemaId(int cinemaId) {
        Cinema cinema = cinemaService.findById(cinemaId);
        if (cinema == null) return new MyMessage(0, "电影院不存在");
        CinemaRemark[] cinemaRemarks = cinemaRemarkDao.findByCinemaId(cinemaId);
        return new MyMessage( 1, cinemaRemarks);
    }

    @Override
    public Object getCinemaRemarkByUserId(int userId) {
        User user = userService.findById(userId);
        if (user == null) return new MyMessage(0, "用户不存在");

        CinemaRemark[] cinemaRemarks = cinemaRemarkDao.findByUserId(userId);
        return new MyMessage(1, cinemaRemarks);
    }
}
