package me.specifies.PlayerStats.Events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

/*
 * This class will filter all the inventory click events.
 * Since there's really nothing to be clickable, we just need to check the inventory name by splitting certain data
 * and if it's one of our inventories, cancel it.
 */

public class InventoryHandlers implements Listener {
	
	@EventHandler
	public void click(InventoryClickEvent e) {
		
		InventoryView view = e.getView();
		
		
		if(ChatColor.stripColor(view.getTitle()).split(" - ")[0].equals("Stats")) {
			e.setCancelled(true);
		}
		
	}

}