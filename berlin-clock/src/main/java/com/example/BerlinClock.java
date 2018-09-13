package com.example;

import static com.example.BerlinClock.Bulb.OFF;
import static com.example.BerlinClock.Bulb.RED;
import static com.example.BerlinClock.Bulb.YELLOW;
import static java.lang.Integer.parseInt;
import static java.util.stream.IntStream.rangeClosed;

import java.util.function.IntFunction;

public class BerlinClock {
	
	enum Bulb {
		YELLOW("Y"),
		RED("R"),
		OFF("O");
		String colour;
		Bulb(String colour) {
			this.colour = colour;
		}
	}
	
	public String convertSingleMinutes(String string) {
		return convertSingleMinutes(new BerlinClockComponents(string));
	}

	public String convertFiveMinutes(String string) {
		return convertFiveMinutes(new BerlinClockComponents(string));
	}

	public String convertSingleHours(String string) {
		return convertSingleHours(new BerlinClockComponents(string));
	}

	public String convertFiveHours(String string) {
		return convertFiveHours(new BerlinClockComponents(string));
	}

	public String convertSeconds(String string) {
		return new BerlinClockComponents(string).secondsLamp == 0 ? YELLOW.colour : OFF.colour;
	}

	public String convertTime(String string) {
		BerlinClockComponents components = new BerlinClockComponents(string);
		return convertSeconds(string) + 
			   convertFiveHours(components) + 
			   convertSingleHours(components) + 
			   convertFiveMinutes(components) + 
			   convertSingleMinutes(components);
	}
	
	private String convertSingleMinutes(BerlinClockComponents components) {
		return rangeClosed(1, 4).mapToObj(customOnOffMap(components.singleMinutes, YELLOW.colour, OFF.colour)).reduce(String::concat).get();
	}
	
	private String convertFiveMinutes(BerlinClockComponents components) {
		return rangeClosed(1, 11).mapToObj(customTwoOnOffMap(components.fiveMinutes, RED.colour, YELLOW.colour, 3, OFF.colour)).reduce(String::concat).get();
	}
	
	private String convertSingleHours(BerlinClockComponents components) {
		return rangeClosed(1, 4).mapToObj(customOnOffMap(components.singleHours, RED.colour, OFF.colour)).reduce(String::concat).get();
	}
	
	private String convertFiveHours(BerlinClockComponents components) {
		return rangeClosed(1, 4).mapToObj(customOnOffMap(components.fiveHours, RED.colour, OFF.colour)).reduce(String::concat).get();
	}
	
	private IntFunction<String> customOnOffMap(int component, String on, String off) {
		return i -> component >= i ? on : off;
	}
	
	private IntFunction<String> customTwoOnOffMap(int component, String onOne, String onTwo, int multiple, String off) {
		return i -> component >= i ? 
				i % multiple == 0 ? 
					onOne : onTwo 
				: off;
	}
	
	private class BerlinClockComponents {
		final int singleMinutes;
		final int fiveMinutes;
		final int singleHours;
		final int fiveHours;
		final int secondsLamp;
		BerlinClockComponents(String time) {
			String[] timeComponents = time.split(":");
			int hours = parseInt(timeComponents[0]);
			int minutes = parseInt(timeComponents[1]);
			int seconds = parseInt(timeComponents[2]);
			singleMinutes = minutes % 5;
			fiveMinutes = minutes / 5;
			singleHours = hours % 5;
			fiveHours = hours / 5;
			secondsLamp = seconds % 2;
		}
		
	}

}
