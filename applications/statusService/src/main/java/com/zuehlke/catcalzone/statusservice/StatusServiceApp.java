package com.zuehlke.catcalzone.statusservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * Created by kinggrass on 18.05.15.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
public class StatusServiceApp {

    @Value("${incomingAppointmentQueueHost}")
    private String queueHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(queueHost);
        return connectionFactory;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(StatusServiceApp.class).web(true).run(args);
    }

}
