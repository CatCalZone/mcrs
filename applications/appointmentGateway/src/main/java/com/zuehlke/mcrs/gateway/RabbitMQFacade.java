package com.zuehlke.mcrs.gateway;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by kinggrass on 21.05.15.
 */
@Component
public class RabbitMQFacade implements IncomingAppointmentRequestQueue {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${incomingAppointmentQueue}")
    private String queueName;


    @Override
    public void sendMessage(String json) {
        rabbitTemplate.convertAndSend(queueName, json);
    }
}
