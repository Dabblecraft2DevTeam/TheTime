package backend.date;

import java.util.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class DateUtils {
	
	/*
	 * Puts all date properties from DateEnum into a HashMap.
	 */
	public HashMap<DateEnum, Object> toHashMap(Date date){
		date = new Date(date);
		
		HashMap<DateEnum, Object> dateMap = new HashMap<DateEnum, Object>();
		
		dateMap.put(DateEnum.tick, date.getTick());
		dateMap.put(DateEnum.second, date.getSecond());
		dateMap.put(DateEnum.minute, date.getMinute());
		dateMap.put(DateEnum.hour, date.getHour());
		dateMap.put(DateEnum.day, date.getDay());
		dateMap.put(DateEnum.week, date.getWeek());
		dateMap.put(DateEnum.month, date.getMonth());
		dateMap.put(DateEnum.year, date.getYear());
		dateMap.put(DateEnum.era, date.getEra());
		
		dateMap.put(DateEnum.timeSystem, date.getTimeSystem());
		
		return dateMap;
	}
	
	/*
	 * Method to create a date with a timeSystem, but 0 paramaters.
	 */
	public Date getNullDate(TimeSystem timeSystem){
		return new Date(timeSystem, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	/*
	 * Method to add the zero points to the given date object, with the given timeSystem.
	 * Return a new Date object.
	 */
	public Date addZero(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		date.setTick  (date.getTick()   + timeSystem.getTickZero()  );
		date.setSecond(date.getSecond() + timeSystem.getSecondZero());
		date.setMinute(date.getMinute() + timeSystem.getMinuteZero());
		date.setHour  (date.getHour()   + timeSystem.getHourZero()  );
		date.setDay	  (date.getDay()    + timeSystem.getDayZero()   );
		date.setWeek  (date.getWeek()   + timeSystem.getWeekZero()  );
		date.setMonth (date.getMonth()  + timeSystem.getMonthZero() );
		date.setYear  (date.getYear()   + timeSystem.getYearZero()  );
		date.setEra   (date.getEra()    + timeSystem.getEraZero()   );
		
		return date;
	}
	
	/*
	 * Method to remove/discount the zero points from the given date object, with the givent imeSystem.
	 * Returns a new Date object.
	 */
	public Date removeZero(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		date.setTick  (date.getTick()   - timeSystem.getTickZero()  );
		date.setSecond(date.getSecond() - timeSystem.getSecondZero());
		date.setMinute(date.getMinute() - timeSystem.getMinuteZero());
		date.setHour  (date.getHour()   - timeSystem.getHourZero()  );
		date.setDay	  (date.getDay()    - timeSystem.getDayZero()   );
		date.setWeek  (date.getWeek()   - timeSystem.getWeekZero()  );
		date.setMonth (date.getMonth()  - timeSystem.getMonthZero() );
		date.setYear  (date.getYear()   - timeSystem.getYearZero()  );
		date.setEra   (date.getEra()    - timeSystem.getEraZero()   );
		
		return date;
	}
	
	/*
	 * Methods to calculate up or down a single parameter from a given date, with the date timeSystem.
	 * With maximum and minimum check.
	 */
	public Date upTick(Date date){
		
		/*
		 * Creates a new copy of the Date and TimeSystem
		 */
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		/*
		 * Gets the current tick
		 */
		long tick = date.getTick();
		
		/*
		 * Declares variables for the minimum and maximum ticks
		 */
		long minTick = 0;
		long maxTick = timeSystem.getTicksPerSecond() - timeSystem.getTickZero();
		
			/*
			 * Calls up(tick, minTick, maxTick). Return upUnit boolean.
			 * If true calls upSecond(date).
			 */
			if(up(tick, minTick, maxTick)){
				upSecond(date);
			}
		/*
		 * Sets the new tick in the date object.
		 */
		date.setTick(tick);;
		
		/*
		 * Returns the new date.
		 */
		return date;
	}
	
	public Date downTick(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long tick = date.getTick();
		
		long minTick = 0;
		long maxTick = timeSystem.getTicksPerSecond() - timeSystem.getTickZero();
		
			if(down(tick, minTick, maxTick)){
				date = downSecond(date);
			}
		
		date.setTick(tick);
		
		return date;
	}

	public Date upSecond(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long second = date.getSecond();
		
		long minSecond = 0;
		long maxSecond = timeSystem.getSecondsPerMinute() - timeSystem.getSecondZero();
		
			if(up(second, minSecond, maxSecond)){
				upMinute(date);
			}
		
			date.setSecond(second);
		
		return date;
	}
	
	public Date downSecond(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long second = date.getSecond();
		
		long minSecond = 0;
		long maxSecond = timeSystem.getSecondsPerMinute() - timeSystem.getSecondZero();
		
			if(down(second, minSecond, maxSecond)){
				date = downMinute(date);
			}
			
			date.setSecond(second);;
		
		return date;
	}
	
	public Date upMinute(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long minute = date.getMinute();
		
		long minMinute = 0;
		long maxMinute = timeSystem.getMinutesPerHour() - timeSystem.getMinuteZero();
		
		
			if(up(minute, minMinute, maxMinute)){
				date = upHour(date);
			}
			
			date.setMinute(minute);
		
		return date;
	}
	
	public Date downMinute(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long minute = date.getMinute();
		
		long minMinute = 0;
		long maxMinute = timeSystem.getMinutesPerHour() - timeSystem.getMinuteZero();
		
			if(down(minute, minMinute, maxMinute)){
				date = upHour(date);
			}
			
			date.setMinute(minute);
		
		return date;
	}
	
	public Date upHour(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long hour = date.getHour();
		
		long minHour = 0;
		long maxHour = timeSystem.getHoursPerDay() - timeSystem.getHourZero();
		
			if(up(hour, minHour, maxHour)){
				date = upDay(date);
			}
			
		date.setHour(hour);
		
		return date;
	}
	
	public Date downHour(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long hour = date.getHour();
		
		long minHour = 0;
		long maxHour = timeSystem.getHoursPerDay() - timeSystem.getHourZero();
		
			if(down(hour, minHour, maxHour)){
				date = downDay(date);
			}
			
		date.setHour(hour);
		
		return date;
	}
	
	public Date upDay(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long day = date.getDay();
		
		long minDay = 0;
		long maxWeekDay = timeSystem.getDaysPerWeek() - timeSystem.getDayZero();
		long maxMonthDay = timeSystem.getDaysPerMonth().get((int) date.getMonth()) - timeSystem.getDayZero();
		
			if(up(day, minDay, maxMonthDay)){
				date = upMonth(date);
			}
			
		date.setDay(day);
		
		return date;
	}
	
	public Date downDay(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long day = date.getDay();
		
		long minDay = 0;
		long maxMonthDay = timeSystem.getDaysPerMonth().get((int) date.getMonth()) - timeSystem.getDayZero();
		
			if(down(day, minDay, maxMonthDay)){
				date = downMonth(date);
			}
		
		date.setDay(day);
		
		return date;
	}
	
	public Date upMonth(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long month = date.getMonth();
		
		long minMonth = 0;
		long maxMonth = timeSystem.getMonthsPerYear() - timeSystem.getMonthZero();
		
			if(up(month, minMonth, maxMonth)){
				date  = upYear(date);
			}
			
		date.setMonth(month);
		
		return date;
	}
	
	public Date downMonth(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long month = date.getMonth();
		
		long minMonth = 0;
		long maxMonth = timeSystem.getMonthsPerYear() - timeSystem.getMonthZero();
		
			if(down(month, minMonth, maxMonth)){
				date = downYear(date);
			}
			
			date.setMonth(month);
		
		return date;
	}
	
	public Date upYear(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long year = date.getYear();
		
		long minYear = Long.MIN_VALUE;
		long maxYear = Long.MAX_VALUE;
		
				if(up(year, minYear, maxYear)){
				}
		
				date.setYear(year);
				
		return date;
	}
	
	public Date downYear(Date date){
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		long year = date.getYear();
		
		long minYear = Long.MIN_VALUE;
		long maxYear = Long.MAX_VALUE;
			
				if(down(year, minYear, maxYear)){
				}
				
				date.setYear(year);
		
		return date;
	}
	
	
	private boolean up(long unit, long unitMin, long unitMax){
		boolean unitUp = false;
		
		if(unit < unitMax){
			unit++;
		}else{
			unitUp = true;
			unit = unitMin;
		}
		
		return unitUp;
	}
	
	private boolean down(long unit, long unitMin, long unitMax){
		boolean unitDown = false;
		
		if(unit > unitMin){
			unit--;
		}else{
			unitDown = true;
			unit = unitMin;
		}
		
		return unitDown;
	}
}
