package com.five.globalSearch.controller;

import com.five.globalSearch.service.GlobalSearchService;
import com.five.user.model.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haoye on 17-6-21.
 */
@RestController
public class GlobalSearchController {

    @Autowired
    private GlobalSearchService globalSearchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public MyMessage search(String text) {
        return globalSearchService.search(text);
    }
}
