package com.zuehlke.catcalzone.calendarservice;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar.Freebusy;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

/**
 * Example for for autorization of access to Google calendar API,
 * taken from https://developers.google.com/google-apps/calendar/quickstart/java and expanded
 * 
 * @author mibo
 */
public class CalendarQuickstart {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Google Calendar API Java Quickstart";

    /** Directory to store user credentials. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/calendar-api-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart. */
    private static final List<String> SCOPES =
        Arrays.asList(CalendarScopes.CALENDAR_READONLY);
    
    private static final long ONE_MONTH = 30*24*60*60*1000L;
    private static final long TWO_MONTHS = 2*30*24*60*60*1000L;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            CalendarQuickstart.class.getResourceAsStream("/secrets/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
    
    public static Credential authorizeApp() throws IOException, GeneralSecurityException{
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
    public static com.google.api.services.calendar.Calendar
        getCalendarService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void main(String[] args) throws Exception {
        // Build a new authorized API client service.
        // Note: Do not confuse this class with the
        //   com.google.api.services.calendar.model.Calendar class.
        com.google.api.services.calendar.Calendar service =
            getCalendarService();

        FreeBusyRequest req = new FreeBusyRequest();
        req.setTimeMin(new DateTime(System.currentTimeMillis()+ONE_MONTH));
        req.setTimeMax(new DateTime(System.currentTimeMillis()+TWO_MONTHS));
        FreeBusyRequestItem it = new FreeBusyRequestItem();
        it.setId("nnblqc60ned749hnpg3naebleg@group.calendar.google.com");
        req.setItems(Arrays.asList(it));
        FreeBusyResponse res = service.freebusy().query(req).execute();
        
        for(Entry<String, FreeBusyCalendar> cal : res.getCalendars().entrySet()){
        	System.out.println(cal.getKey()+": ");
        	for(TimePeriod p : cal.getValue().getBusy()){
            	System.out.print("\t");
            	System.out.print(p.getStart());
            	System.out.print(" - ");
            	System.out.print(p.getEnd());
            	System.out.println();
        	}
        	System.out.println();
        }

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("nnblqc60ned749hnpg3naebleg@group.calendar.google.com")
            .setMaxResults(10)
            .setTimeMin(now)
            .setOrderBy("startTime")
            .setSingleEvents(true)
            .execute();
        List<Event> items = events.getItems();
        if (items.size() == 0) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
        
    }

}