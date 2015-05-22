package com.zuehlke.catcalzone.scheduler.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.zuehlke.catcalzone.scheduler.IncomingAppointmentRequestQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kinggrass on 17.04.15.
 */
@Service
public class SQSFacade implements IncomingAppointmentRequestQueue {


    @Autowired
    AmazonSQS sqs;

    @Value("${incomingAppointmentQueue}")
    private String incomingSMSQueue;

    @Scheduled(fixedDelay = 1000)
    public void test() {
        System.err.println("Test---------");
    }

    @Override
    public void recieveMessages() {
        String queueUrl = sqs.getQueueUrl(incomingSMSQueue).getQueueUrl();
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        messages.forEach(it -> System.err.println(it.toString()));
    }
}
