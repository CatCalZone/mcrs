package com.zuehlke.mcrs.gateway;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuehlke.mcrs.gateway.aws.AWSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import reactor.Environment;
import reactor.bus.EventBus;

import java.util.concurrent.CountDownLatch;

/**
 * Created by kinggrass on 17.04.15.
 */
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan
public class ContextConfig {

    public static final int RUNS = 20;

    @Bean
    Environment env() {
        return new Environment();
    }

    @Bean
    CountDownLatch getLatch() {
        return new CountDownLatch(RUNS);
    }

    @Bean
    EventBus createReactor(Environment env) {
        return EventBus.config()
                .env(env)
                .dispatcher(Environment.THREAD_POOL)
                .get();
    }


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
