package com.zuehlke.catcalzone.statusservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kinggrass on 18.05.15.
 */
@RestController
@RequestMapping("/appointment")
public class StatusController {

    @RequestMapping(method = RequestMethod.GET)
    public String getStatus() {
        return "status of appointment request";
    }

}
