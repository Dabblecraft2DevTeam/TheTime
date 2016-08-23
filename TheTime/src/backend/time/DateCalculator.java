package backend.time;

import java.util.ArrayList;

public class DateCalculator {
	
	public Date calculateDate(long ticks, TimeSystem timeSystem){
		
		Date date;
		
		/*
		 * Variable declaration.
		 */
		long tick	= 0;
		long second = 0;  	 long ticksPerSecond 			= timeSystem.getTicksPerSecond();
		long minute	= 0;  	 long secondsPerMinute		    = timeSystem.getSecondsPerMinute();
		long hour  	= 0;     long minutesPerHour 			= timeSystem.getMinutesPerHour();
		long day  	= 0;     long hoursPerDay 				= timeSystem.getHoursPerDay();
		long week  	= 0;     long daysPerWeek 				= timeSystem.getDaysPerWeek();
		long month 	= 0;     ArrayList<Long> daysPerMonth 	= timeSystem.getDaysPerMonth();
		long year  	= 0;     long monthsPerYear 			= timeSystem.getMonthsPerYear();
		long era	= 0;     ArrayList<Long> erasBegin 		= timeSystem.getErasBegin();
					  		 ArrayList<Long> erasEnd   		= timeSystem.getErasEnd();
					  
		
		/*
		 * Time calculation part.
		 */
		tick = ticks;
		
		second = tick / ticksPerSecond;
		tick = tick - second * ticksPerSecond;
		
		minute = second / secondsPerMinute;
		second = second - minute * secondsPerMinute;
		
		hour = minute / minutesPerHour;
		minute = minute - hour * minutesPerHour;
		
		day = hour / hoursPerDay;
		hour = hour - day * hoursPerDay;
		
		week = day / daysPerWeek;
				
		for(int i = 0; day >= daysPerMonth.get(i); i++){
			
			week = week - (day / daysPerWeek);
			
			month = month + (day / daysPerMonth.get(i));
			day = day - (1 * daysPerMonth.get(i));
		
			year = year + month / monthsPerYear;
			month = month - (year * daysPerMonth.get(i));
		
		}
		
		for(long eraBegin : erasBegin){
			
			int index = erasBegin.indexOf(erasBegin);
			long eraEnd = erasEnd.get(index);
			
			if(eraBegin <= year && eraEnd >= year){
				era = index;
			}
		}
		
		date = new Date(timeSystem, tick, second, minute, hour, day, week, month, year, era);
		
		return date;
	}

}
