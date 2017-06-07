package com.five.hallSitting.controller;

import com.five.filmSession.service.FilmSessionService;
import com.five.hallSitting.model.Sits;
import com.five.hallSitting.service.HallSittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haoye on 17-6-6.
 */
@RestController
public class HallSittingController {

    @Autowired
    private HallSittingService hallSittingService;

    @Autowired
    private FilmSessionService filmSessionService;

    @RequestMapping(value = "/sit/{filmSessionId}", method = RequestMethod.GET)
    public Sits getCurrentSit(@PathVariable("filmSessionId") int filmSessionId) {
        int hallSittingId = filmSessionService.findById(filmSessionId).getHallSittingId();
//        int hallSittingId = 3;
        Sits sits = hallSittingService.getCurrentSit(hallSittingId);
        return sits;
    }

}
