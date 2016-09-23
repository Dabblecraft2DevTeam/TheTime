package TheTime.frontend.listener.command;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import TheTime.backend.date.Date;
import TheTime.backend.date.TimeSystem;
import TheTime.backend.main.main;
import TheTime.frontend.configs.CommandErrors;
import TheTime.frontend.gui.Calendar;
import TheTime.frontend.gui.StorageUtils;

public class CalendarCommand {
	
	
	StorageUtils storageUtils = main.getStorageUtils();
	
	HashMap<CommandErrors, String> errors = main.getCommandConfig().getErrors();
	
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
				 * Checks if the player has enough permissions.
				 */
				if(player.hasPermission("TheTime.calendar.default")){
					/*
					 * Opens a new calendar inventory, with the default TimeSystem, for the sender.
					 */
						/*
						 * Checks if the given TimeSystem name exists.
						 */
						if(timeSystems.containsKey("default")){
							
							 // Gets given TimeSystem from the name and the current Date.
								TimeSystem timeSystem = timeSystems.get("default");
								
								// Creates the needed date Objects
								Date date = main.getDateCalculator().dateFromTicks(player.getWorld().getFullTime(), timeSystem);
								Date creationDate = main.getDateCalculator().dateFromTicks(player.getWorld().getFullTime(), timeSystem);
								
								// Creates a new calendar instance and stores it.
								Calendar calendar = new Calendar(date, creationDate);
								storageUtils.storageCalendar(player, calendar);
								
								// Opens the calendar for the player
								player.openInventory(calendar.getInventory());
								
						
					}else{
						// Unkown TimeSystem
						player.sendMessage(errors.get(CommandErrors.unkwonTimeSystem));
					}
				}else{
					// Not enough permissions.
					player.sendMessage(errors.get(CommandErrors.noPermissions));
				}
				
			}else
			
			if(args.length == 1){
				
				/*
				 * Checks if the player has enough permissions.
				 */
				if(player.hasPermission("TheTime.calendar.TimeSystem")){
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
							Date date = main.getDateCalculator().dateFromTicks(player.getWorld().getFullTime(), timeSystem);
							Date creationDate = main.getDateCalculator().dateFromTicks(player.getWorld().getFullTime(), timeSystem);
							
							// Creates a new calendar instance and stores it.
							Calendar calendar = new Calendar(date, creationDate);
							storageUtils.storageCalendar(player, calendar);
							
							// Opens the calendar for the player
							player.openInventory(calendar.getInventory());
							
						}else{
							// Unkown TimeSystem
							player.sendMessage(errors.get(CommandErrors.unkwonTimeSystem));
						}
				}else{
					// No permissions
					player.sendMessage(errors.get(CommandErrors.noPermissions));
				}
				
			}else{
				// Unkown Command
				player.sendMessage(errors.get(CommandErrors.unkownCommand));
			}
			
		}else{
			// Not a player
			sender.sendMessage(errors.get(CommandErrors.notPlayer));
		}
		
	}

}
