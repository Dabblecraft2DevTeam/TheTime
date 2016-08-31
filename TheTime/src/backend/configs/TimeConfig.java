package backend.configs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import backend.date.TimeSystem;
import backend.main.main;
import net.md_5.bungee.api.ChatColor;

public class TimeConfig extends Config implements ConfigUtils {
	
	main instance = main.instance;
	
	FileConfiguration config;
	
	public TimeConfig() {
		super();
		
		config = super.loadConfig(instance.getDataFolder(), "TimeConfig.yml");
		reader();
	}
	
	HashMap<String, TimeSystem> timeSystems = new HashMap<String, TimeSystem>();
	
	/*
	 * Reads the configuration file and declares variables for the parameters of the config.
	 */
	private void reader() {
		
		ConfigurationSection timeSystemsName = config.getConfigurationSection("Time-Systems");
		for(String timeSystemName : timeSystemsName.getKeys(false)){
			timeSystems.put(timeSystemName, getTimeSystem(timeSystemName));
		}
		
	}
	
	public HashMap<String, TimeSystem> getTimeSystems(){
		return timeSystems;
	}
	
	private TimeSystem getTimeSystem(String timeSystemName){
		
		//Tick
																	long tickZero		= 0;
		//Second
		long ticksPerSecond 			= 0;          				long secondZero		= 0;
		
		//Minute
		long secondsPerMinute			= 0;						long minuteZero		= 0;
		
		//Hour
		long minutesPerHour				= 0;   						long hourZero		= 0;
		
		//Day
		long hoursPerDay				= 0;						long dayZero		= 0;
		
		//Week
		long daysPerWeek 				= 0;						long weekZero		= 0;	ArrayList<String> dayNames 		= new ArrayList<String>();
		
		//Month
		ArrayList<Long> daysPerMonth	= new ArrayList<Long>();	long monthZero		= 0;	ArrayList<String> monthNames	= new ArrayList<String>();
		
		//Year
		long monthsPerYear				= 0;						long yearZero		= 0;
		
		//Era
		ArrayList<Long> erasBegin		= new ArrayList<Long>();	long eraZero		= 0;	ArrayList<String> eraNames		= new ArrayList<String>();
		ArrayList<Long> erasEnd			= new ArrayList<Long>();
		
		String defaultPath = "Time-Systems." + timeSystemName + ".";
		String path;
		
		//Tick
		path = defaultPath + "tick.";
		
		tickZero = getZero(path);
		
		//Second
		path = defaultPath + "second.";
		
		ticksPerSecond = getLong(path + "ticksPerSecond");
		secondZero = getZero(path);
		
		//Minute
		path = defaultPath + "minute.";
		
		secondsPerMinute = getLong(path + "secondsPerMinute");
		minuteZero = getZero(path);
		
		//Hour
		path = defaultPath + "hour.";
		
		minutesPerHour = getLong(path + "minutesPerHour");
		hourZero = getZero(path);
		
		//Day
		path = defaultPath + "day.";
		
		hoursPerDay = getLong(path + "hoursPerDay");
		dayZero = getZero(path);
		
		ArrayList<Object> dayNamesObject = getSection(path + "dayNames", "name");
		for(Object dayNameObject : dayNamesObject){
			dayNames.add((String) dayNameObject); 
		}
		
		//Week
		path = defaultPath + "week.";
				
		daysPerWeek = getLong(path + "daysPerWeek");
		weekZero = getZero(path);
		
		//Month
		path = defaultPath + "month.";
		
		ArrayList<Object> daysPerMonthObject = getSection(path + "daysPerMonth", "days");
			for(Object daysThisMonthObject : daysPerMonthObject){
					daysPerMonth.add(Long.valueOf((String) daysThisMonthObject));
			}		
		monthZero = getZero(path);
		
		ArrayList<Object> monthNamesObject = getSection(path + "daysPerMonth", "name");
			for(Object monthNameObject : monthNamesObject){
				monthNames.add((String) monthNameObject);
			}
		
		//Year
		path = defaultPath + "year.";
		
		monthsPerYear = getLong(path + "monthsPerYear");
		yearZero = getZero(path);
		
		//Era
		path = defaultPath + "era.";
		
		ArrayList<Object> erasBeginObjects = getSection(path + "eras", "startYear");
			for(Object erasBeginObject : erasBeginObjects){
				erasBegin.add(Long.valueOf((String) erasBeginObject));
			}
		ArrayList<Object> erasEndObjects = getSection(path + "eras", "endYear");
			for(Object erasEndObject : erasEndObjects){
				erasEnd.add(Long.valueOf((String) erasEndObject));
			}
		eraZero = getZero(path);
		
		ArrayList<Object> erasNameObjects = getSection(path + "eras", "name");
			for(Object eraNameObject : erasNameObjects){
				eraNames.add((String) eraNameObject);
			}

		
		TimeSystem timeSystem = new TimeSystem(timeSystemName,
											   ticksPerSecond,
											   secondsPerMinute,
											   minutesPerHour,
											   hoursPerDay,
											   daysPerWeek,
											   daysPerMonth,
											   monthsPerYear,
											   erasBegin,
											   erasEnd,
											   
											   tickZero,
											   secondZero,
											   minuteZero,
											   hourZero,
											   dayZero,
											   weekZero,
											   monthZero,
											   yearZero,
											   eraZero,
											   
											   dayNames,
											   monthNames,
											   eraNames);
		
		return timeSystem;
	}
	
