package com.zuehlke.mcrs.gateway;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zuehlke.mcrs.gateway.model.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Api(description = "Endpoint for appointment requests")
@RestController
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService service;

    @ApiOperation("ping to check if controller is live")
    @RequestMapping(value="/ping", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String ping(HttpServletResponse response) {
        return "ping sent from appointment gateway: " + new Date().toString();
    }

    @ApiOperation("create new appointment request")
    @RequestMapping(value = "/appointmentRequest", method = RequestMethod.POST)
    public AppointmentRequest createAppointmentRequest(@ApiParam(value = "the appointment request which should be created")
                                                           @RequestBody @Valid final AppointmentRequest request) {
        return service.createAppointmentRequest(request);
    }
}
