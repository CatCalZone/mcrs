package com.zuehlke.mcrs.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuehlke.mcrs.gateway.model.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Named;

/**
 * Created by kinggrass on 17.04.15.
 */
@Service
public class AppointmentRequestService {

    @Autowired
    IncomingAppointmentRequestQueue incomingQueue;

    @Autowired
    ObjectMapper mapper;

    public AppointmentRequest createAppointmentRequest(AppointmentRequest request) {

        try {

            String message = mapper.writeValueAsString(request);
            incomingQueue.sendMessage(message);
            return request;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
