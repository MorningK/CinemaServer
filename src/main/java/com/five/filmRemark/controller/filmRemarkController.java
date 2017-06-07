package com.five.filmRemark.controller;

import com.five.filmRemark.service.FilmRemarkService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public MyMessage postFilmRemark(HttpSession session, int filmId, String content) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return filmRemarkService.postFilmRemark(userId, filmId, content);
    }

    @RequestMapping(value = "/getByFilmId/{filmId}", method = RequestMethod.GET)
    public Object getFilmRemarkByFilmId(@PathVariable("filmId") int filmId) {
        return filmRemarkService.getFilmRemarkByFilmId(filmId);
    }

    @RequestMapping(value = "/getMyFilmRemark", method = RequestMethod.GET)
    public Object getFilmRemarkByUserId(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return filmRemarkService.getFilmRemarkByUserId(userId);
    }

}
