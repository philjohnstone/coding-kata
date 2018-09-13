package com.example;

import org.junit.Assert;
import org.junit.Test;

public class BerlinClockTest {

	@Test
	public void testSingleMinutes() {
		Assert.assertEquals("OOOO", new BerlinClock().convertSingleMinutes("00:00:00"));
		Assert.assertEquals("YYYY", new BerlinClock().convertSingleMinutes("23:59:59"));
		Assert.assertEquals("YYOO", new BerlinClock().convertSingleMinutes("12:32:00"));
		Assert.assertEquals("YYYY", new BerlinClock().convertSingleMinutes("12:34:00"));
		Assert.assertEquals("OOOO", new BerlinClock().convertSingleMinutes("12:35:00"));
	}
	
	@Test
	public void testFiveMinuteRow() {
		Assert.assertEquals("OOOOOOOOOOO", new BerlinClock().convertFiveMinutes("00:00:00"));
		Assert.assertEquals("YYRYYRYYRYY", new BerlinClock().convertFiveMinutes("23:59:59"));
		Assert.assertEquals("OOOOOOOOOOO", new BerlinClock().convertFiveMinutes("12:04:00"));
		Assert.assertEquals("YYRYOOOOOOO", new BerlinClock().convertFiveMinutes("12:23:00"));
		Assert.assertEquals("YYRYYRYOOOO", new BerlinClock().convertFiveMinutes("12:35:00"));
	}
	
	@Test
	public void testSingleHoursRow() {
		Assert.assertEquals("OOOO", new BerlinClock().convertSingleHours("00:00:00"));
		Assert.assertEquals("RRRO", new BerlinClock().convertSingleHours("23:59:59"));
		Assert.assertEquals("RROO", new BerlinClock().convertSingleHours("02:04:00"));
		Assert.assertEquals("RRRO", new BerlinClock().convertSingleHours("08:23:00"));
		Assert.assertEquals("RRRR", new BerlinClock().convertSingleHours("14:35:00"));
	}
	
	@Test
	public void testFiveHoursRow() {
		Assert.assertEquals("OOOO", new BerlinClock().convertFiveHours("00:00:00"));
		Assert.assertEquals("RRRR", new BerlinClock().convertFiveHours("23:59:59"));
		Assert.assertEquals("OOOO", new BerlinClock().convertFiveHours("02:04:00"));
		Assert.assertEquals("ROOO", new BerlinClock().convertFiveHours("08:23:00"));
		Assert.assertEquals("RRRO", new BerlinClock().convertFiveHours("16:35:00"));
	}
	
	@Test
	public void testSecondsLamp() {
		Assert.assertEquals("Y", new BerlinClock().convertSeconds("00:00:00"));
		Assert.assertEquals("O", new BerlinClock().convertSeconds("23:59:59"));
	}
	
	@Test
	public void testWholeClock() {
		Assert.assertEquals("YOOOOOOOOOOOOOOOOOOOOOOO", new BerlinClock().convertTime("00:00:00"));
		Assert.assertEquals("ORRRRRRROYYRYYRYYRYYYYYY", new BerlinClock().convertTime("23:59:59"));
		Assert.assertEquals("YRRROROOOYYRYYRYYRYOOOOO", new BerlinClock().convertTime("16:50:06"));
		Assert.assertEquals("ORROOROOOYYRYYRYOOOOYYOO", new BerlinClock().convertTime("11:37:01"));
	}
}
