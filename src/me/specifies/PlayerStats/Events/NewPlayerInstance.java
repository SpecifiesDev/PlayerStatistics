package me.specifies.PlayerStats.Events;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.specifies.PlayerStats.PlayerData.GamePlayer;

public class NewPlayerInstance implements Listener {
	

	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		
		GamePlayer p = new GamePlayer(e.getPlayer().getName());
		
		try { p.checkIfExists(); } catch(IOException err) { err.printStackTrace(); }
		
		
	}
	

}
