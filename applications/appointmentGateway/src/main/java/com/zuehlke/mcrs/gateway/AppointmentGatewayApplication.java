package com.zuehlke.mcrs.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ContextConfig.class,AwsContextConfig.class})
public class AppointmentGatewayApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(AppointmentGatewayApplication.class, args);
    }

}