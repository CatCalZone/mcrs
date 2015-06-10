package com.zuehlke.catcalzone.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Named;

/**
 * Created by kinggrass on 08.06.15.
 */
@Service
@Log
public class AppointmentRequestProcessor {

    @Autowired
    private SlotFinder slotFinder;

    @Autowired
    @Named("slotFoundQueueFacade")
    private AppointmentRequestQueue slotFoundQueue;

    @Autowired
    @Named("noSlotFoundQueueFacade")
    private AppointmentRequestQueue noSlotFoundQueue;


    @Autowired
    ObjectMapper mapper;


    public void process(AppointmentRequest request) {

        try {
            log.info("try to find slot");
            slotFinder.findAndAppendSlot(request);
            request.setStatus("SLOT_FOUND");
            slotFoundQueue.sendMessage(map(request));
        } catch (NoSlotFoundException e) {
            log.info("no slot found");
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
