package backend.date;

import java.util.HashMap;

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

}
