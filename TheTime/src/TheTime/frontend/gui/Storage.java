package TheTime.frontend.gui;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import TheTime.backend.date.Date;
import TheTime.backend.item.Items;

public class Storage {
	
	Player storageHolder;
	
	Calendar calendar;
	
	public Player getHolder() {
		return storageHolder;
	}
	public void setStorageHolder(Player holder) {
		this.storageHolder = holder;
	}
	

	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar){
		this.calendar = calendar;
	}

	public boolean allNull() {
		return 	   
				   calendar == null;
	}

}
