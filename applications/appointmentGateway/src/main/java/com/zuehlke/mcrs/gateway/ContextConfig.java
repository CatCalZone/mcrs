package com.zuehlke.mcrs.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by kinggrass on 17.04.15.
 */
@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan
public class ContextConfig extends WebMvcConfigurerAdapter {

    @Bean
    ObjectMapper createJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }

    @Value("${incomingAppointmentQueue}")
    private String queueName;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(queueName+"-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }



}
