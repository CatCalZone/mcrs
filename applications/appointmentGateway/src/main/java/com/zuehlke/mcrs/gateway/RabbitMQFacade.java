package com.zuehlke.mcrs.gateway;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by kinggrass on 21.05.15.
 */
@Component
@Log
@Profile({"docker","queue"})
public class RabbitMQFacade implements IncomingAppointmentRequestQueue {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${incomingAppointmentQueue}")
    private String queueName;

    @Value("${sendToQueue:true}")
    private boolean sendToQueue;


    @Override
    public void sendMessage(String json) {
        if (sendToQueue) {
            rabbitTemplate.setExchange(queueName + "-exchange");
            rabbitTemplate.convertAndSend(json);
        } else {
            log.info("Not send to queue: " +json);
        }
    }
}
