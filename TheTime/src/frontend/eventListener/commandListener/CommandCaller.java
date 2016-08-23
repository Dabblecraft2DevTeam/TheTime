package frontend.eventListener.commandListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandCaller implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		/*
		 * Creates a new CalendarCommand Object if the command equals 'calendar'.
		 */
		if(command.getName().equalsIgnoreCase("calendar")){
			new CalendarCommand();
		}
		
		/*
		 * Creates a new TheTimeCommand Object if the command equals 'TheTime'.
		 */
		if(command.getName().equalsIgnoreCase("TheTime")){
			new TheTimeCommand();
		}
		
		return true;
	}

}
