package com.zuehlke.mcrs.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by kinggrass on 08.04.15.
 */
@Component
public class AppointmentCreator {

    @Autowired
    private RandomMobileNumber randomMobileNumber;
    public AppointmentRequest newMessage() {
        return new AppointmentRequest(UUID.randomUUID().toString(),randomMobileNumber.randomNumber());
    }
}
