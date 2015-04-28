package com.zuehlke.mcrs.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.*;
import reactor.bus.EventBus;

@Configuration
@Import(ContextConfig.class)
public class AppointmentGatewayApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AppointmentGatewayApplication.class, args);
    }

}