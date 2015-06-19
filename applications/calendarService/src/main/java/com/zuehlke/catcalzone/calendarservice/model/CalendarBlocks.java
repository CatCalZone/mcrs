package com.zuehlke.catcalzone.calendarservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Information about blocked periods in a calendar
 * 
 * @author mibo
 */
@XmlRootElement
@Getter @Setter @NoArgsConstructor @ AllArgsConstructor
public class CalendarBlocks {
	/**
	 * ID of the calendar
	 */
	private String calendarId;
	
	/**
	 * Blocked periods
	 */
	private List<Block> blocks;
}
