package com.zuehlke.catcalzone.calendarservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.transport.local.LocalConduit;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import com.zuehlke.catcalzone.calendarservice.model.Block;
import com.zuehlke.catcalzone.calendarservice.model.CalendarBlocks;

public class CalendarTest extends Assert {

	private final static String ENDPOINT_ADDRESS = "local://books";
	private static Server server;

	@BeforeClass
	public static void initialize() throws Exception {
		startServer();
	}

	private static void startServer() throws Exception {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(CalendarService.class);

	     sf.setProviders(Collections.singletonList(new MOXyJsonProvider()));
	     
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
		WebClient client = WebClient.create(ENDPOINT_ADDRESS, Collections.singletonList(new MOXyJsonProvider()));
		WebClient.getConfig(client).getRequestContext()
				.put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);

		client.accept(MediaType.APPLICATION_JSON);
		client.path("blocks");
		Response result = client.post(null);
		assertNotNull(result);
		assertEquals(200, result.getStatus());
		List<CalendarBlocks> data = result.readEntity(new GenericType<ArrayList<CalendarBlocks>>(){});
		assertEquals(1, data.size());
		assertEquals("cal1", data.get(0).getCalendarId());
	}

}