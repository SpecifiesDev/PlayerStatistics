package me.specifies.PlayerStats.Events;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import me.specifies.PlayerStats.Inventories.ScrollingInventory;

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
	
	
	@EventHandler(ignoreCancelled = true)
	public void handleTraversal(InventoryClickEvent e) {
		if(!(e.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) e.getWhoClicked();
		
		if(!ScrollingInventory.users.containsKey(p.getUniqueId())) return;
		
		ScrollingInventory state = ScrollingInventory.users.get(p.getUniqueId());
		
		
		if(e.getCurrentItem() == null) return;
		
		ItemStack item = e.getCurrentItem();
		
		if(ChatColor.stripColor(item.getItemMeta().getDisplayName()).equalsIgnoreCase("next page")) {
			
			e.setCancelled(true);
			if(state.currentPage >= state.pages.size() - 1) return;
			
			state.currentPage++;
			
			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 10, 29);
			
			p.openInventory(state.pages.get(state.currentPage));
			
		} else if(ChatColor.stripColor(item.getItemMeta().getDisplayName()).equalsIgnoreCase("previous page")) {
			
			e.setCancelled(true);
			
			if(state.currentPage > 0) {
				state.currentPage--;
				p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 10, 29);
				p.openInventory(state.pages.get(state.currentPage));
			}
		} else e.setCancelled(true);
	}

}