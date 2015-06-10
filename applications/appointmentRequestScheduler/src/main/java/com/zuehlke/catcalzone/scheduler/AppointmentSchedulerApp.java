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

import javax.inject.Named;

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

    @Bean(name = "slotFoundQueueFacade")
    public AppointmentRequestQueue initSlotFoundQueue() {
        return new RabbitMQFacade(slotFoundQueue + exchangePostfix);
    }

    @Bean(name = "noSlotFoundQueueFacade")
    public AppointmentRequestQueue initNoSlotFoundQueue() {
        return new RabbitMQFacade(noSlotFoundQueue + exchangePostfix);
    }

    @Value("${incomingAppointmentQueue}")
    private String appointmentRequestQueue;

    @Value("${incomingAppointmentQueueHost}")
    private String queueHost;

    @Value("${slotFoundQueue}")
    private String slotFoundQueue;

    @Value("${noSlotFoundQueue}")
    private String noSlotFoundQueue;

    @Value("${queue_postfix}")
    private String queuePostfix;

    @Value("${exchange_postfix}")
    private String exchangePostfix;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(queueHost);
        return connectionFactory;
    }

     /* incomingAppointmentQueue  */

    @Bean(name = "incomingAppointmentQueue")
    Queue incomingAppointmentQueue() {
        return new Queue(appointmentRequestQueue + queuePostfix, false);
    }


    @Bean(name = "incomingAppointmentExchange")
    FanoutExchange incomingAppointmentExchange() {
        return new FanoutExchange(appointmentRequestQueue + exchangePostfix);
    }

    @Bean(name = "incomingAppointmentQueueBinding")
    Binding incomingAppointmentQueueBinding(@Named("incomingAppointmentQueue") Queue queue, @Named("incomingAppointmentExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    /* slotFoundQueue */

    @Bean(name = "slotFoundExchange")
    FanoutExchange slotFoundExchange() {
        return new FanoutExchange(slotFoundQueue + exchangePostfix);
    }


    /* noSlotFoundQueue */

    @Bean(name = "noSlotFoundExchange")
    FanoutExchange noSlotFoundExchange() {
        return new FanoutExchange(noSlotFoundQueue + exchangePostfix);
    }

    /* queue definitions end */

}
