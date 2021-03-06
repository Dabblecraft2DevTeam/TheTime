package TheTime.backend.date;

import java.util.ArrayList;

public class TimeSystem {
	
	//TimeSystem name
	private String name;
	
	//Date parameters.
	private long ticksPerSecond;
	private long secondsPerMinute;
	private long minutesPerHour;
	private long hoursPerDay;
	private long daysPerWeek;
	private ArrayList<Long> daysPerMonth;
	private long monthsPerYear;
	private ArrayList<Long> erasBegin;
	private ArrayList<Long> erasEnd;

	// Zero points
	private long tickZero;
	private long secondZero;
	private long minuteZero;
	private long hourZero;
	private long dayZero;
	private long weekZero;
	private long monthZero;
	private long yearZero;
	private long eraZero;
	
	//Date parameter names.
	private ArrayList<String> dayNames;
	private ArrayList<String> monthNames;
	private ArrayList<String> eraNames;
	
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
					  long eraZero,
					  
					  ArrayList<String> dayNames,
					  ArrayList<String> monthNames,
					  ArrayList<String> eraNames) {
		
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
		
		this.dayNames = dayNames;
		this.monthNames = monthNames;
		this.eraNames = eraNames;
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
		
		this.dayNames 			= timeSystem.getDayNames();
		this.monthNames			= timeSystem.getMonthNames();
		this.eraNames			= timeSystem.getEraNames();
		
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
	 *  Name getter.
	 */
	
	public ArrayList<String> getDayNames() {
		return dayNames;
	}
	
	public ArrayList<String> getMonthNames() {
		return monthNames;
	}
	
	public ArrayList<String> getEraNames() {
		return eraNames;
	}
	
}
