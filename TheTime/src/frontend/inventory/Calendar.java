package frontend.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.time.DateUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import backend.date.Date;
import backend.date.TimeSystem;
import backend.item.ItemCreator;
import backend.item.ItemProperties;
import backend.item.Items;
import backend.main.main;
import frontend.configs.CalendarConfig;

public class Calendar {
	
	backend.date.DateUtils dateUtils = main.getDateUtils();
	
	CalendarConfig calendarConfig = main.getCalendarConfig();
	
	Inventory inventory;
	
	public Calendar(Date date, Player player){
		/*
		 * Create of a new date and timeSystem instance.
		 */
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		Date currentDate = main.getDateCalculator().calculateDate(player.getWorld().getFullTime(), timeSystem);
		
		HashMap<InventoryProperties, Object> calendarProperties = calendarConfig.getCalendarProperties();
		
		/*
		 * Declares Lists for the item storaging.
		 */
		HashMap<Items, Object> calendarItems = new HashMap<Items, Object>();
		ArrayList<ItemStack> dayItems = new ArrayList<ItemStack>();
		ArrayList<ItemStack> weekItems = new ArrayList<ItemStack>();
		
		/*
		 * Creates the inventory used for the calendar. The slots are calculated by getInventorySize();
		 */
		inventory = Bukkit.createInventory(null, getInventorySize(date, timeSystem, 9), replacePlaceholder((String) calendarProperties.get(InventoryProperties.HEADER), date));
		
		
		double daysPerMonth = timeSystem.getDaysPerMonth().get((int) date.getMonth());
		double daysPerWeek = timeSystem.getDaysPerWeek();
		 /*
		  * Checks if daysPerMonth is smaller 8, if not sets it to 8, because of the space of the inventory only 8 is permittet.
		  */
		 if(daysPerWeek > 8){
			 daysPerWeek = 8;
		 }
		double weeksPerMonth = Math.ceil((daysPerMonth / daysPerWeek));
		
		
		/*
		 * Declares variables for week slot and day slot. 
		 */
		int weekSlot = (int) daysPerWeek;
		int daySlot = 0;
		
		int monthDay = 0;
		
		/*
		 * Gets the properties of the items of the calendar.
		 */
		HashMap<Items, HashMap<ItemProperties, Object>> items = (HashMap<Items, HashMap<ItemProperties, Object>>) calendarProperties.get(InventoryProperties.ITEMS); 
		HashMap<ItemProperties, Object> todayItemProperties = items.get(Items.TODAY);
		HashMap<ItemProperties, Object> dayItemProperties = items.get(Items.DAY);
		HashMap<ItemProperties, Object> weekItemProperties = items.get(Items.WEEK);
			/*
			 * Goes threw each week of the month.
			 */
			for(int week = 0; week <= (weeksPerMonth - timeSystem.getWeekZero()); week++){
				date.setWeek(week);
				
				/*
				 * Goes threw each day of the week.
				 */
				for(int day = 0; day <= (daysPerWeek - timeSystem.getDayZero()); day++){
					date.setDay(monthDay);
					monthDay++;
					
					if(isToday(date, currentDate)){
						/*
						 * Creates a today item and sets it into the calendar.
						 */
						ItemStack todayItem =  createItem(todayItemProperties, date);
							if(todayItem != null){
								inventory.setItem(daySlot, todayItem);
								calendarItems.put(Items.TODAY, todayItem);
								dayItems.add(todayItem);
							}
					}else{
						/*
						 * Creates a day item and sets it into the calendar
						 */
						ItemStack dayItem = createItem(dayItemProperties, date);
							if (dayItem != null) {
								inventory.setItem(daySlot, dayItem);
								dayItems.add(dayItem);
							}
					}
					
					/*
					 * Checks for the end of the month.
					 */
					if(date.getDay() == daysPerMonth){
						week = (int) ((weeksPerMonth - timeSystem.getWeekZero()) + 1);
						day = (int) ((daysPerWeek- timeSystem.getDayZero()) + 1);
					}
					
					daySlot++;
				}
				
				/*
				 * Creates a week item and sets it into the calendar.
				 */
				ItemStack weekItem = createItem(weekItemProperties, date);
					if(weekItem != null){
						inventory.setItem(weekSlot, weekItem);
						weekItems.add(weekItem);
					}
				
				
				weekSlot = weekSlot + 9;
				daySlot = (int) (daySlot + (8 - (daysPerWeek - timeSystem.getDayZero())));
			}
			
			/*
			 * Storaging part
			 */
			calendarItems.put(Items.DAY, dayItems);
			calendarItems.put(Items.WEEK, weekItems);
			storageData(player, new Date(date), inventory, calendarItems);
		
	}
	
	/*
	 * Method to get the created Inventory.
	 */
	public Inventory getInventory(){
		return inventory;
	}
	
