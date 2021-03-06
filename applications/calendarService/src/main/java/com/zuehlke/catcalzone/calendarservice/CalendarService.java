package com.zuehlke.catcalzone.calendarservice;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.mortbay.log.Log;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.FreeBusyCalendar;
import com.google.api.services.calendar.model.FreeBusyRequest;
import com.google.api.services.calendar.model.FreeBusyRequestItem;
import com.google.api.services.calendar.model.FreeBusyResponse;
import com.google.api.services.calendar.model.TimePeriod;
import com.zuehlke.catcalzone.calendarservice.model.Block;
import com.zuehlke.catcalzone.calendarservice.model.BlocksRequest;
import com.zuehlke.catcalzone.calendarservice.model.CalendarBlocks;


/**
 * Implementation of Google calendar API authorization and access
 * 
 * @author mibo
 */
@Service
public class CalendarService {
    private static final String APPLICATION_NAME = "CatCalZone";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR_READONLY);
        
    private static HttpTransport HTTP_TRANSPORT;
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Perform authorization using a service account, 
     * see https://developers.google.com/identity/protocols/OAuth2ServiceAccount
     */
    private static Credential authorizeApp() throws IOException, GeneralSecurityException{
    	String emailAddress = "401587662556-q8ehf0j5kmd9j79em0v5nr0phk3jb0qa@developer.gserviceaccount.com";
    	JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    	HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    	GoogleCredential credential = new GoogleCredential.Builder()
    	    .setTransport(httpTransport)
    	    .setJsonFactory(JSON_FACTORY)
    	    .setServiceAccountId(emailAddress)
    	    .setServiceAccountPrivateKeyFromP12File(new File("src/main/resources/secrets/service_account_secret.p12"))
    	    .setServiceAccountScopes(SCOPES)
    	    .build();
    	return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    private static com.google.api.services.calendar.Calendar getCalendarClient() throws IOException, GeneralSecurityException {
        Credential credential = authorizeApp();
        return new com.google.api.services.calendar.Calendar.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }

    /**
     * Convert dates
     * 
     * @param dt Java 8 date object
     * @param zone for dt
     * @return Google API date object
     */
    public static DateTime toGoogle(LocalDateTime dt, String zone){
    	return new DateTime(dt.atZone(ZoneId.of(zone)).toEpochSecond()*1000);
    }
    
    /**
     * Convert dates
     * 
     * @param dt Google API date object
     * @return Java 8 date object
     */
    public static LocalDateTime fromGoogle(DateTime dt){
    	LocalDateTime ldt = LocalDateTime.ofEpochSecond(dt.getValue()/1000, 0, ZoneOffset.ofTotalSeconds(dt.getTimeZoneShift()*60));
    	return ldt;
    }

    /**
     * Get and convert data to and from calendar API
     * 
     * @param originalRequest
     * @return calendar response converted to our format
     * @throws Exception
     */
    public List<CalendarBlocks> getBlocksFor(BlocksRequest originalRequest) throws Exception{
        Log.debug("calendars: " + originalRequest.getItems().size());
        
        com.google.api.services.calendar.Calendar client = getCalendarClient();
        FreeBusyRequest req = toGoogle(originalRequest);
        FreeBusyResponse res = client.freebusy().query(req).execute();        
        Log.debug("blocks: " + res.getCalendars().values().stream().map(cal -> cal.getBusy().size()).collect(Collectors.toList()));
        return fromGoogle(res);
    }

    /**
     * Convert response
     * @param res from Google
     * @return ours
     */
	private List<CalendarBlocks> fromGoogle(FreeBusyResponse res) {
		List<CalendarBlocks> data = new ArrayList<>();
        for(Entry<String, FreeBusyCalendar> entry : res.getCalendars().entrySet()){
        	CalendarBlocks cb = new CalendarBlocks();
        	cb.setCalendarId(entry.getKey());
        	cb.setBlocks(new ArrayList<>());
        	for(TimePeriod tp: entry.getValue().getBusy()){
        		cb.getBlocks().add(new Block(fromGoogle(tp.getStart()), fromGoogle(tp.getEnd())));
        	}
        	data.add(cb);
        }
		return data;
	}

	/**
	 * Convert request
	 * 
	 * @param originalRequest ours
	 * @return Google format
	 */
	private FreeBusyRequest toGoogle(BlocksRequest originalRequest) {
		FreeBusyRequest req = new FreeBusyRequest();
        req.setTimeMin(toGoogle(originalRequest.getTimeMin(), originalRequest.getTimeZone()));
        req.setTimeMax(toGoogle(originalRequest.getTimeMax(), originalRequest.getTimeZone()));        
        req.setTimeZone(originalRequest.getTimeZone());
        req.setItems(
        	originalRequest.getItems().stream()
        		.map(cal -> { FreeBusyRequestItem it = new FreeBusyRequestItem(); it.setId(cal.getId()); return it; })
        		.collect(Collectors.toList())
        );
		return req;
	}

}
