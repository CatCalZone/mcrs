package com.zuehlke.mcrs.gateway;

import com.zuehlke.mcrs.gateway.model.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by dea on 17.04.15.
 */
@RestController
@RequestMapping("/appointmentRequest")
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String ping (HttpServletResponse response) {
        return "ping from appointment gateway";
    }

    @RequestMapping(method= RequestMethod.POST)
    public AppointmentRequest createAppointmentRequest(@RequestBody @Valid final AppointmentRequest request) {
        return service.createAppointmentRequest(request);
    }




}
