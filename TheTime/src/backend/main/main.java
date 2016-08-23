package backend.main;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	
	public static main instance;

	/*
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 * 
	 * Is called when the plugin get enabled.
	 */
	public void onEnable() {
		
		instance = this;
	}
	
	/*
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 * 
	 * Is called when the plugin get disabled.
	 */
	public void onDisable() {
		
	}
	
}
