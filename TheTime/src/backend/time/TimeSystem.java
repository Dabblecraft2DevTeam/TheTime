package backend.time;

import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.HashMap;

public class TimeSystem {
	
	String name;
	
	long ticksPerSecond;
	long secondsPerMinute;
	long minutesPerHour;
	long hoursPerDay;
	long daysPerWeek;
	ArrayList<Long> daysPerMonth;
	long monthsPerYear;
	ArrayList<Long> erasBegin;
	ArrayList<Long> erasEnd;

	// Zero points
	long tickZero;
	long secondZero;
	long minuteZero;
	long hourZero;
	long dayZero;
	long weekZero;
	long monthZero;
	long yearZero;
	
	long eraZero;
	
	/*
	 * Is called when an instance of this class is created.
	 * Declares all class variables with the given parameters (sets the time-system).
	 */
	public TimeSystem(String name,
					  long ticksPerSecond,
					  long secondsPerMinute,
					  long minutesPerHour,
					  long hoursPerDay,
					  long daysPerWeek,
					  ArrayList<Long> daysPerMonth,
					  long monthsPerYear,
					  ArrayList<Long> erasBegin,
					  ArrayList<Long> erasEnd,
					  
					  long tickZero,
					  long secondZero,
					  long minuteZero,
					  long hourZero,
					  long dayZero,
					  long weekZero,
					  long monthZero,
					  long yearZero,
					  long eraZero) {
		
		this.name 				= name;
		
		this.ticksPerSecond 	= ticksPerSecond;
		this.secondsPerMinute 	= secondsPerMinute;
		this.minutesPerHour	 	= minutesPerHour;
		this.hoursPerDay 		= hoursPerDay;
		this.daysPerWeek 		= daysPerWeek;
		this.daysPerMonth 		= daysPerMonth;
		this.monthsPerYear 		= monthsPerYear;
		this.erasBegin 			= erasBegin;
		this.erasEnd 			= erasEnd;
		
		this.tickZero 			= tickZero;
		this.secondZero 		= secondZero;
		this.minuteZero 		= minuteZero;
		this.hourZero 			= hourZero;
		this.dayZero 			= dayZero;
		this.weekZero 			= weekZero;
		this.monthZero 			= monthZero;
		this.yearZero 			= yearZero;
		this.eraZero 			= eraZero;
	}
	
	/*
	 * Is called when an instance of this class is created.
	 * Declares all variables with a TimeSystem Object.
	 */
	public TimeSystem(TimeSystem timeSystem){
		
		this.name = timeSystem.getName();
		
		this.ticksPerSecond 	= timeSystem.getTicksPerSecond();
		this.secondsPerMinute 	= timeSystem.getSecondsPerMinute();
		this.minutesPerHour 	= timeSystem.getMinutesPerHour();
		this.hoursPerDay 		= timeSystem.getHoursPerDay();
		this.daysPerWeek 		= timeSystem.getDaysPerWeek();
		this.daysPerMonth 		= timeSystem.getDaysPerMonth();
		this.monthsPerYear 		= timeSystem.getMonthsPerYear();
		this.erasBegin 			= timeSystem.getErasBegin();
		this.erasEnd 			= timeSystem.getErasEnd();
		
		this.tickZero 			= timeSystem.getTickZero();
		this.secondZero 		= timeSystem.getSecondZero();
		this.minuteZero 		= timeSystem.getMinuteZero();
		this.hourZero 			= timeSystem.getHourZero();
		this.dayZero 			= timeSystem.getDayZero();
		this.weekZero 			= timeSystem.getWeekZero();
		this.monthZero 			= timeSystem.getMonthZero();
		this.yearZero 			= timeSystem.getYearZero();
		this.eraZero 			= timeSystem.getEraZero();
		
	}
	
	
	/*
	 * Getters for the time-system properties.
	 */
	public String getName() {
		return name;
	}

	public long getTicksPerSecond() {
		return ticksPerSecond;
	}

	public long getSecondsPerMinute() {
		return secondsPerMinute;
	}

	public long getMinutesPerHour() {
		return minutesPerHour;
	}

	public long getHoursPerDay() {
		return hoursPerDay;
	}

	public long getDaysPerWeek() {
		return daysPerWeek;
	}

	public ArrayList<Long> getDaysPerMonth() {
		return daysPerMonth;
	}

	public long getMonthsPerYear() {
		return monthsPerYear;
	}

	public ArrayList<Long> getErasBegin() {
		return erasBegin;
	}

	public ArrayList<Long> getErasEnd() {
		return erasEnd;
	}

	
	/*
	 * Zero point getter.
	 */
	public long getTickZero() {
		return tickZero;
	}

	public long getSecondZero() {
		return secondZero;
	}

	public long getMinuteZero() {
		return minuteZero;
	}

	public long getHourZero() {
		return hourZero;
	}

	public long getDayZero() {
		return dayZero;
	}

	public long getWeekZero() {
		return weekZero;
	}

	public long getMonthZero() {
		return monthZero;
	}

	public long getYearZero() {
		return yearZero;
	}

	public long getEraZero() {
		return eraZero;
	}
	

	/*
	 * Puts all time-system properties into a HashMap.
	 * All properties are saved in the enum time.TimeSystemEnum.
	 */
	public HashMap<TimeSystemEnum, Object> toHashMap() {
		
		HashMap<TimeSystemEnum, Object> timeSystem = new HashMap<TimeSystemEnum, Object>();
		
		timeSystem.put(TimeSystemEnum.NAME, name);
		
		timeSystem.put(TimeSystemEnum.ticksPerSecond, ticksPerSecond);
		timeSystem.put(TimeSystemEnum.secondsPerMinute, secondsPerMinute);
		timeSystem.put(TimeSystemEnum.minutesPerHour, minutesPerHour);
		timeSystem.put(TimeSystemEnum.hoursPerDay, hoursPerDay);
		timeSystem.put(TimeSystemEnum.daysPerWeek, daysPerWeek);
		timeSystem.put(TimeSystemEnum.daysPerMonth, daysPerMonth);
		timeSystem.put(TimeSystemEnum.monthsPerYear, monthsPerYear);
		timeSystem.put(TimeSystemEnum.erasBegin, erasBegin);
		timeSystem.put(TimeSystemEnum.erasEnd, erasEnd);
		
		timeSystem.put(TimeSystemEnum.tickZero, tickZero);
		timeSystem.put(TimeSystemEnum.secondZero, secondZero);
		timeSystem.put(TimeSystemEnum.minuteZero, minuteZero);
		timeSystem.put(TimeSystemEnum.hourZero, hourZero);
		timeSystem.put(TimeSystemEnum.dayZero, dayZero);
		timeSystem.put(TimeSystemEnum.weekZero, weekZero);
		timeSystem.put(TimeSystemEnum.monthZero, monthZero);
		timeSystem.put(TimeSystemEnum.eraZero, eraZero);
		
		return null;
	}
	
}
