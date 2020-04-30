package me.specifies.core.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.specifies.core.PlayerData.GamePlayer;

public class SaveStats implements Listener {
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		handle(e.getPlayer());
	}
	
	@EventHandler
	public void kick(PlayerKickEvent e) {
		handle(e.getPlayer());
	}
	
	private void handle(Player p) {
		
		GamePlayer player = new GamePlayer(p.getName());
		
		player.update(p);
		
	}
	
	

}
