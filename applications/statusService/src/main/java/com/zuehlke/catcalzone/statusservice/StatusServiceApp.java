package com.zuehlke.catcalzone.statusservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by kinggrass on 18.05.15.
 */
@SpringBootApplication
@EnableEurekaClient
public class StatusServiceApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StatusServiceApp.class).web(true).run(args);
    }

}
