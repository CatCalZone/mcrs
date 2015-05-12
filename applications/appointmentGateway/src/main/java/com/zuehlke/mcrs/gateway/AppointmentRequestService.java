package com.zuehlke.mcrs.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuehlke.mcrs.gateway.aws.SQSFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by kinggrass on 17.04.15.
 */
@Service
public class AppointmentRequestService {

    @Autowired
    SQSFacade sqs;

    @Autowired
    ObjectMapper mapper;

    public String createAppointmentRequest(AppointmentRequest request) {

        try {
            String message = mapper.writeValueAsString(request);
            sqs.sendMessage(message);
            return request.getId();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
