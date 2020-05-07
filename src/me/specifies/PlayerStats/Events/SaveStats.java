package me.specifies.PlayerStats.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.specifies.PlayerStats.Inventories.ScrollingInventory;
import me.specifies.PlayerStats.PlayerData.GamePlayer;

public class SaveStats implements Listener {
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		handle(e.getPlayer());
		
		if(ScrollingInventory.users.containsKey(e.getPlayer().getUniqueId())) ScrollingInventory.users.remove(e.getPlayer().getUniqueId());
	}
	
	@EventHandler
	public void kick(PlayerKickEvent e) {
		handle(e.getPlayer());
		
		if(ScrollingInventory.users.containsKey(e.getPlayer().getUniqueId())) ScrollingInventory.users.remove(e.getPlayer().getUniqueId());
	}
	
	private void handle(Player p) {
		
		GamePlayer player = new GamePlayer(p.getName());
		
		player.update(p);
		
	}
	
	

}

