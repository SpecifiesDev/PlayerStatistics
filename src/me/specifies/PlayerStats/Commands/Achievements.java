package me.specifies.PlayerStats.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.specifies.PlayerStats.Main;

public class Achievements implements CommandExecutor {
	
	Main plugin;
	public Achievements() { this.plugin = Main.getInstance(); }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(p.hasPermission("playerstatistics.achievements")) {
				
				
				
				
			} else p.sendMessage(plugin.color("&cYou do not have permission to check achievements."));
			
		} else sender.sendMessage(plugin.color("&cYou must be a player to use this command."));
		
		return true;
	}

}
