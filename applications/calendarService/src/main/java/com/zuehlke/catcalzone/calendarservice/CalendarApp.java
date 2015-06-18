package com.zuehlke.catcalzone.calendarservice;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.java.Log;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Log
public class CalendarApp {
    public static void main(String[] args) {
    	log.warning("Starting CalendarApp");
        new SpringApplicationBuilder(CalendarApp.class).web(true).run(args);
    }

    @Named
    @Configuration
    public static class JerseyConfig extends ResourceConfig {
        public JerseyConfig(){        	
        	log.warning("Configuring Jersey");
//        	this.register(MoxyJsonFeature.class);
//        	this.register(MOXyJsonProvider.class);
        	this.register(CalendarService.class);
        	this.register(GreetingEndpoint.class);
            packages("com.zuehlke.catcalzone.calendarservice;com.zuehlke.catcalzone.calendarservice.model");
        }
    }
    
    @Named
    @Path("/hello")
    @Produces({MediaType.APPLICATION_JSON})
    public static class GreetingEndpoint {

        @GET
        @Path("/test")
        public String get() {
            return "Hello World!";
        }
    }
}
