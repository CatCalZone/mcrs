package com.zuehlke.catcalzone.statusservice;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kinggrass on 22.05.15.
 */
@Service
@Log
public class AppointmentRequestQueueListener {

    @Autowired
    private  AppointmentRequestStore store;

    @RabbitListener(queues = "appointmentRequestQueue")
    public void processOrder(String data) {
        log.info("From queue" + data);
        store.storeRequest(data);
    }

}
