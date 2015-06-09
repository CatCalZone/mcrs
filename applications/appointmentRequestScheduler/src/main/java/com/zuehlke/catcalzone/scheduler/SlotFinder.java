package com.zuehlke.catcalzone.scheduler;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by kinggrass on 08.06.15.
 */
@Service
public class SlotFinder {

    public void findAndAppendSlot(AppointmentRequest request) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Slot s = new Slot();
        s.setEndDateTime(LocalDateTime.now());
        s.setStartDateTime(LocalDateTime.now());
        s.setRequestId(request.getRequestId());
        s.setNotVoted(request.getAttendees()); //TODO add requester itself
        request.setSlot(s);

    }

}
