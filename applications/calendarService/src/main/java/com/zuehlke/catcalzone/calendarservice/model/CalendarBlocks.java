package com.zuehlke.catcalzone.calendarservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@Getter @Setter @NoArgsConstructor @ AllArgsConstructor
public class CalendarBlocks {
	private String calendarId;
	private List<Block> blocks;
}
