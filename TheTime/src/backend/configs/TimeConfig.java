package backend.configs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import backend.main.main;
import backend.time.TimeSystem;
import net.md_5.bungee.api.ChatColor;

public class TimeConfig extends Config implements ConfigUtils {
	
	main instance = main.instance;
	
	FileConfiguration config;
	
	public TimeConfig() {
		super();
		
		config = super.loadConfig(instance.getDataFolder(), "TimeConfig.yml");
	}
	
	public TimeSystem getTimeSystem(String timeSystem){
		
		long ticksPerSecond 			= 0;          				long secondZero		= 0;
		long secondsPerMinute			= 0;						long minuteZero		= 0;
		long minutesPerHour				= 0;   						long hourZero		= 0;
		long hoursPerDay				= 0;						long dayZero		= 0;
		long daysPerWeek 				= 0;						long weekZero		= 0;
		ArrayList<Long> daysPerMonth	= new ArrayList<Long>();	long monthZero		= 0;
		long monthsPerYear				= 0;						long yearZero		= 0;
		ArrayList<Long> erasBegin		= new ArrayList<Long>();	long eraZero		= 0;
		ArrayList<Long> erasEnd			= new ArrayList<Long>();
		
		return null;
	}

	@Override
	public String getString(String path) {
		return ChatColor.translateAlternateColorCodes('&', config.getString(path));
	}

	@Override
	public Integer getInteger(String path) {
		return config.getInt(path);
	}

	@Override
	public Long getLong(String path) {
		return Long.valueOf(config.getString(path));
	}

	@Override
	public Boolean getBoolean(String path) {
		return config.getBoolean(path);
	}

	@Override
	public List<String> getListString(String path) {
		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) config.getList(path);
		
		for(int index = 0; index < list.size(); index++){
			list.set(index, ChatColor.translateAlternateColorCodes('&', list.get(index)));
		}
		
		return (List<String>) list;
	}

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
