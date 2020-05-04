package me.specifies.PlayerStats.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.specifies.PlayerStats.Main;
import me.specifies.PlayerStats.Inventories.StatFactory;
import me.specifies.PlayerStats.PlayerData.GamePlayer;

public class StatCommand implements CommandExecutor {
	
	
	private Main plugin = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length == 0) {
				// Stats of the executing player
				
				if(p.hasPermission("playerstatistics.stats.self") || p.hasPermission("playerstatistics.stats.*")) {
					new GamePlayer(p.getName()).update(p); // update the stats
					StatFactory factory = new StatFactory(54, p.getName());
					
					factory.open();
				} else p.sendMessage(plugin.color("&cYou do not have permission to check statistics of yourself."));
				
			} else {
				
				if(p.hasPermission("playerstatistics.stats.others") || p.hasPermission("playerstatistics.stats.*")) {
					
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
