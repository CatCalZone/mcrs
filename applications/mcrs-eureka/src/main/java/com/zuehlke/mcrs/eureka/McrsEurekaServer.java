package com.zuehlke.mcrs.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableAutoConfiguration
@EnableEurekaServer
public class McrsEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(McrsEurekaServer.class, args);
    }

}

