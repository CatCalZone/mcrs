package com.zuehlke.catcalzone.calendarservice;

import javax.inject.Named;

import lombok.extern.java.Log;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration for Spring Boot and Jersey (JAX-RS)
 * 
 * @author mibo
 */
@SpringBootApplication
@Log
public class CalendarApp {
    public static void main(String[] args) {
    	log.info("Starting CalendarApp");
        new SpringApplicationBuilder(CalendarApp.class).web(true).run(args);
    }

    @Named
    @Configuration
    public static class JerseyConfig extends ResourceConfig {
        public JerseyConfig(){        	
        	log.info("Configuring Jersey");
        	this.register(CalendarController.class);
        	this.register(DateResolver.class);
        }
    }
}
