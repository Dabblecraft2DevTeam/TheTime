package TheTime.backend.date;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import TheTime.backend.main.main;

public class DateUtils {
	
	/*
	 * Puts all date properties from DateEnum into a HashMap.
	 */
	public HashMap<DateEnum, Object> toHashMap(Date date){
		date = new Date(date);
		
		HashMap<DateEnum, Object> dateMap = new HashMap<DateEnum, Object>();
		
		dateMap.put(DateEnum.timeSystem, date.getTimeSystem());
		
		dateMap.put(DateEnum.tick, date.getTick());
		dateMap.put(DateEnum.second, date.getSecond());
		dateMap.put(DateEnum.minute, date.getMinute());
		dateMap.put(DateEnum.hour, date.getHour());
		dateMap.put(DateEnum.day, date.getDay());
		dateMap.put(DateEnum.week, date.getWeek());
		dateMap.put(DateEnum.month, date.getMonth());
		dateMap.put(DateEnum.year, date.getYear());
		dateMap.put(DateEnum.era, date.getEra());
		
		return dateMap;
	}
	
	/*
	 * Method to create a date with a timeSystem, but 0 paramaters.
	 */
	public Date getNullDate(TimeSystem timeSystem){
		return new Date(timeSystem, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	/*
	 * Method to add the zero points to the given date object, with the given timeSystem.
	 * Return a new Date object.
	 */
	public Date addZero(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		date.setTick	(date.getTick() 	+ timeSystem.getTickZero());
		date.setSecond	(date.getSecond() 	+ timeSystem.getSecondZero());
		date.setMinute	(date.getMinute() 	+ timeSystem.getMinuteZero());
		date.setHour	(date.getHour() 	+ timeSystem.getHourZero());
		date.setDay		(date.getDay() 		+ timeSystem.getDayZero());
		date.setWeek	(date.getWeek() 	+ timeSystem.getWeekZero());
		date.setMonth	(date.getMonth() 	+ timeSystem.getMonthZero());
		date.setYear	(date.getYear() 	+ timeSystem.getYearZero());
		date.setEra		(date.getEra() 		+ timeSystem.getEraZero());
		
		return date;
	}
	
	/*
	 * Method to remove/discount the zero points from the given date object, with the givent imeSystem.
	 * Returns a new Date object.
	 */
	public Date removeZero(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		date.setTick	(date.getTick() 	- timeSystem.getTickZero());
		date.setSecond	(date.getSecond() 	- timeSystem.getSecondZero());
		date.setMinute	(date.getMinute() 	- timeSystem.getMinuteZero());
		date.setHour	(date.getHour() 	- timeSystem.getHourZero());
		date.setDay		(date.getDay() 		- timeSystem.getDayZero());
		date.setWeek	(date.getWeek() 	- timeSystem.getWeekZero());
		date.setMonth	(date.getMonth() 	- timeSystem.getMonthZero());
		date.setYear	(date.getYear() 	- timeSystem.getYearZero());
		date.setEra		(date.getEra() 		- timeSystem.getEraZero());
		
		return date;
	}
	
	/*
	 * Methods to calculate up or down a single parameter from a given date, with the date timeSystem.
	 * With maximum and minimum check.
	 */	
	public Date up(DateEnum unit, int count, Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		DateCalculator dateCalculator = main.getDateCalculator();
		long ticks = date.getRootTicks();
		
		long ticksPerSecond = timeSystem.getTicksPerSecond();
		long ticksPerMinute = ticksPerSecond * timeSystem.getSecondsPerMinute();
		long ticksPerHour 	= ticksPerMinute * timeSystem.getMinutesPerHour();
		long ticksPerDay    = ticksPerHour   * timeSystem.getHoursPerDay();
		long ticksPerWeek   = ticksPerDay    * timeSystem.getDaysPerWeek();
		ArrayList<Long> ticksPerMonth = new ArrayList<Long>();
			for(long daysThisMonth : timeSystem.getDaysPerMonth()){
				ticksPerMonth.add(ticksPerDay * daysThisMonth);
			}
		long ticksPerYear  = 0;
			for(long ticksThisMonth : ticksPerMonth){
				ticksPerYear = ticksPerYear + ticksThisMonth;
			}
		
	switch(unit){
		
		case tick:
			return dateCalculator.dateFromTicks(ticks + (1 * count), timeSystem);
		
		case second:
			return dateCalculator.dateFromTicks(ticks + (ticksPerSecond * count),  timeSystem);
		
		case minute:
			return dateCalculator.dateFromTicks(ticks + (ticksPerMinute * count), timeSystem);
		
		case hour:
			return dateCalculator.dateFromTicks(ticks + (ticksPerHour * count), timeSystem);
		
		case day:
			return dateCalculator.dateFromTicks(ticks + (ticksPerDay * count), timeSystem);
		
		case week:
			return dateCalculator.dateFromTicks(ticks + (ticksPerWeek * count), timeSystem);
		
		case month:
			return dateCalculator.dateFromTicks(ticks + ticksPerMonth.get((int) date.getMonth() + 1), timeSystem);
		
		case year:
			return dateCalculator.dateFromTicks(ticks + (ticksPerYear * count), timeSystem);
		
		default:
			return date;
		}
		
	}
	
	public Date down(DateEnum unit, int count, Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		DateCalculator dateCalculator = main.getDateCalculator();
		long ticks = date.getRootTicks();
		
		long ticksPerSecond = timeSystem.getTicksPerSecond();
		long ticksPerMinute = ticksPerSecond * timeSystem.getSecondsPerMinute();
		long ticksPerHour 	= ticksPerMinute * timeSystem.getMinutesPerHour();
		long ticksPerDay    = ticksPerHour   * timeSystem.getHoursPerDay();
		long ticksPerWeek   = ticksPerDay    * timeSystem.getDaysPerWeek();
		ArrayList<Long> ticksPerMonth = new ArrayList<Long>();
			for(long daysThisMonth : timeSystem.getDaysPerMonth()){
				ticksPerMonth.add(ticksPerDay * daysThisMonth);
			}
		long ticksPerYear  = 0;
			for(long ticksThisMonth : ticksPerMonth){
				ticksPerYear = ticksPerYear + ticksThisMonth;
			}
		
	switch(unit){
		
		case tick:
			return dateCalculator.dateFromTicks(ticks - (1 * count), timeSystem);
		
		case second:
			return dateCalculator.dateFromTicks(ticks - (ticksPerSecond * count),  timeSystem);
		
		case minute:
			return dateCalculator.dateFromTicks(ticks - (ticksPerMinute * count), timeSystem);
		
		case hour:
			return dateCalculator.dateFromTicks(ticks - (ticksPerHour * count), timeSystem);
		
		case day:
			return dateCalculator.dateFromTicks(ticks - (ticksPerDay * count), timeSystem);
		
		case week:
			return dateCalculator.dateFromTicks(ticks - (ticksPerWeek * count), timeSystem);
		
		case month:
			return dateCalculator.dateFromTicks(ticks - ticksPerMonth.get((int) date.getMonth() - 1), timeSystem);
		
		case year:
			return dateCalculator.dateFromTicks(ticks - (ticksPerYear * count), timeSystem);
		
		default:
			return date;
		}
		
	}
	
	/*
	 * Method to get the day of the week.
	 */
	public long getDayOfWeek(Date date) {
		date = new Date(date);
		date.setDay(date.getDay() + date.getTimeSystem().getDayZero());
		
	    int cc = (int) (date.getYear()/100);
	    int yy = (int) (date.getYear() - ((date.getYear()/100)*100));

	    int c = (cc/4) - 2*cc-1;
	    int y = 5*yy/4;
	    int m = (int) (26*(date.getMonth()+1)/10);
	    int d = (int) date.getDay();

	    int dayOfWeek = (int) ((c+y+m+d) % date.getTimeSystem().getDaysPerWeek());
		
		return dayOfWeek;
	}
}
