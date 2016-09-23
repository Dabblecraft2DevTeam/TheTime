/**
 * 
 * TheTime
 * 
 * @author Joshua Wirtz
 * 
 */

package TheTime.backend.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import TheTime.backend.configs.TimeConfig;
import TheTime.backend.date.DateCalculator;
import TheTime.backend.date.DateUtils;
import TheTime.backend.date.TimeSystem;
import TheTime.backend.date.TimeSystemUtils;
import TheTime.frontend.configs.CalendarConfig;
import TheTime.frontend.configs.CommandConfig;
import TheTime.frontend.gui.Calendar;
import TheTime.frontend.gui.Storage;
import TheTime.frontend.gui.StorageUtils;
import TheTime.frontend.listener.command.CommandCaller;
import TheTime.frontend.listener.inventory.InventoryCaller;

public class main extends JavaPlugin {
	
	public static main instance;
	public static HashMap<Player, Storage> storages = new HashMap<Player, Storage>();

	/*
	 * Plugin Tag for Command output.
	 */
	public static String tag = "§r{§4§lTheTime§r} ";
	
	private static DateCalculator dateCalculator;
	private static DateUtils dateUtils;
	private static TimeSystemUtils timeSystemUtils;
	
	private static StorageUtils storageUtils;
	
	private static TimeConfig timeConfig;
	private static CalendarConfig calendarConfig;
	private static CommandConfig commandConfig;
	
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
		timeSystemUtils = new TimeSystemUtils();
		
		storageUtils = new StorageUtils();
		
		timeConfig = new TimeConfig();
		calendarConfig = new CalendarConfig();
		commandConfig = new CommandConfig();
	}
	
	/*
	 * Method to register all Commands.
	 */
	private void registerCommands() {
		
		CommandCaller commandCaller = new CommandCaller();
		
		getCommand("calendar").setExecutor(commandCaller);
		getCommand("date").setExecutor(commandCaller);
		
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
	
	public static TimeSystemUtils getTimeSystemUtils() {
		return timeSystemUtils;
	}
	
	public static StorageUtils getStorageUtils() {
		return storageUtils;
	}
	
	public static TimeConfig getTimeConfig(){
		return timeConfig;
	}
	
	public static CalendarConfig getCalendarConfig() {
		return calendarConfig;
	}
	
	public static CommandConfig getCommandConfig() {
		return commandConfig;
	}
	
}
