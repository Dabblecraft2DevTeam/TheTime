package backend.date;

import java.util.ArrayList;

public class DateCalculator {
	
	public Date calculateDate(long ticks, TimeSystem timeSystem){
		
		Date date;
		
		/*
		 * Variable declaration.
		 */
		long tick	 = 0;
		long second  = 0;  	 long ticksPerSecond 			= timeSystem.getTicksPerSecond();
		long minute	 = 0;  	 long secondsPerMinute		    = timeSystem.getSecondsPerMinute();
		long hour  	 = 0;     long minutesPerHour 			= timeSystem.getMinutesPerHour();
		long day  	 = 0;     long hoursPerDay 				= timeSystem.getHoursPerDay();
		long week  	 = 0;     long daysPerWeek 				= timeSystem.getDaysPerWeek();
		long month 	 = 0;     ArrayList<Long> daysPerMonth 	= timeSystem.getDaysPerMonth();
		long year  	 = 0;     long monthsPerYear 			= timeSystem.getMonthsPerYear();
		long era	 = 0;     ArrayList<Long> erasBegin 		= timeSystem.getErasBegin();
					  		 ArrayList<Long> erasEnd   		= timeSystem.getErasEnd();
			
		/*
		 * Calculates the ticks for each date parameter.
		 */
		ticksPerSecond = ticksPerSecond;
		long ticksPerMinute = ticksPerSecond * secondsPerMinute;
		long ticksPerHour 	= ticksPerMinute * minutesPerHour;
		long ticksPerDay    = ticksPerHour   * hoursPerDay;
		long ticksPerWeek   = ticksPerDay    * daysPerWeek;
		ArrayList<Long> ticksPerMonth = new ArrayList<Long>();
			for(long daysThisMonth : daysPerMonth){
				ticksPerMonth.add(ticksPerDay * daysThisMonth);
			}
		long ticksPerYear  = 0;
			for(long ticksThisMonth : ticksPerMonth){
				ticksPerYear = ticksPerYear + ticksThisMonth;
			}
		
		
		/*
		 * Divides ticks by ticksPerYear to get the amount of years. After that subtracts the ticks of the years from the ticks.
		 */
		year = ticks / ticksPerYear;
		ticks = ticks - year * ticksPerYear;
		
		for(long ticksThisMonth : ticksPerMonth){
			
			if(ticks / ticksThisMonth > 0){
				month++;
				ticks = ticks - ticksThisMonth;
			}
		}
		
		week = ticks / ticksPerWeek;
		
		day = ticks / ticksPerDay;
		ticks = ticks - day * ticksPerDay;
		
		hour = ticks / ticksPerHour;
		ticks = ticks - hour * ticksPerHour;
		
		minute = ticks / ticksPerMinute;
		ticks = ticks - minute * ticksPerMinute;
		
		second = ticks / ticksPerSecond;
		ticks = ticks - second * ticksPerSecond;
		
		tick = ticks;
		ticks = ticks - tick;
		
		tick = ticks;
		
		
		/*
		 * Gets the current era.
		 */
		for(long eraBegin : erasBegin){
			int index = erasBegin.indexOf(eraBegin);
			long eraEnd = erasEnd.get(index);
			
			if(eraBegin <= year && eraEnd >= year){
				era = index;
			}
		}
		
		date = new Date(timeSystem, tick, second, minute, hour, day, week, month, year, era);
		
		return date;
	}

}
