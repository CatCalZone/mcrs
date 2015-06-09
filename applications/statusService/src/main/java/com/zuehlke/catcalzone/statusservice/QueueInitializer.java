package com.zuehlke.catcalzone.statusservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by kinggrass on 03.06.15.
 */
@Component
public class QueueInitializer {

    @Autowired
    private List<AppointmentRequestQueue> queueList;

    @PostConstruct
    private void initQueue() {

        queueList.stream().forEach(
                queue ->
                        queue.sendMessage("ping")
        );
    }
}
