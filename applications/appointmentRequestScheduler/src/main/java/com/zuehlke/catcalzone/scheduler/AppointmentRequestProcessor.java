package com.zuehlke.catcalzone.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.name.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kinggrass on 08.06.15.
 */
@Service
public class AppointmentRequestProcessor {

    @Autowired
    private SlotFinder slotFinder;

    @Autowired
    @Named("slotFoundQueue")
    private AppointmentRequestQueue slotFoundQueue;


    @Autowired
    ObjectMapper mapper;


    public void process(AppointmentRequest request) {

        try {
            slotFinder.findAndAppendSlot(request);
            request.setStatus("SLOT_FOUND");
            slotFoundQueue.sendMessage(map(request));
        } catch (NoSlotFoundException e) {
            request.setStatus("NO_SLOT_FOUND");
            slotFoundQueue.sendMessage(map(request));
        }

    }

    private String map(AppointmentRequest request) {
        try {
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
