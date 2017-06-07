package com.five.cinemaRemark.controller;

import com.five.cinemaRemark.service.CinemaRemarkService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/post")
    public Object postCinemaRemark(HttpSession session, int cinemaId, String content) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return cinemaRemarkService.postCinemaRemark(userId, cinemaId, content);
    }

    @GetMapping("/getByCinemaId")
    public Object getCinemaRemarkByCinemaId(int cinemaId) {
        return cinemaRemarkService.getCinemaRemarkByCinemaId(cinemaId);
    }

    @GetMapping("/getMyCinemaRemark")
    public Object getCinemaRemarkByUserId(HttpSession session) {
        Object userIdObj = session.getAttribute("userId");
        if (userIdObj == null) {
            return new MyMessage(0, "请登录");
        }
        int userId = (int)userIdObj;
        return cinemaRemarkService.getCinemaRemarkByUserId(userId);
    }
}
