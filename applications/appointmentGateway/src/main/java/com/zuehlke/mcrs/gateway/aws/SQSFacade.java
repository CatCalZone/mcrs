package com.zuehlke.mcrs.gateway.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by kinggrass on 17.04.15.
 */
@Service
public class SQSFacade {


   // @Autowired
    AmazonSQS sqs;

    @Value("${sendToSqs : false}")
    private boolean sendToSqs;

    @Value("${incomingAppointmentQueue:nil}")
    private String incomingSMSQueue;


    public void sendMessage(String json) {
        if (sendToSqs) {
            sqs.sendMessage(new SendMessageRequest(incomingSMSQueue, json));
        } else {
            System.out.println(json);
        }

    }
}
