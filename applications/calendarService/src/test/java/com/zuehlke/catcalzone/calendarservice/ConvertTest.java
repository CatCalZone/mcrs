package com.zuehlke.catcalzone.calendarservice;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

import com.google.api.client.util.DateTime;

public class ConvertTest {

	@Test
	public void fromGoogle() {
		DateTime dt = new DateTime("2015-01-07T12:01:10+00:00");
		LocalDateTime ldt = CalendarService.fromGoogle(dt);
		assertEquals(2015, ldt.getYear());
		assertEquals(Month.JANUARY, ldt.getMonth());
		assertEquals(7, ldt.getDayOfMonth());
		assertEquals(12, ldt.getHour());
		assertEquals(1, ldt.getMinute());
		assertEquals(10, ldt.getSecond());
	}

	@Test
	public void toGoogle() {
		LocalDateTime ldt = LocalDateTime.of(2014, 12, 7, 11, 30, 17);
		DateTime dt = CalendarService.toGoogle(ldt, "Europe/Berlin");
		assertEquals("2014-12-07T11:30:17.000+01:00", dt.toStringRfc3339());
	}

}
