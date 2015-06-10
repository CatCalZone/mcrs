package com.zuehlke.catcalzone.scheduler;

import lombok.extern.java.Log;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by kinggrass on 08.06.15.
 */
@Service
@Log
public class SlotFinder {

    public void findAndAppendSlot(AppointmentRequest request) {

        try {
            log.info("waiting for external APIs (DUMMY)");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double random = Math.random() * 10;

        if (random > 3) {

            log.info("found a slot ;)");
            Slot s = new Slot();
            s.setEndDateTime(LocalDateTime.now());
            s.setStartDateTime(LocalDateTime.now());
            s.setRequestId(request.getRequestId());
            s.setNotVoted(request.getAttendees()); //TODO add requester itself
            request.setSlot(s);
        } else {
            throw new NoSlotFoundException();
        }

    }

}