	/*
	 * Gets the zero for a date parameter out of a given date parameter configuration section.
	 * Returns a long value.
	 */
	private long getZero(String path){
		return getLong(path + "zero");
	}
	
	/*
	 * Returns section value of the keys of a given configuration section.
	 */
	private ArrayList<Object> getSection(String path, String value){
		
		ArrayList<Object> names = new ArrayList<Object>();
		
		ConfigurationSection section = config.getConfigurationSection(path);
		
		if (section != null) {
			for(String key : section.getKeys(false)){
				names.add(getString(path + "." + key + "." + value));
			}
		}
		
		return names;		
	}
	
	/*
	 * @see backend.configs.ConfigUtils#getString(java.lang.String)
	 */
	@Override
	public String getString(String path) {
		return ChatColor.translateAlternateColorCodes('&', config.getString(path));
	}

	/*
	 * @see backend.configs.ConfigUtils#getInteger(java.lang.String)
	 */
	@Override
	public Integer getInteger(String path) {
		return config.getInt(path);
	}

	/*
	 * @see backend.configs.ConfigUtils#getLong(java.lang.String)
	 */
	@Override
	public Long getLong(String path) {
		return Long.valueOf(config.getString(path));
	}

	/*
	 * @see backend.configs.ConfigUtils#getBoolean(java.lang.String)
	 */
	@Override
	public Boolean getBoolean(String path) {
		return config.getBoolean(path);
	}

	/*
	 * @see backend.configs.ConfigUtils#getListString(java.lang.String)
	 */
	@Override
	public List<String> getListString(String path) {
		
		if(config.getList(path) != null){
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) config.getList(path);
		
			for(int index = 0; index < list.size(); index++){
				list.set(index, ChatColor.translateAlternateColorCodes('&', list.get(index)));
			}
		
			return (List<String>) list;
		}
		
		return null;
	}

	/*
	 * @see backend.configs.ConfigUtils#getArrayListString(java.lang.String)
	 */
	@Override
	public ArrayList<String> getArrayListString(String path) {
		
		List<String> list = getListString(path);
		ArrayList<String> arrayList = new ArrayList<String>();
		
		for(int index = 0; index < list.size(); index++){
			arrayList.add(list.get(index));
		}
		
		return arrayList;
	}
	
}
