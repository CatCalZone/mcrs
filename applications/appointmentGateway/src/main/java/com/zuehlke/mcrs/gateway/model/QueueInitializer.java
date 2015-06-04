package com.zuehlke.mcrs.gateway.model;

import com.zuehlke.mcrs.gateway.IncomingAppointmentRequestQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by kinggrass on 03.06.15.
 */
@Component
public class QueueInitializer {

    @Autowired private IncomingAppointmentRequestQueue queue;

    @PostConstruct
    private void initQueue() {
        queue.sendMessage("ping");
    }

}
