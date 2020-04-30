package me.specifies.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.specifies.core.Commands.ExperienceCommand;
import me.specifies.core.Commands.ReturnXPByLevel;
import me.specifies.core.Commands.StatCommand;
import me.specifies.core.Events.BlockHandler;
import me.specifies.core.Events.InventoryHandlers;
import me.specifies.core.Events.NewPlayerInstance;
import me.specifies.core.Events.SaveStats;
import me.specifies.core.Proxy.DataServer;

public class PlayerStats extends JavaPlugin {
	
	
	private File dataDirectory = new File(this.getDataFolder() + "\\data");
	private ArrayList<String> achievements = new ArrayList<>(Arrays.asList("10-kills-m", "20-kills-m"));
	private DataServer proxy = null;
	private static PlayerStats instance;
	
	public void onEnable() {
		
		instance = this;
		// Pull default configuration
		this.saveDefaultConfig();
		
		// Check if the data folder exists
		try { if(!dataDirectory.exists()) dataDirectory.mkdirs(); } catch(Exception err) { err.printStackTrace(); }
		
		
		// Check achievement configuration
		grabAchievementConfig();
		
		if(this.config().getBoolean("proxy")) {
			
			this.proxy = new DataServer(this.config().getInt("port"), this.getServer().getPort());
			
		}
	
		// Register events and commands
		registerCommands();
		registerEvents();
	}
	
	public void onDisable() {
		instance = null;
		try { this.proxy.stopProxy(); } catch(Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * @return this.getConfig(); An instance of the configuration.
	 */
	public FileConfiguration config() {
		return this.getConfig();
	}
	
	public ArrayList<String> getAchievements() {
		return achievements;
	}

	
	
	private void grabAchievementConfig() {
		
		for(String s : this.config().getStringList("disabled-achievements")) {
			
			if(achievements.contains(s.toLowerCase())) {
				achievements.remove(s);
			}
			
		}
		
	}
	
	public static PlayerStats getInstance() {
		return instance;
	}
	
	public String color(String m) {
		return ChatColor.translateAlternateColorCodes('&', m);
	}
	
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new NewPlayerInstance(), this);
		pm.registerEvents(new InventoryHandlers(), this);
		pm.registerEvents(new BlockHandler(), this);
		pm.registerEvents(new SaveStats(), this);
	}
	
	private void registerCommands() {
		
		getCommand("stats").setExecutor(new StatCommand());
		getCommand("xp").setExecutor(new ExperienceCommand());
		getCommand("xpbylevel").setExecutor(new ReturnXPByLevel());
		
	}
	
	

}
