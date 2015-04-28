package com.zuehlke.mcrs.gateway;

import com.zuehlke.mcrs.gateway.aws.SQSFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.*;

/**
 * Created by dea on 17.04.15.
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService service;

    @RequestMapping(method = RequestMethod.GET)
    public String ping() {
        AppointmentRequest request = new AppointmentRequest("ping","pong");

        return service.createAppointmentRequest(request);

    }


    @RequestMapping(method= RequestMethod.POST)
    public String createAppointmentRequest(@RequestBody final AppointmentRequest request) {
        return service.createAppointmentRequest(request);

    }


}
