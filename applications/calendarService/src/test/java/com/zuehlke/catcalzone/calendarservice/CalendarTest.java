package com.zuehlke.catcalzone.calendarservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.transport.local.LocalConduit;
import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.zuehlke.catcalzone.calendarservice.model.Block;
import com.zuehlke.catcalzone.calendarservice.model.BlocksRequest;
import com.zuehlke.catcalzone.calendarservice.model.CalendarBlocks;

public class CalendarTest extends Assert {

	private final static String ENDPOINT_ADDRESS = "local://books";
	private static Server server;

	@Provider
	public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {  
	    private final ObjectMapper MAPPER;

	    public ObjectMapperContextResolver() {
	        MAPPER = new ObjectMapper();
	        MAPPER.registerModule(new JSR310Module());
	        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	    }

	    @Override
	    public ObjectMapper getContext(Class<?> type) {
	        return MAPPER;
	    }  
	}
	@BeforeClass
	public static void initialize() throws Exception {
		startServer();
	}

	private static void startServer() throws Exception {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(CalendarService.class);

	    sf.setProviders(Arrays.asList(new JacksonJaxbJsonProvider(), new ObjectMapperContextResolver()));
	     
		sf.setResourceProvider(CalendarService.class, new SingletonResourceProvider(
				new CalendarService(), true));
		sf.setAddress(ENDPOINT_ADDRESS);

		server = sf.create();
	}

	@AfterClass
	public static void destroy() throws Exception {
		server.stop();
		server.destroy();
	}

	@Test
	public void testGetBlocks() {
		WebClient client = WebClient.create(ENDPOINT_ADDRESS, Arrays.asList(new JacksonJaxbJsonProvider(), new ObjectMapperContextResolver()));
		WebClient.getConfig(client).getRequestContext()
				.put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);
		client.accept(MediaType.APPLICATION_JSON);
		client.type(MediaType.APPLICATION_JSON);
		client.path("/calendar/blocks");
		
		BlocksRequest req = new BlocksRequest();
		req.setItems(Arrays.asList(new BlocksRequest.Calendar("x1"), new BlocksRequest.Calendar("x2")));
		
		Response result = client.post(req);
		assertNotNull(result);
		assertEquals(200, result.getStatus());
		List<CalendarBlocks> data = result.readEntity(new GenericType<ArrayList<CalendarBlocks>>(){});
		assertEquals(2, data.size());
		assertEquals("x1", data.get(0).getCalendarId());
		assertEquals("x2", data.get(1).getCalendarId());
	}

}