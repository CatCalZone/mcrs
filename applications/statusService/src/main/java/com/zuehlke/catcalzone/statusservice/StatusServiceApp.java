package com.zuehlke.catcalzone.statusservice;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

/**
 * Created by kinggrass on 18.05.15.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
@EnableScheduling
public class StatusServiceApp  {

    @Value("${incomingAppointmentQueueHost}")
    private String queueHost;

    @Value("${incomingAppointmentQueue}")
    private String appointmentRequestQueue;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(queueHost);


        return connectionFactory;
    }

    @Bean
    Queue queue() {
        return new Queue(appointmentRequestQueue, false);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(appointmentRequestQueue+"-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(appointmentRequestQueue);
    }


    public static void main(String[] args) {
        new SpringApplicationBuilder(StatusServiceApp.class).web(true).run(args);
    }

}
