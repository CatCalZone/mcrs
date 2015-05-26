package com.zuehlke.mcrs.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.bus.Event;
import reactor.fn.Consumer;

import java.util.concurrent.CountDownLatch;

/**
 * Created by kinggrass on 08.04.15.
 */
@Service
@RefreshScope
public class MessageSender implements Consumer<Event<AppointmentRequest>> {


    private Logger log = Logger.getLogger(MessageSender.class);

    @Autowired
    private CountDownLatch latch;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private AppointmentGatewayService appointmentGatewayService;


    @Override
    public void accept(Event<AppointmentRequest> event) {

// todo hysterix

        AppointmentRequest data = event.getData();
        this.appointmentGatewayService.createAppointmentRequest(data);

        latch.countDown();


    }
}
