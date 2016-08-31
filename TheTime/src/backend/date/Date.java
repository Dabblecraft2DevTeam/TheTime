package backend.date;

import java.util.HashMap;

public class Date {
	
	TimeSystem timeSystem;
	
	long tick;
	long second;
	long minute;
	long hour;
	long day;
	long week;
	long month;
	long year;
	long era;
	
	/*
	 * Is called when an instance of this class is created.
	 * Declares all class variables with the given parameters.
	 */
	public Date(TimeSystem timeSystem,
			
				long tick,
				long second,
				long minute,
				long hour,
				long day,
				long week,
				long month,
				long year,
				long era){
		
		this.timeSystem = timeSystem;
		
		this.tick 	 = tick;
		this.second  = second;
		this.minute  = minute;
		this.hour 	 = hour;
		this.day 	 = day;
		this.week 	 = week;
		this.month 	 = month;
		this.year 	 = year;
		this.era 	 = era;
		
	}
	
	/*
	 * Is called when an instance of this class is created.
	 * Declares all variables with an Date Object.
	 */
	public Date(Date date){
		
		this.timeSystem = date.getTimeSystem();
		
		this.tick 	= date.getTick();
		this.second = date.getSecond();
		this.minute = date.getMinute();
		this.hour 	= date.getHour();
		this.day 	= date.getDay();
		this.week 	= date.getWeek();
		this.month 	= date.getMonth();
		this.year 	= date.getYear();
		this.era 	= date.getEra();
	}
	
	/*
	 * Getter for the TimeSystem of the Date object.
	 * Returns a TimeSystem object, which was used to create the date.
	 */
	public TimeSystem getTimeSystem() {
		return timeSystem;
	}
	
	/*
	 * Getters for date parameters.
	 */
	public long getTick() {
		return tick;
	}

	public long getSecond() {
		return second;
	}

	public long getMinute() {
		return minute;
	}

	public long getHour() {
		return hour;
	}

	public long getDay() {
		return day;
	}

	public long getWeek() {
		return week;
	}

	public long getMonth() {
		return month;
	}

	public long getYear() {
		return year;
	}

	public long getEra() {
		return era;
	}
	
	/*
	 * Setters for date parameters.
	 */
	public void setTimeSystem(TimeSystem timeSystem) {
		this.timeSystem = timeSystem;
	}

	public void setTick(long tick) {
		this.tick = tick;
	}

	public void setSecond(long second) {
		this.second = second;
	}

	public void setMinute(long minute) {
		this.minute = minute;
	}

	public void setHour(long hour) {
		this.hour = hour;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public void setWeek(long week) {
		this.week = week;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public void setEra(long era) {
		this.era = era;
	}

}
