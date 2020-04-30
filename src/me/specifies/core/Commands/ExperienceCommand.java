package me.specifies.core.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.specifies.core.PlayerStats;
import me.specifies.core.Chat.ProgressionBar;
import me.specifies.core.PlayerData.GamePlayer;
import net.md_5.bungee.api.ChatColor;

public class ExperienceCommand implements CommandExecutor {
	
	private PlayerStats plugin = PlayerStats.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(args.length == 0) {
				
				if(p.hasPermission("playerstatistics.xp.self")) {
					
					GamePlayer player = new GamePlayer(p.getName());
					
					player.update(p);
					
					int level = player.getLevel();
					int xp = (int) player.getXP();
					
					System.out.println(xp + "\n" + player.calculateEndOfLevelXP(level + 1));

					
					p.sendMessage(plugin.color("&8&m---------------&7[ &bLevel &6" + Integer.toString(level) + " &7]&8&m---------------"));
					p.sendMessage("");
					p.sendMessage(plugin.color("&c&l" + Integer.toString(xp) + "&7/&c&l" + Integer.toString((int) player.calculateEndOfLevelXP(level + 1)) + " &8&l[" + new ProgressionBar().getProgressBar(xp, (int) player.calculateEndOfLevelXP(level + 1), 42, '|', ChatColor.AQUA, ChatColor.GRAY) + "&8&l]"));
					p.sendMessage("");
					p.sendMessage(plugin.color("&8&m---------------------------------------"));
				} else p.sendMessage(plugin.color("&cYou do not have permission to check your experience."));
				
			} else {
				
				if(p.hasPermission("playerstatistics.xp.others")) {
					
					GamePlayer player = new GamePlayer(args[0]);
					
					if(!player.exists()) {
						p.sendMessage(plugin.color("&cThis player does not exist."));
					} else {
						player.update(p);
						
						int level = player.getLevel();
						int xp = (int) player.getXP();
						
						System.out.println(xp + "\n" + player.calculateEndOfLevelXP(level + 1));

						
						p.sendMessage(plugin.color("&8&m-------------&7[ &bLevel &6" + Integer.toString(level) +  " &a" + args[0] + " &7]&8&m------------"));
						p.sendMessage("");
						p.sendMessage(plugin.color("&c&l" + Integer.toString(xp) + "&7/&c&l" + Integer.toString((int) player.calculateEndOfLevelXP(level + 1)) + " &8&l[" + new ProgressionBar().getProgressBar(xp, (int) player.calculateEndOfLevelXP(level + 1), 42, '|', ChatColor.AQUA, ChatColor.GRAY) + "&8&l]"));
						p.sendMessage("");
						p.sendMessage(plugin.color("&8&m---------------------------------------"));						
					}
					

					
				} else p.sendMessage(plugin.color("&cYou do not have permission to check the experience of other players."));
				
			}
			
			
		} else sender.sendMessage(plugin.color("&cYou must be a player to use this command."));
		
		return true;
	}

}
