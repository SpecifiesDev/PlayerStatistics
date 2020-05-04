package me.specifies.PlayerStats.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.specifies.PlayerStats.Main;

public class ReturnXPByLevel implements CommandExecutor {
	
	private Main plugin = Main.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(args.length == 0) {
				p.sendMessage(plugin.color("&cInvalid arguments. Usage&7: &c/xpbylevel <level>"));
			} else {
				
				try {
					int convert = Integer.parseInt(args[0]);
					p.sendMessage(plugin.color("&cThe required xp for level &6" + args[0] + " &cis &b" + Integer.toString((int) this.calcXP(Integer.parseInt(args[0]))) + " &cpoints&7."));
					
				} catch(NumberFormatException e) {
					p.sendMessage(plugin.color("&cThe inputted parameters were incorrect."));
				}
				
			}
			
		} else sender.sendMessage(plugin.color("&cYou must be a player to use this command"));
		
		return true;
	}
	
	private double calcXP(int level) {
		return (level / .07) * (level / .07);
	}

}
