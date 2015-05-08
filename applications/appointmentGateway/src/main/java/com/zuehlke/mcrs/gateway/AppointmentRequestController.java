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
@RequestMapping("/appointment")
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public void getDocumentationHtml (HttpServletResponse response) {

        try {
            response.sendRedirect("/appointment/specification/index.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/raml+yaml")
    public void getRamlSpecification (HttpServletResponse response) {

        try {
            response.sendRedirect("/appointment/specification/appointmentGateway.raml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @RequestMapping(method= RequestMethod.POST)
    public String createAppointmentRequest(@RequestBody final AppointmentRequest request) {
        return service.createAppointmentRequest(request);

    }


}
