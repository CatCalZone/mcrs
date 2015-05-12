package com.zuehlke.mcrs.gateway;

import com.amazonaws.services.sqs.AmazonSQS;
import com.zuehlke.mcrs.gateway.aws.AWSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by kinggrass on 12.05.15.
 */
@Configuration
@PropertySource("classpath:testApplication.properties")
public class TestContextConfig {

    @Autowired
    AWSFactory awsFactory;

    @Bean
    AmazonSQS createSQSService() {
        return null; //TODO tmp
    }


}
