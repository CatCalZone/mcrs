package com.zuehlke.mcrs.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ContextConfig.class})
public class AppointmentGatewayApplication {

    public static void main(String[] args) throws InterruptedException {
        new SpringApplicationBuilder(AppointmentGatewayApplication.class).web(true).run(args);
    }

}