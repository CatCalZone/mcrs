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

/**
 * Created by dea on 17.04.15.
 */
@Api(value = "appointmentRequest", description = "Takes new appointement requests ")
@RestController
@RequestMapping("/appointmentRequest")
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService service;

    @ApiOperation("a ping method to check if the controller is live")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String ping(HttpServletResponse response) {
        return "ping from appointment gateway";
    }

    @ApiOperation("Create a new appointment request")
    @RequestMapping(method = RequestMethod.POST)
    public AppointmentRequest createAppointmentRequest(@ApiParam(value = "the appointment request which should be created") @RequestBody @Valid final AppointmentRequest request) {
        return service.createAppointmentRequest(request);
    }


}
