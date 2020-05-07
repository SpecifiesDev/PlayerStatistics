package me.specifies.PlayerStats.Commands;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.specifies.PlayerStats.Main;
import me.specifies.PlayerStats.Inventories.ItemFactory;
import me.specifies.PlayerStats.Inventories.ScrollingInventory;

public class ReturnXPByLevel implements CommandExecutor {
	
	private Main plugin = Main.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(p.hasPermission("playerstatistics.levels")) {
				int maxLevel = plugin.getMaxLevel();
				
				ArrayList<ItemStack> items = new ArrayList<ItemStack>();
				
				for(int i = 1; i <= maxLevel; i++) {
					int xp = (int) this.calcXP(i);
					
					ItemStack item = new ItemStack(Material.PAPER, 1);
					
					ItemMeta im = item.getItemMeta();
					
					im.setDisplayName(plugin.color("&bLevel &a" + i + " &7- &b" + NumberFormat.getInstance(Locale.US).format(xp).replaceAll(",", "&8,&b")));
					
					item.setItemMeta(im);
					
					items.add(item);
					
				}
				
				new ScrollingInventory(items, "&cLevels", p);
			} else p.sendMessage(plugin.color("&cYou do not have permission to use this command."));
			
			
			
		} else sender.sendMessage(plugin.color("&cYou must be a player to use this command"));
		
		return true;
	}
	
	private double calcXP(int level) {
		return (level / .07) * (level / .07);
	}

}
