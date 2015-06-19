package com.zuehlke.catcalzone.calendarservice;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

/**
 * Configure Jackson so that it can serialize Java 8 date objects properly
 * @author mibo
 */
@Provider
public class DateResolver implements ContextResolver<ObjectMapper> {  
    private final ObjectMapper MAPPER;

    public DateResolver() {
        MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JSR310Module());
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return MAPPER;
    }  
}