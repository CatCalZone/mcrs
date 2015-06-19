package com.zuehlke.catcalzone.calendarservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.zuehlke.catcalzone.calendarservice.model.Block;
import com.zuehlke.catcalzone.calendarservice.model.BlocksRequest;
import com.zuehlke.catcalzone.calendarservice.model.CalendarBlocks;

/**
 * JAX-RS endpoint for access to Google Calendar API
 * 
 * @author mibo
 */
@Log
@RestController
@Path("/calendar")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Component
public class CalendarController {

    public CalendarController() {	
    	log.info("Starting CalendarService");
    }
    
    @Autowired
    private CalendarService service;

    /**
     * @param request time period and list of calendars
     * @return all blocked periods for the given calendars in the given time
     */
    @POST
    @Path("/blocks")
    public Response blocks(BlocksRequest request){
    	List<CalendarBlocks> data;
		try {
			data = service.getBlocksFor(request);
	    	GenericEntity<List<CalendarBlocks>> response = new GenericEntity<List<CalendarBlocks>>(data){};
	    	return Response.ok().entity(response).build();
		} catch (Exception e) {
			log.log(Level.SEVERE, "error calling service", e);
			return Response.serverError().entity("error calling service: " + e.getMessage()).build();
		}
    }

    /**
     * JAX-RS test method that always gives 2 blocked periods for every calendar in the request
     */
    @POST
    @Path("/testBlocksPing")
    public Response testPing(BlocksRequest request){
    	List<CalendarBlocks> result = request.getItems().stream()
			.map( cal -> new CalendarBlocks(cal.getId(), Arrays.asList(
        			new Block(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1)), 
        			new Block(LocalDateTime.now().plusDays(2).minusHours(2), LocalDateTime.now().plusDays(2)))
        		))
			.collect(Collectors.toList());
    	GenericEntity<List<CalendarBlocks>> response = new GenericEntity<List<CalendarBlocks>>(result){};
    	return Response.ok().entity(response).build();
    }

    /**
     * Test method that gives a constant response
     */
    @GET
    @Path("/testBlocks")
    public Response test(){
    	List<CalendarBlocks> result = new ArrayList<CalendarBlocks>();
    	result.add(new CalendarBlocks("test", Arrays.asList(
    			new Block(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1)), 
    			new Block(LocalDateTime.now().plusDays(2).minusHours(2), LocalDateTime.now().plusDays(2)))
    		));    		
    	GenericEntity<List<CalendarBlocks>> response = new GenericEntity<List<CalendarBlocks>>(result){};
    	return Response.ok().entity(response).build();
    }
}
