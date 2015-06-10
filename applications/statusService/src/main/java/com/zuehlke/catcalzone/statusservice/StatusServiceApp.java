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

import javax.inject.Named;
import java.io.IOException;

/**
 * Created by kinggrass on 18.05.15.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
@EnableScheduling
public class StatusServiceApp {

    @Value("${incomingAppointmentQueueHost}")
    private String queueHost;

    @Value("${incomingAppointmentQueue}")
    private String appointmentRequestQueue;

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

    @Bean(name = "slotFoundQueue")
    Queue slotFoundQueue() {
        return new Queue(slotFoundQueue + queuePostfix, false);
    }

    @Bean(name = "slotFoundExchange")
    FanoutExchange slotFoundExchange() {
        return new FanoutExchange(slotFoundQueue + exchangePostfix);
    }

    @Bean(name = "slotFoundQueueBinding")
    Binding slotFoundBinding(@Named("slotFoundQueue") Queue queue, @Named("slotFoundExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


    /* noSlotFoundQueue */

    @Bean(name = "noSlotFoundQueue")
    Queue noSlotFoundQueue() {
        return new Queue(noSlotFoundQueue + queuePostfix, false);
    }

    @Bean(name = "noSlotFoundExchange")
    FanoutExchange noSlotFoundExchange() {
        return new FanoutExchange(noSlotFoundQueue + exchangePostfix);
    }

    @Bean(name = "noSlotFoundQueueBinding")
    Binding noSlotFoundQueueBinding(@Named("noSlotFoundQueue") Queue queue, @Named("noSlotFoundExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    /* queue definitions end */


    public static void main(String[] args) {
        new SpringApplicationBuilder(StatusServiceApp.class).web(true).run(args);
    }

}
