package com.zuehlke.catcalzone.calendarservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.transport.local.LocalConduit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.zuehlke.catcalzone.calendarservice.model.BlocksRequest;
import com.zuehlke.catcalzone.calendarservice.model.CalendarBlocks;

public class CalendarTest extends Assert {

	private final static String ENDPOINT_ADDRESS = "local://books";
	private static Server server;
	private WebClient client;

	@BeforeClass
	public static void initialize() throws Exception {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(CalendarController.class);
	    sf.setProviders(Arrays.asList(new JacksonJaxbJsonProvider(), new DateResolver()));
		sf.setResourceProvider(CalendarController.class, new SingletonResourceProvider(
				new CalendarController(), true));
		sf.setAddress(ENDPOINT_ADDRESS);
		server = sf.create();
	}

	@AfterClass
	public static void destroy() throws Exception {
		server.stop();
		server.destroy();
	}

	@Before
	public void setupClient(){
		client = WebClient.create(ENDPOINT_ADDRESS, Arrays.asList(new JacksonJaxbJsonProvider(), new DateResolver()));
		WebClient.getConfig(client).getRequestContext()
				.put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);
		client.accept(MediaType.APPLICATION_JSON);
		client.type(MediaType.APPLICATION_JSON);
	}
	
	@Test
	public void testTest() {
		client.path("/calendar/testBlocks");
		Response result = client.get();
		
		assertNotNull(result);
		assertEquals(200, result.getStatus());
		List<CalendarBlocks> data = result.readEntity(new GenericType<ArrayList<CalendarBlocks>>(){});
		assertEquals(1, data.size());
		assertEquals("test", data.get(0).getCalendarId());
		assertEquals(2, data.get(0).getBlocks().size());
	}

	@Test
	public void testGetBlocks() {
		client.path("/calendar/testBlocksPing");		
		BlocksRequest req = new BlocksRequest();
		req.setItems(Arrays.asList(new BlocksRequest.Calendar("xyzzy"), new BlocksRequest.Calendar("ultramarine")));		

		Response result = client.post(req);
		
		assertNotNull(result);
		assertEquals(200, result.getStatus());
		List<CalendarBlocks> data = result.readEntity(new GenericType<ArrayList<CalendarBlocks>>(){});
		assertEquals(2, data.size());
		assertEquals("xyzzy", data.get(0).getCalendarId());
		assertEquals("ultramarine", data.get(1).getCalendarId());
	}

}