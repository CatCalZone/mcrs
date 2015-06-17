package com.zuehlke.catcalzone.calendarservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.ServiceMode;

import com.zuehlke.catcalzone.calendarservice.model.Block;
import com.zuehlke.catcalzone.calendarservice.model.BlocksRequest;
import com.zuehlke.catcalzone.calendarservice.model.CalendarBlocks;
import com.zuehlke.catcalzone.calendarservice.model.InvitationRequest;

@Stateless
@Path("/")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CalendarService {

    @POST
    @Path("blocks")
    public Response blocks(/*BlocksRequest request*/){
    	List<CalendarBlocks> result = new ArrayList<CalendarBlocks>();
    	result.add(new CalendarBlocks("cal1", Arrays.asList(
    			new Block(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1)), 
    			new Block(LocalDateTime.now().plusDays(2).minusHours(2), LocalDateTime.now().plusDays(2)))
    		));
    	GenericEntity<List<CalendarBlocks>> response = new GenericEntity<List<CalendarBlocks>>(result){};
    	return Response.ok().entity(response).build();
    }

    @POST
    @Path("invitation")
    public Response order(InvitationRequest request){
    	return null;
    }
}
