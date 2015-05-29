package com.zuehlke.mcrs.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by kinggrass on 08.04.15.
 */
@Component
public class AppointmentCreator {


    public AppointmentRequest newMessage() {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setRequestUser("User" + UUID.randomUUID());
        appointmentRequest.setTitle("Title " + UUID.randomUUID());
        appointmentRequest.setMaxEndDate(LocalDate.now());
        appointmentRequest.setMinStartDate(LocalDate.now());
        appointmentRequest.setAttendees(Arrays.asList("User2","User3"));
        appointmentRequest.setDurationInHours(2);
        return appointmentRequest;
    }
}
