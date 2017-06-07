package com.five.cinemaRemark.controller;

import com.five.cinemaRemark.service.CinemaRemarkService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by msi on 2017/6/7.
 */
@RestController
@RequestMapping("/cinemaRemark")
public class CinemaRemarkController {
    @Autowired
    private CinemaRemarkService cinemaRemarkService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Object postCinemaRemark(HttpSession session, int cinemaId, String content) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return cinemaRemarkService.postCinemaRemark(userId, cinemaId, content);
    }

    @RequestMapping(value = "/getByCinemaId/{cinemaId}", method = RequestMethod.GET)
    public Object getCinemaRemarkByCinemaId(@PathVariable("cinemaId") int cinemaId) {
        return cinemaRemarkService.getCinemaRemarkByCinemaId(cinemaId);
    }

    @RequestMapping(value = "/getMyCinemaRemark", method = RequestMethod.GET)
    public Object getCinemaRemarkByUserId(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return cinemaRemarkService.getCinemaRemarkByUserId(userId);
    }
}
