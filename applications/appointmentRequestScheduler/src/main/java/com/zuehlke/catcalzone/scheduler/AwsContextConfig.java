package com.zuehlke.catcalzone.scheduler;

import com.amazonaws.services.sqs.AmazonSQS;
import com.zuehlke.catcalzone.scheduler.aws.AWSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kinggrass on 12.05.15.
 */
@Configuration
public class AwsContextConfig {

    @Autowired
    AWSFactory awsFactory;

    @Bean
    AmazonSQS createSQSService() {
        return awsFactory.createSQS();
    }

    /*
    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQS amazonSqs, ResourceIdResolver resourceIdResolver) {
        return new QueueMessagingTemplate(amazonSqs, resourceIdResolver);
    }
*/

}
