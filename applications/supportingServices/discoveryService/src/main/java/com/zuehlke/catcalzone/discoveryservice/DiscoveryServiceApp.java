package com.zuehlke.catcalzone.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableAutoConfiguration
@EnableEurekaServer
public class DiscoveryServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServiceApp.class, args);
    }

}

