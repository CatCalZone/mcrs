package com.zuehlke.mcrs.gateway;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by kinggrass on 26.05.15.
 */
@Service
@Log
@Profile("mock")
public class IncomingAppointmentRequetQueueMock implements IncomingAppointmentRequestQueue {

    @Override
    public void sendMessage(String json) {
        log.info("Mocked appointment queue: " + json);
    }
}
