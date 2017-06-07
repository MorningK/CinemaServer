package com.five.cinemaPic.controller;

import com.five.cinemaPic.service.CinemaPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by msi on 2017/6/7.
 */
@RestController
@RequestMapping("/cinemaPic")
public class CinemaPicController {

    @Autowired
    private CinemaPicService cinemaPicService;

    @RequestMapping(value = "/cover/{cinemaId}", method = RequestMethod.GET)
    public Object getCoverByCinemaId(@PathVariable("cinemaId") int cinemaId) {
        return cinemaPicService.getCoverByCinemaId(cinemaId);
    }

    @RequestMapping(value = "/inside/{cinemaId}", method = RequestMethod.GET)
    public Object getInsideByCinemaId(@PathVariable("cinemaId") int cinemaId) {
        return cinemaPicService.getInsideByCinemaId(cinemaId);
    }
}
