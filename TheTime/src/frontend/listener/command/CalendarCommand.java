package frontend.listener.command;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import backend.date.Date;
import backend.date.TimeSystem;
import backend.main.main;
import frontend.inventory.Calendar;

public class CalendarCommand {
	
	public CalendarCommand(CommandSender sender, Command command, String label, String[] args){
		
		/*
		 * Checks if the CommandSender 'sender' is a instance of the Player class.
		 */
		if(sender instanceof Player){
		Player player = (Player) sender;
		HashMap<String, TimeSystem> timeSystems = main.getTimeConfig().getTimeSystems();
		
			/*
			 * Checks if the length of the command arguments equals 0.
			 */
			if(args.length == 0){
				
				/*
				 * Opens a new calendar inventory, with the default TimeSystem, for the sender.
				 */
					/*
					 * Checks if the given TimeSystem name exists.
					 */
					if(timeSystems.containsKey("default")){
						/*
						 * Gets given TimeSystem from the name and the current Date.
						 */
						TimeSystem timeSystem = timeSystems.get("default");
						Date date = main.getDateCalculator().calculateDate(player.getWorld().getFullTime(), timeSystem);
						
						/*
						 * Opens the calendar.
						 */
						player.openInventory(new Calendar(date, player).getInventory());
						main.storages.get(player).setCalendarDate(new Date(date));
					}
				
			}else
			
			if(args.length == 1){
				
				/*
				 * Opens a new calendar inventory, with a given TimeSystem name, for the sender.
				 */
				String timeSystemName = args[0];
						
						/*
						 * Checks if the given TimeSystem name exists.
						 */
						if(timeSystems.containsKey(timeSystemName)){
							/*
							 * Gets given TimeSystem from the name and the current Date.
							 */
							TimeSystem timeSystem = timeSystems.get(timeSystemName);
							Date date = main.getDateCalculator().calculateDate(player.getWorld().getFullTime(), timeSystem);
							
							/*
							 * Opens the calendar.
							 */
							player.openInventory(new Calendar(date, player).getInventory());
							
						}
				
			}
			
		}else{
			// Not a player
		}
		
	}

}
