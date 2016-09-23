package TheTime.backend.date;

import TheTime.backend.main.main;

public class Date {
	
	private TimeSystem timeSystem;
	long rootTicks;
	
	private long tick;
	private long second;
	private long minute;
	private long hour;
	private long day;
	private long week;
	private long month;
	private long year;
	private long era;
	/*
	 * Is called when an instance of this class is created.
	 * Declares all class variables with the given parameters.
	 */
	public Date(TimeSystem timeSystem, long rootTicks,
				
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
		this.rootTicks  = rootTicks;
		
		this.tick 	 		= tick;
		this.second  		= second;
		this.minute  		= minute;
		this.hour 	 		= hour;
		this.day			= day;
		this.week			= week;
		this.month 	 		= month;
		this.year 	 		= year;
		this.era 	 		= era;
		
	}
	
	/*
	 * Is called when an instance of this class is created.
	 * Declares all variables with an Date Object.
	 */
	public Date(Date date){
		
		this.timeSystem = date.getTimeSystem();
		this.rootTicks 	= date.getRootTicks();
		
		this.tick 	= date.getTick();
		this.second = date.getSecond();
		this.minute = date.getMinute();
		this.hour 	= date.getHour();
		this.day	= date.getDay();
		this.week	= date.getWeek();
		this.month 	= date.getMonth();
		this.year 	= date.getYear();
		this.era 	= date.getEra();
	}
	
	/*
	 * Method to check if the Date is before the given Date.
	 */
	public boolean isBefore(Date date) {
		return true;
	}
	
	/*
	 * Method to check if the Date is after the given Date.
	 */
	public boolean isAfter(Date date) {
		return true;
	}
	
	
	public void toNullDate() {
		Date date = main.getDateUtils().getNullDate(timeSystem);
		this.rootTicks = date.getRootTicks();
		
		this.tick = date.getTick();
		this.second = date.getSecond();
		this.minute = date.getMinute();
		this.hour = date.getHour();
		this.day = date.getDay();
		this.week = date.getWeek();
		this.month = date.getMonth();
		this.year = date.getYear();
		this.era = date.getEra();
	}
	
	/*
	 * Getter for the TimeSystem of the Date object.
	 * Returns a TimeSystem object, which was used to create the date.
	 */
	public TimeSystem getTimeSystem() {
		return timeSystem;
	}
	
	/*
	 * Method to get the root Ticks of the date Object;
	 */
	public long getRootTicks() {
		return rootTicks;
	}
 
	/*
	 * Getters for the Date Units
	 */
	public long getTick() {
		return tick;
	}

	public void setTick(long tick) {
		this.tick = tick;
	}

	public long getSecond() {
		return second;
	}

	public void setSecond(long second) {
		this.second = second;
	}

	public long getMinute() {
		return minute;
	}

	public void setMinute(long minute) {
		this.minute = minute;
	}

	public long getHour() {
		return hour;
	}

	public void setHour(long hour) {
		this.hour = hour;
	}

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public long getWeek() {
		return week;
	}

	public void setWeek(long week) {
		this.week = week;
	}

	public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public long getEra() {
		return era;
	}

	public void setEra(long era) {
		this.era = era;
	}
	
}
