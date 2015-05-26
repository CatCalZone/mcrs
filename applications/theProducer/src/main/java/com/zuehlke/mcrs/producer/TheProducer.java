package com.zuehlke.mcrs.producer;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import reactor.Environment;
import reactor.bus.EventBus;

import javax.inject.Named;
import java.util.concurrent.CountDownLatch;

import static reactor.bus.selector.Selectors.$;

@Configuration
@EnableAutoConfiguration
@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan
public class TheProducer implements CommandLineRunner {

    public static final int RUNS = 20000;

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



    public String internalAppointmentQueue = "appointmentQueue";

    @Bean(name = "internalAppointmentQueue")
    public String getInternalAppointmentQueue() {
        return internalAppointmentQueue;
    }

    @Autowired
    private EventBus eventBus;

    @Autowired
    private MessageProducer messageCreator;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    @Named("internalAppointmentQueue")
    private String appointmentQueue;

    @Override
    public void run(String... args) throws Exception {

        eventBus.on($(appointmentQueue), messageSender);
        messageCreator.createMessages();

    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(TheProducer.class, args);
    }

}