package com.zuehlke.catcalzone.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by kinggrass on 21.05.15.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
public class AppointmentSchedulerApp {


    public static void main(String[] args) {
        new SpringApplicationBuilder(AppointmentSchedulerApp.class).web(true).run(args);
    }


    @Bean
    ObjectMapper createJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }

    @Bean(name = "incomingAppointmentQueue")
    public AppointmentRequestQueue initIncomingAppointmentRequestQueue(@Value("${incomingAppointmentQueue}") String name) {
        return new RabbitMQFacade(name);
    }

    @Bean(name = "slotFoundQueue")
    public AppointmentRequestQueue initSlotFoundQueue(@Value("${incomingAppointmentQueue}") String name) {
        return new RabbitMQFacade(name);
    }

    @Bean(name = "noSlotFoundQueue")
    public AppointmentRequestQueue initNoSlotFoundQueue(@Value("${incomingAppointmentQueue}") String name) {
        return new RabbitMQFacade(name);
    }

    @Value("${incomingAppointmentQueue}")
    private String queueName;

    @Value("${incomingAppointmentQueueHost}")
    private String queueHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(queueHost);
        return connectionFactory;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName+"-scheduler", false);
    }


    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(queueName + "-exchange");
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


}
