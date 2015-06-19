package com.zuehlke.catcalzone.calendarservice.model;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a period in a calendar that is blocked by an appointment
 * 
 * @author mibo
 */
@XmlRootElement
@Getter @Setter @NoArgsConstructor @ AllArgsConstructor
public class Block {
    private LocalDateTime start;
    private LocalDateTime end;
}
