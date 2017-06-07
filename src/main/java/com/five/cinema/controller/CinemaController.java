package com.five.cinema.controller;

import com.five.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by msi on 2017/6/6.
 */
@RestController
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @RequestMapping(value = "/cinema", method = RequestMethod.POST)
    public Object findCinema(int citycode, double longtitude, double latitude, HttpSession session) {
        int currentpage = 0;
        if (session.getAttribute("page") != null) currentpage = Integer.parseInt(session.getAttribute("page").toString());
        return cinemaService.getCinemas(citycode, longtitude, latitude, currentpage);
    }


}
