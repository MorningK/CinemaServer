package com.five.filmRemark.controller;

import com.five.filmRemark.service.FilmRemarkService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by haoye on 17-6-6.
 */
@RestController
@RequestMapping("/filmRemark")
public class filmRemarkController {

    @Autowired
    private FilmRemarkService filmRemarkService;

    @PostMapping("/post")
    public MyMessage postFilmRemark(HttpSession session, int filmId, String content) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return filmRemarkService.postFilmRemark(userId, filmId, content);
    }

    @GetMapping("/getByFilmId")
    public Object getFilmRemarkByFilmId(int filmId) {
        return filmRemarkService.getFilmRemarkByFilmId(filmId);
    }

    @GetMapping("/getMyFilmRemark")
    public Object getFilmRemarkByUserId(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return filmRemarkService.getFilmRemarkByUserId(userId);
    }

}
