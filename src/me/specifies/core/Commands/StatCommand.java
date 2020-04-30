package me.specifies.core.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.specifies.core.PlayerStats;
import me.specifies.core.Inventories.StatFactory;
import me.specifies.core.PlayerData.GamePlayer;

public class StatCommand implements CommandExecutor {
	
	
	private PlayerStats plugin = PlayerStats.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length == 0) {
				// Stats of the executing player
				
				if(p.hasPermission("playerstats.stats.self") || p.hasPermission("playerstats.stats.*")) {
					new GamePlayer(p.getName()).update(p); // update the stats
					StatFactory factory = new StatFactory(54, p.getName());
					
					factory.open();
				} else p.sendMessage(plugin.color("&cYou do not have permission to check statistics of yourself."));
				
			} else {
				
				if(p.hasPermission("playerstats.stats.others") || p.hasPermission("playerstats.stats.*")) {
					
					String target = args[0];
					GamePlayer instanceOfPlayer = new GamePlayer(target);
					
					if(instanceOfPlayer.exists()) {
						if(!(Bukkit.getPlayer(target) == null)) instanceOfPlayer.update(Bukkit.getPlayer(target)); // If the player isn't offline, update their stats before the inventory is built
						StatFactory factory = new StatFactory(54, target);
						
						p.openInventory(factory.getInventory());
					} else p.sendMessage(plugin.color("&cIt would appear that the player &6" + target + " &chas no statistics."));
					
				} else p.sendMessage(plugin.color("&cYou do not have permission to check statistics of other players."));
				
				
			}
			
			
		} else sender.sendMessage(plugin.color("&cYou must be a player to use this command."));
		
		return true;
	}
	
	
	
	

}
