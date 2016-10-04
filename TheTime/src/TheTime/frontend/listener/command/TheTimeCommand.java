package TheTime.frontend.listener.command;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import TheTime.backend.main.main;
import TheTime.frontend.configs.CommandErrors;

public class TheTimeCommand {
	
	HashMap<CommandErrors, String> errors = main.getCommandConfig().getErrors();
	
	public TheTimeCommand(CommandSender sender, Command command, String label, String[] args){
		
		if(args.length == 0){
			
			Plugin TheTime = main.instance;
			String ver = TheTime.getDescription().getVersion();
			String author = TheTime.getDescription().getAuthors().get(0);
			String web = TheTime.getDescription().getWebsite();
			sender.sendMessage("");
			sender.sendMessage("                 " +main.tag + "");
			sender.sendMessage("                 §1§oVersion: §f"+ ver +"");
			sender.sendMessage("          §2§oAuthor: §6§o"+author+"§f");
			sender.sendMessage("");
			sender.sendMessage(" §o"+web+"");
			sender.sendMessage("");
			
		}else
		
		// Checks if there is one argument
		if(args.length == 1){
			
			// Checks if the Command argument is 'reload'.
			if(args[0].equalsIgnoreCase("reload")){
				// Checks if the sender has enough permissions to execute a reload.
				if(sender.hasPermission("TheTime.reload")){
					// Reloads the configs.
					main.getTimeConfig().reloadConfig();
					main.getCalendarConfig().reloadConfig();
					main.getCommandConfig().reloadConfig();
				}else{
					// No enough permissions
					sender.sendMessage(errors.get(CommandErrors.noPermissions));
				}
			}else{
				// Unknown Command.
				sender.sendMessage(errors.get(CommandErrors.unkownCommand));
			}
			
		}else{
			// Unknown command / to much args.
			sender.sendMessage(errors.get(CommandErrors.unkownCommand));
		}
		
	}

}
