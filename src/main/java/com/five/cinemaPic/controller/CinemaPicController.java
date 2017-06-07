package com.five.cinemaPic.controller;

import com.five.cinemaPic.service.CinemaPicService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by msi on 2017/6/7.
 */
@RestController
@RequestMapping("/cinemaPic")
public class CinemaPicController {

    @Autowired
    private CinemaPicService cinemaPicService;

    @GetMapping("/cover")
    public Object getCoverByCinemaId(int cinemaId) {
        return cinemaPicService.getCoverByCinemaId(cinemaId);
    }

    @GetMapping("/inside")
    public Object getInsideByCinemaId(int cinemaId) {
        return cinemaPicService.getInsideByCinemaId(cinemaId);
    }
}
