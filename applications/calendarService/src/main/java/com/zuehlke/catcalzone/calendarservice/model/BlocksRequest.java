package com.zuehlke.catcalzone.calendarservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@Getter @Setter @NoArgsConstructor
public class BlocksRequest {
	private LocalDateTime timeMin;	
	private LocalDateTime timeMax;
	private String timeZone;
	private List<Calendar> items;
	
	@XmlRootElement
	@Getter @Setter @NoArgsConstructor @ AllArgsConstructor
	public static class Calendar{
		String id;
	}
}
