package com.zuehlke.catcalzone.statusservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by kinggrass on 18.05.15.
 */
@RestController
@RequestMapping("/appointmentRequest")
public class StatusController {

    @Autowired
    private AppointmentRequestStore requestStore;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<AppointmentRequest> getStatus() {
        return requestStore.getRequests();
    }



}
