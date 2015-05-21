package com.zuehlke.catcalzone.scheduler;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by kinggrass on 21.05.15.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@Import(AwsContextConfig.class)
public class AppointmentSchedulerApp {


    public static void main(String[] args) {
        new SpringApplicationBuilder(AppointmentSchedulerApp.class).web(true).run(args);
    }
}
