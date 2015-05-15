package com.zuehlke.mcrs.gateway;

import com.zuehlke.mcrs.gateway.aws.SQSFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.MediaType.*;

/**
 * Created by dea on 17.04.15.
 */
@RestController
@RequestMapping("/")
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getDocumentationHtml (HttpServletResponse response) {
        return "hello from appointment gateway";
    }

    @RequestMapping(method= RequestMethod.POST)
    public String createAppointmentRequest(@RequestBody final AppointmentRequest request) {
        return service.createAppointmentRequest(request);

    }


}
