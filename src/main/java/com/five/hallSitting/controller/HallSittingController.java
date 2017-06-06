package com.five.hallSitting.controller;

import com.five.hallSitting.model.Sits;
import com.five.hallSitting.service.HallSittingService;
import com.five.user.model.MyMessage;
import com.five.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haoye on 17-6-6.
 */
@RestController
public class HallSittingController {

    @Autowired
    private HallSittingService hallSittingService;
//    @Autowired
//    private FilmSessionService filmSessionService;

    @GetMapping("/sit")
    public Sits getCurrentSit(int filmSessionId) {
        // TODO
        //int hallSittingId = filmSessionService.....
        int hallSittingId = 3;
        Sits sits = hallSittingService.getCurrentSit(hallSittingId);
        return sits;
    }

}
