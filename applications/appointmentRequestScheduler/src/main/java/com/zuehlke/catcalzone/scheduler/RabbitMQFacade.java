package com.zuehlke.catcalzone.scheduler;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by kinggrass on 21.05.15.
 */
@Log
@Profile({"docker","queue"})
public class RabbitMQFacade implements AppointmentRequestQueue {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private String queueName;

    @Value("${sendToQueue:true}")
    private boolean sendToQueue;

    public RabbitMQFacade(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public void sendMessage(String json) {
        if (sendToQueue) {
            rabbitTemplate.setExchange(queueName);
            rabbitTemplate.convertAndSend(json);
        } else {
            log.info("Not send to queue: " +json);
        }
    }
}
