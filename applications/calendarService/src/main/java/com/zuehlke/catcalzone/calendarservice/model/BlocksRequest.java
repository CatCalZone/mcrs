package com.zuehlke.catcalzone.calendarservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Requests information about blocked periods in a set of calendars
 * 
 * @author mibo
 */
@XmlRootElement
@Getter @Setter @NoArgsConstructor
public class BlocksRequest {
	/**
	 * Beginning of the time for which we want results
	 */
	private LocalDateTime timeMin;	

	/**
	 * End of the time for which we want results
	 */
	private LocalDateTime timeMax;
	
	/**
	 * Timezone to be used for dates in request and response
	 */
	private String timeZone;
	
	/**
	 * Calendars for which to get data
	 */
	private List<Calendar> items;
	
	@XmlRootElement
	@Getter @Setter @NoArgsConstructor @ AllArgsConstructor
	public static class Calendar{
		String id;
	}
}
