package frontend.inventory;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import backend.date.Date;
import backend.item.Items;

public class Storage {
	
	Player holder;
	
	Date calendarDate;
	Inventory calendarInventory;
	HashMap<Items, Object> calendarItems = new HashMap<Items, Object>();
	
	public Player getHolder() {
		return holder;
	}

	public void setHolder(Player holder) {
		this.holder = holder;
	}

	public Date getCalendarDate() {
		return calendarDate;
	}

	public void setCalendarDate(Date calendarDate) {
		this.calendarDate = calendarDate;
	}

	public Inventory getCalendarInventory() {
		return calendarInventory;
	}

	public void setCalendarInventory(Inventory calendarInventory) {
		this.calendarInventory = calendarInventory;
	}

	public HashMap<Items, Object> getCalendarItems() {
		return calendarItems;
	}

	public void setCalendarItems(HashMap<Items, Object> calendarItems) {
		this.calendarItems = calendarItems;
	}

	public boolean allNull() {
		return 	   
				   calendarInventory 	== null
				&& calendarItems 		== null;
	}

}
