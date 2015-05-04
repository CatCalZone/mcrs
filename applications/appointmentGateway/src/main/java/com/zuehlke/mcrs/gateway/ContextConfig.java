package com.zuehlke.mcrs.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kinggrass on 17.04.15.
 */
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan
public class ContextConfig {

    public static final int RUNS = 20;


    /*@Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQS amazonSqs, ResourceIdResolver resourceIdResolver) {
        return new QueueMessagingTemplate(amazonSqs, resourceIdResolver);
    }

    @Autowired
    AWSFactory awsFactory;

    @Bean
    AmazonSQS createSQSService() {
        return awsFactory.createSQS();
    }
    */

    @Bean
    ObjectMapper createJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }


}
