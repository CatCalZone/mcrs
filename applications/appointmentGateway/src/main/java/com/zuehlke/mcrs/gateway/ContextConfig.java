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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by kinggrass on 17.04.15.
 */
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan
public class ContextConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/specification/**")
                .addResourceLocations("classpath:spec/");
    }




    @Bean
    ObjectMapper createJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }


}
