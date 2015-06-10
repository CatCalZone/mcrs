package com.zuehlke.catcalzone.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by kinggrass on 22.05.15.
 */
@Service
@Log
public class AppointmentRequestQueueListener {

    @Autowired
    private AppointmentRequestProcessor requestProcessor;

    @Autowired
    ObjectMapper mapper;

    @RabbitListener(queues = "appointmentRequestQueue-schedulerservice")
    public void processMessage(String data) {
        log.info("From queue" + data);
        try {
            AppointmentRequest appointmentRequest = mapper.readValue(data, AppointmentRequest.class);
            requestProcessor.process(appointmentRequest);
        } catch (IOException e) {
            log.info("Could not read message " + data + "! Ingoring it!");
        }
    }

}
