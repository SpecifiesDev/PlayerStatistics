package me.specifies.core.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import me.specifies.core.PlayerStats;
import me.specifies.core.PlayerData.GamePlayer;

public class StatFactory {
	
	private final int slots;
	private final String player;
	private Inventory inv;
	private final GamePlayer stats;
	
	public StatFactory(int slots, String player) {
		this.slots = slots;
		this.player = player;
		this.stats = new GamePlayer(this.player);
		this.inv = this.createInventory();
	}
	
	// should only be used in the event that the player is checking their own stats
	public void open() {
		Bukkit.getPlayer(this.player).openInventory(this.inv);
	}
	
	public Inventory getInventory() {
		return this.inv;
	}
	
	
	private Inventory createInventory() {
		Inventory inv = Bukkit.createInventory(null, this.slots, PlayerStats.getInstance().color("&7Stats &8- &c" + this.stats.getPlayer().getName()));
		
		int kills = stats.getPlayerKills();
		int mobKills = stats.getMobKills();
		int brokenBlocks = stats.getByIntegerSpecificity("Broken Blocks");
		int placedBlocks = stats.getByIntegerSpecificity("Placed Blocks");
		int walkedBlocks = stats.getMetersWalked();
		int jumps = stats.getTimesJumped();
		int blocksFlown = stats.getMetersFlown();
		int deaths = stats.getDeaths();
		int boatTraveled = stats.getMetersBoated();
		
		ItemFactory factory = new ItemFactory(Material.DIAMOND, 1);
		
		factory.setDisplayName("&a&lOverall Statistics");
		factory.setLore("&cExp&7: &b" + (int) stats.getXP() + "~&cMinutes Played&7: &b" + Integer.toString(stats.getTimePlayed() / 60));
		
		inv.setItem(4, factory.getItem());
		
		ItemFactory skullFactory = new ItemFactory(Material.WITHER_SKELETON_SKULL, 1);
		
		
		
		skullFactory.setType(Material.WITHER_SKELETON_SKULL);
		skullFactory.setDisplayName("&c&lMob Kills");
		skullFactory.setLore("&cValue&7: &b" + Integer.toString(mobKills));
		
		inv.setItem(9, skullFactory.getItem());
		
		factory.setType(Material.DIAMOND_SWORD);
		factory.setDisplayName("&c&lPlayer Kills");
		factory.setLore("&cValue&7: &b" + Integer.toString(kills));
		factory.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
		
		inv.setItem(11, factory.getItem());
		
		factory.flush(Material.STONE, 1);
		factory.setDisplayName("&c&lBlock Stats");
		factory.setLore("&cBroken Blocks&7: &b" + Integer.toString(brokenBlocks) + "~&cPlaced Blocks&7: &b" + Integer.toString(placedBlocks));
		
		inv.setItem(13, factory.getItem());
		
		factory.setType(Material.FEATHER);
		factory.setDisplayName("&c&lMovement Stats");
		factory.setLore("&cMeters Walked&7: &b" + Integer.toString(walkedBlocks) + "~&cTimes Jumped&7: &b" + Integer.toString(jumps) + "~&cMeters Flown&7: &b" + Integer.toString(blocksFlown));
		
		inv.setItem(15, factory.getItem());
		
		skullFactory.setType(Material.SKELETON_SKULL);
		skullFactory.setDisplayName("&c&lDeaths");
		skullFactory.setLore("&cValue&7: &b" + Integer.toString(deaths));
		
		inv.setItem(17, skullFactory.getItem());
		
		factory.setType(Material.OAK_BOAT);
		factory.setDisplayName("&c&lMeters Traveled in Boat");
		factory.setLore("&cValue&7: &b" + Integer.toString(boatTraveled));
		
		inv.setItem(27, factory.getItem());
		
		factory.setType(Material.DIAMOND_ORE);
		factory.setDisplayName("&c&lOre Stats");
		factory.setLore("&cCoal Broken&7: &b" + Integer.toString(stats.getByIntegerSpecificity("Coal Broken")) + "~&cIron Broken&7: &b" + Integer.toString(stats.getByIntegerSpecificity("Iron Broken")) + "~&cLapis Broken&7: &b" + Integer.toString(stats.getByIntegerSpecificity("Lapis Broken")) + "~&cRedstone Broken&7: &b" + Integer.toString(stats.getByIntegerSpecificity("Redstone Broken")) + "~&cGold Broken&7: &b" + Integer.toString(stats.getByIntegerSpecificity("Gold Broken")) + "~&cDiamond Broken&7: &b" + Integer.toString(stats.getByIntegerSpecificity("Diamond Broken")) + "~&cEmerald Broken&7: &b" + Integer.toString(stats.getByIntegerSpecificity("Emerald Broken")));
		
		inv.setItem(29, factory.getItem());
		return inv;
	}

}
