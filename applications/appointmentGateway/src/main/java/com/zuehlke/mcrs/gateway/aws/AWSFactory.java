package com.zuehlke.mcrs.gateway.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by kinggrass on 17.04.15.
 */
@Service
public class AWSFactory {

    @Value("${incomingAppointmentQueue:testQueue}")
    private String incomingSMSQueue;


    public AmazonSQS createSQS() {

        AWSCredentials credentials = null;

        try {

            credentials = new BasicAWSCredentials("x", "x");

        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }

        AmazonSQS sqs = new AmazonSQSClient(credentials);
        sqs.setEndpoint("http://localhost:9324");
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
       // sqs.setRegion(usWest2);
        sqs.createQueue("testQueue");
        return sqs;

    }
}
