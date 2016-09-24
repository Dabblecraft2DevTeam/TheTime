package TheTime.frontend.gui;

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

import TheTime.backend.date.Date;
import TheTime.backend.date.DateEnum;
import TheTime.backend.date.TimeSystem;
import TheTime.backend.date.TimeSystemUtils;
import TheTime.backend.item.ItemCreator;
import TheTime.backend.item.ItemProperties;
import TheTime.backend.item.Items;
import TheTime.backend.main.main;
import TheTime.frontend.configs.CalendarConfig;

public class Calendar {
	
	TheTime.backend.date.DateUtils dateUtils = main.getDateUtils();
	
	CalendarConfig calendarConfig = main.getCalendarConfig();
	
	Date date;
	Date creationDate;
	
	Inventory inventory;
	HashMap<Items, Object> items = new HashMap<Items, Object>();
	
	public Calendar(Date date, Date creationDate){
		
		date = new Date(date);
		creationDate = new Date(creationDate);
		
		
		inventory = createInventory(date, creationDate);
		
	}
	
	/*
	 * Getters for the calendar parameters
	 */
	public Date getDate() {
		return date;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public HashMap<Items, Object> getItems() {
		return items;
	}
	
	
	/*
	 * Method to create the inventory of the calendar
	 */
	private Inventory createInventory(Date date, Date creationDate){
		/*
		 * Create of a new date and timeSystem instance.
		 */
		date = new Date(date);
		TimeSystem timeSystem = new TimeSystem(date.getTimeSystem());
		
		creationDate = new Date(creationDate);
		
		// Gets the properties of the calendar.
		HashMap<InventoryProperties, Object> calendarProperties = calendarConfig.getCalendarProperties();
		
		/*
		 * Declares Lists for the item storaging.
		 */
		ArrayList<ItemStack> dayItems = new ArrayList<ItemStack>();
		ArrayList<ItemStack> weekItems = new ArrayList<ItemStack>();
		
		/*
		 * Creates the inventory used for the calendar. The slots are calculated by getInventorySize();
		 */
		Inventory inventory = Bukkit.createInventory(null, getInventorySize(date, timeSystem, 9), replacePlaceholder((String) calendarProperties.get(InventoryProperties.HEADER), date));
		
		
		double daysPerMonth = timeSystem.getDaysPerMonth().get((int) date.getMonth());
		double firstWeekDay = dateUtils.getDayOfWeek(dateUtils.down(DateEnum.day, (int) date.getDay(), date));
		double daysPerWeek = timeSystem.getDaysPerWeek();
		 /*
		  * Checks if daysPerMonth is smaller 8, if not sets it to 8, because of the space of the inventory only 8 is permittet.
		  */
		 if(daysPerWeek > 8){
			 daysPerWeek = 8;
		 }
		double weeksPerMonth = Math.ceil(((daysPerMonth + firstWeekDay) / daysPerWeek));
		
		
		/*
		 * Declares variables for week slot and day slot. 
		 */
		int weekSlot = (int) daysPerWeek;
		int daySlot = (int) firstWeekDay;
		int dayNullSlot = daySlot;
		
		int monthDay = 0;
		
		/*
		 * Gets the properties of the items of the calendar.
		 */
		HashMap<Items, HashMap<ItemProperties, Object>> itemProperties = (HashMap<Items, HashMap<ItemProperties, Object>>) calendarProperties.get(InventoryProperties.ITEMS); 
		HashMap<ItemProperties, Object> todayItemProperties = itemProperties.get(Items.TODAY);
		HashMap<ItemProperties, Object> dayItemProperties = itemProperties.get(Items.DAY);
		HashMap<ItemProperties, Object> weekItemProperties = itemProperties.get(Items.WEEK);
		
			/*
			 * Goes threw each week of the month.
			 */
			for(int week = 0; week <= (weeksPerMonth - timeSystem.getWeekZero()); week++, weekSlot = weekSlot + 9){
				date.setWeek(week);
				
				/*
				 * Goes threw each day of the week.
				 */
				for(int day = 0; day <= (daysPerWeek - timeSystem.getDayZero()); day++, daySlot++){
					date.setDay(monthDay);
					monthDay++;
					
					if(isToday(date, creationDate)){
						
						/*
						 * Creates a today item and sets it into the calendar.
						 */
						ItemStack todayItem =  createItem(todayItemProperties, date);
							if(todayItem != null){
								inventory.setItem(daySlot, todayItem);
								items.put(Items.TODAY, todayItem);
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
					
					
						if(isEndOfWeek(date, daySlot)) {
							daySlot++;
								break;
						}
						
						if(isEndOfMonth(date)) {
							week = (int) ((weeksPerMonth - timeSystem.getWeekZero()) + 1);
							day = (int) ((daysPerWeek- timeSystem.getDayZero()) + 1);
						}

				}
				
				/*
				 * Creates a week item and sets it into the calendar.
				 */
				ItemStack weekItem = createItem(weekItemProperties, date);
					if(weekItem != null){
						inventory.setItem(weekSlot, weekItem);
						weekItems.add(weekItem);
					}
				
				daySlot = (int) (daySlot + (8 - (daysPerWeek - timeSystem.getDayZero())));
			}
			
			/*
			 * Storaging part
			 */
			items.put(Items.DAY, dayItems);
			items.put(Items.WEEK, weekItems);
			
			return inventory;
		
	}
	
	/*
	 * Method to check if the given day is the end of the week.
	 */
	private boolean isEndOfWeek(Date date, int daySlot) {
		TimeSystem timeSystem = date.getTimeSystem();
			
			if(date.getWeek() == 0){
				if(daySlot >= timeSystem.getDaysPerWeek() - 1) {
					return true;
				}
			}
			
		return false;
	}
	
	/*
	 * Method to check if the given day is the end of the month.
	 */
	private boolean isEndOfMonth(Date date){
		TimeSystem timeSystem = date.getTimeSystem();
		
		long daysPerMonth = timeSystem.getDaysPerMonth().get((int) date.getMonth());
		
			if(date.getDay() == daysPerMonth - timeSystem.getDayZero()){
				return true;
			}
		
		return false;
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
		double firstWeekDay = (double) dateUtils.getDayOfWeek(dateUtils.down(DateEnum.day, (int) date.getDay(), date));
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
		double weeksPerMonth = Math.ceil((daysPerMonth + firstWeekDay) / daysPerWeek);
		
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
		long dayOfWeek = dateUtils.getDayOfWeek(date);
		
		message = message
				.replaceAll("%dayName%", timeSystem.getDayNames().get((int) dayOfWeek))
				.replaceAll("%monthName%", timeSystem.getMonthNames().get((int) date.getMonth()))
				.replaceAll("%eraName%", timeSystem.getEraNames().get((int) date.getEra()));
		
		return message;
	}

}
