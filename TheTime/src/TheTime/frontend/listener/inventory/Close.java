package TheTime.frontend.listener.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import TheTime.backend.main.main;
import TheTime.frontend.gui.Storage;

public class Close {
	
	public Close(InventoryCloseEvent event){
		
		Inventory inventory = event.getInventory();
		
		if(event.getPlayer() instanceof Player){
			Player player = (Player) event.getPlayer();
			Storage storage = main.storages.get(player);
			
			/*
			 * Checks if the Storage of the player isn't null, to prevent NullPointerExeptions.
			 */
			if(storage != null){
				
				/*
				 * Checks if the closed Inventory is the calendar inventory. If so it deletes it out of the Storage.
				 */
				if(inventory.equals(storage.getCalendar().getInventory())){
					storage.setCalendar(null);;
				}
				
				if(storage.allNull()){
					main.storages.remove(player);
				}
			}
			
		}
		
	}

}
