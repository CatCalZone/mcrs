package com.zuehlke.calcatzone.edgeservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by kinggrass on 13.05.15.
 */
@SpringBootApplication
@EnableZuulProxy
public class EdgeServiceApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EdgeServiceApp.class).web(true).run(args);
    }

}
