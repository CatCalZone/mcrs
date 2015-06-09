package com.zuehlke.catcalzone.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Named;
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
