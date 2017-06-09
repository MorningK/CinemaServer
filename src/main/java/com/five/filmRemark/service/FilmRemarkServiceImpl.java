package com.five.filmRemark.service;

import com.five.film.model.Film;
import com.five.film.service.FilmService;
import com.five.filmRemark.dao.FilmRemarkDao;
import com.five.filmRemark.model.FilmRemark;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import com.five.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by haoye on 17-6-6.
 */
@Service
@Transactional
public class FilmRemarkServiceImpl implements FilmRemarkService{

    @Autowired
    private FilmRemarkDao filmRemarkDao;

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @Override
    public MyMessage postFilmRemark(int userId, int filmId, String content) {
        User user = userService.findById(userId);
        if (user == null) return new MyMessage(0, "用户不存在");


        Film film = filmService.findById(filmId);
        if (film == null) return new MyMessage(0, "电影不存在");

        FilmRemark filmRemark = new FilmRemark(userId, filmId, content);
        filmRemarkDao.save(filmRemark);
        return new MyMessage(0, "评论成功");
    }

    @Override
    public Object getFilmRemarkByFilmId(int filmId) {

        Film film = filmService.findById(filmId);
        if (film == null) return new MyMessage(0, "电影不存在");
        List<FilmRemark> filmRemarks = filmRemarkDao.findByFilmId(filmId);
        if (filmRemarks == null||filmRemarks.size() == 0) return new MyMessage(0, "无评论");
        return new MyMessage(1,filmRemarks);
    }

    @Override
    public Object getFilmRemarkByUserId(int userId) {
        User user = userService.findById(userId);
        if (user == null) return new MyMessage(0, "用户不存在");

        List<FilmRemark> filmRemarks = filmRemarkDao.findByUserId(userId);
        if (filmRemarks == null||filmRemarks.size() == 0) return new MyMessage(0, "无评论");
        return new MyMessage(1,filmRemarks);
    }
}
