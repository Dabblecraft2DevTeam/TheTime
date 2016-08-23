package frontend.configs;

import org.bukkit.configuration.file.FileConfiguration;

import backend.configs.Config;
import backend.main.main;

public class CalendarConfig extends Config {
	
	main instance = main.instance;
	
	FileConfiguration config;
	
	
	public CalendarConfig(){
		super();
		
		config = super.loadConfig(instance.getDataFolder(), "CalendarConfig.yml");
	}

}
