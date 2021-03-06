package TheTime.backend.date;

import java.util.ArrayList;
import java.util.HashMap;

import TheTime.backend.main.main;

public class TimeSystemUtils {
	
	/*
	 * Puts all time-system properties into a HashMap.
	 * All properties are saved in the enum time.TimeSystemEnum.
	 */
	public HashMap<TimeSystemEnum, Object> toHashMap(TimeSystem timeSystem){
		timeSystem = new TimeSystem(timeSystem);
			
		HashMap<TimeSystemEnum, Object> timeSystemMap = new HashMap();
		
			timeSystemMap.put(TimeSystemEnum.NAME, timeSystem.getName());
			
			timeSystemMap.put(TimeSystemEnum.ticksPerSecond, timeSystem.getTicksPerSecond());
			timeSystemMap.put(TimeSystemEnum.secondsPerMinute, timeSystem.getSecondsPerMinute());
			timeSystemMap.put(TimeSystemEnum.minutesPerHour, timeSystem.getMinutesPerHour());
			timeSystemMap.put(TimeSystemEnum.hoursPerDay, timeSystem.getHoursPerDay());
			timeSystemMap.put(TimeSystemEnum.daysPerWeek, timeSystem.getDaysPerWeek());
			timeSystemMap.put(TimeSystemEnum.daysPerMonth, timeSystem.getDaysPerMonth());
			timeSystemMap.put(TimeSystemEnum.monthsPerYear, timeSystem.getMonthsPerYear());
			timeSystemMap.put(TimeSystemEnum.erasBegin, timeSystem.getErasBegin());
			timeSystemMap.put(TimeSystemEnum.erasEnd, timeSystem.getErasEnd());
			
			timeSystemMap.put(TimeSystemEnum.tickZero, timeSystem.getTickZero());
			timeSystemMap.put(TimeSystemEnum.secondZero, timeSystem.getSecondZero());
			timeSystemMap.put(TimeSystemEnum.minuteZero, timeSystem.getMinuteZero());
			timeSystemMap.put(TimeSystemEnum.hourZero, timeSystem.getHourZero());
			timeSystemMap.put(TimeSystemEnum.dayZero, timeSystem.getDayZero());
			timeSystemMap.put(TimeSystemEnum.weekZero, timeSystem.getWeekZero());
			timeSystemMap.put(TimeSystemEnum.monthZero, timeSystem.getMonthZero());
			timeSystemMap.put(TimeSystemEnum.eraZero, timeSystem.getEraZero());
			
			return null;
	}
	
	/*
	 * Method to get the ticks of the different date units.
	 */
	public Object getTicksPerUnit(DateEnum unit, TimeSystem timeSystem) {
		
		/*
		 * Calculates the ticks for each date parameter.
		 */
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
			
			
		switch(unit) {
			case tick:
				return 1;
			case second:
				return ticksPerSecond;
			case minute:
				return ticksPerMinute;
			case hour:
				return ticksPerHour;
			case day:
				return ticksPerDay;
			case week:
				return ticksPerWeek;
			case month:
				return ticksPerMonth;
			case year:
				return ticksPerYear;
			default:
				return 0;
		}
	}

}