	/*
	 * Method to storage the created Inventory and Items into the players Storage.
	 */
	private void storageData(Player player, Date date, Inventory inventory, HashMap<Items, Object> items){
		
		HashMap<Player, Storage> storages = main.storages;
		
		
		Storage storage = new Storage();
			
			if(storages.containsKey(player)){
				storage = storages.get(player);
				storages.remove(player);
			}
			
			storage.setHolder(player);
			storage.setCalendarDate(date);
			storage.setCalendarInventory(inventory);
			storage.setCalendarItems(items);
			
			storages.put(player, storage);
	}
	
	/*
	 * Method to create an item with a given ItemProperties HashMap.
	 */
	public ItemStack createItem(HashMap<ItemProperties, Object> itemProperties, Date date){
		
		String name = this.replacePlaceholder((String) itemProperties.get(ItemProperties.NAME), date);
		Material material = (Material) itemProperties.get(ItemProperties.MATERIAL);
		int id = (int) itemProperties.get(ItemProperties.ID);
		int amount = Integer.valueOf(this.replacePlaceholder((String) itemProperties.get(ItemProperties.AMOUNT), date));
		
		List<String> lore = null;
			if(itemProperties.get(ItemProperties.LORE) != null){
				lore = new ArrayList<String>((List<String>) itemProperties.get(ItemProperties.LORE));
					if(lore != null){
						for(String line : lore){
							lore.set(lore.indexOf(line), this.replacePlaceholder(line, date));
						}
					}
		}
			
		if((boolean) itemProperties.get(ItemProperties.TOGGLE)){
			return new ItemCreator(material, amount,(short) id, name, lore).getItem();
		}else{
			return null;
		}
	}
	
	/*
	 * Method to check if a customDate is today or not.
	 * Needs a customDate and the current date.
	 */
	private boolean isToday(Date date, Date currentDate){
		
		if(date.getYear() == currentDate.getYear()){
			if(date.getMonth() == currentDate.getMonth()){
				if(date.getDay() == currentDate.getDay()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*
	 * Method to calculate the inventory size for a given Date and TimeSystem.
	 */
	private int getInventorySize(Date date, TimeSystem timeSystem, int minSize){
		date = new Date(date);
		timeSystem = new TimeSystem(timeSystem);
		
		int slots = 0;
		
		double daysPerMonth = timeSystem.getDaysPerMonth().get((int) date.getMonth());
		double daysPerWeek = timeSystem.getDaysPerWeek();
		 /*
		  * Checks if daysPerMonth is smaller 8, if not sets it to 8, because of the space of the inventory only 8 is permittet.
		  */
		 if(daysPerWeek > 8){
			 daysPerWeek = 8;
		 }
		 
		/*
		 * Calculates the weeks this month has.
		 */
		double weeksPerMonth = Math.ceil(daysPerMonth / daysPerWeek);
		
			/*
			 * Goes threw every week of the month and adds 9 slots for each week, to make the inventory big enough.
			 */
			for(int week = 1; week <= weeksPerMonth; week++){
				slots = slots + 9;
			}
			
				/*
				 * Checks if the slots are bigger then the minimum size, if not sets it to the minimum.
				 */
				if(slots < minSize){
					slots = minSize;
				}
		
		/*
		 * Returns the calculated slots for the inventory.
		 */
		return slots;
	}
	
	/*
	 * Method to replace the Date placeholder of a String.
	 */
	private String replacePlaceholder(String message, Date date){
		message = new String(message);
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		date = dateUtils.addZero(date);
		
		/*
		 * Replaces date number placeholder.
		 */
		message = message
				.replaceAll("%tick%", String.valueOf(date.getTick()))
				.replaceAll("%second%", String.valueOf(date.getSecond()))
				.replaceAll("%minute%", String.valueOf(date.getMinute()))
				.replaceAll("%hour%", String.valueOf(date.getHour()))
				.replaceAll("%day%", String.valueOf(date.getDay()))
				.replaceAll("%week%", String.valueOf(date.getWeek()))
				.replaceAll("%month%", String.valueOf(date.getMonth()))
				.replaceAll("%year%", String.valueOf(date.getYear()))
				.replaceAll("%era%", String.valueOf(date.getEra()));
		
		/*
		 * Replaces date text placeholder.
		 */
		date = dateUtils.removeZero(date);
		
		long weekDay = date.getDay() - (date.getWeek() * timeSystem.getDaysPerWeek());
		
		message = message
				.replaceAll("%dayName%", timeSystem.getDayNames().get((int) weekDay))
				.replaceAll("%monthName%", timeSystem.getMonthNames().get((int) date.getMonth()))
				.replaceAll("%eraName%", timeSystem.getEraNames().get((int) date.getEra()));
		
		return message;
	}

}
