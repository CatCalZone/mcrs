package com.zuehlke.catcalzone.statusservice;

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
public class SlotFoundQueueListener {

    @Autowired
    private  AppointmentRequestStore store;

    @Autowired
    ObjectMapper mapper;

    @RabbitListener(queues = "slotFoundQueue-statusservice")
    public void processMessage(String data) {
        log.info("From queue" + data);
        try {
            store.storeRequest(mapper.readValue(data, AppointmentRequest.class));
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Could not read message " + data + "! Ingoring it!");
        }
    }

}
