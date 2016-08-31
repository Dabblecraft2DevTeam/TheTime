package backend.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import backend.configs.TimeConfig;
import backend.date.DateCalculator;
import backend.date.DateUtils;
import frontend.configs.CalendarConfig;
import frontend.inventory.Calendar;
import frontend.inventory.Storage;
import frontend.listener.command.CommandCaller;
import frontend.listener.inventory.InventoryCaller;

public class main extends JavaPlugin {
	
	public static main instance;
	public static HashMap<Player, Storage> storages = new HashMap<Player, Storage>();

	private static DateCalculator dateCalculator;
	private static DateUtils dateUtils;
	
	private static TimeConfig timeConfig;
	private static CalendarConfig calendarConfig;
	
	/*
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 * 
	 * Is called when the plug-in get enabled.
	 */
	public void onEnable() {
		
		instance = this;
		
			registerObjects();
			registerCommands();
			registerEvents();
	}
	
	
	/*
	 * Method to register all Objects.
	 */
	private void registerObjects() {
		
		dateCalculator = new DateCalculator();
		dateUtils = new DateUtils();
		
		timeConfig = new TimeConfig();
		calendarConfig = new CalendarConfig();
	}
	
	/*
	 * Method to register all Commands.
	 */
	private void registerCommands() {
		
		CommandCaller commandCaller = new CommandCaller();
		
		getCommand("calendar").setExecutor(commandCaller);
		getCommand("TheTime").setExecutor(commandCaller);
		
	}
	
	/*
	 * Method to register all Events.
	 */
	private void registerEvents() {
		
		Bukkit.getPluginManager().registerEvents(new InventoryCaller(), this);
		
	}
	
	/*
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 * 
	 * Is called when the plugin get disabled.
	 */
	public void onDisable() {
		
	}
	
	public static DateCalculator getDateCalculator(){
		return dateCalculator;
	}
	
	public static DateUtils getDateUtils(){
		return dateUtils;
	}
	
	public static TimeConfig getTimeConfig(){
		return timeConfig;
	}
	
	public static CalendarConfig getCalendarConfig() {
		return calendarConfig;
	}
	
}
