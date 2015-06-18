package com.zuehlke.catcalzone.calendarservice;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

import lombok.extern.java.Log;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
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

    @Configuration
    public static class JerseyConfig extends ResourceConfig {
        public JerseyConfig(){        	
        	log.warning("Configuring Jersey");
        	this.register(MoxyJsonFeature.class);
        	this.register(MOXyJsonProvider.class);
        	this.register(CalendarService.class);
            packages("com.zuehlke.catcalzone.calendarservice;com.zuehlke.catcalzone.calendarservice.model");
        }
    }
}
