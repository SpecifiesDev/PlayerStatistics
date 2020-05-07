package me.specifies.PlayerStats.PlayerData;
import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.specifies.PlayerStats.Main;

public class GamePlayer {
	
	private String player = "";
	
	private File path = null;
	private FileConfiguration playerConfig = null;
	
	private Main plugin;
	
	
	public GamePlayer(String player) {
		this.player = player;
		this.plugin = Main.getInstance();
		
		path = new File(this.plugin.getDataFolder() + File.separator + "data" + File.separator + this.getPlayer().getUniqueId() + ".yml");
		playerConfig = YamlConfiguration.loadConfiguration(path);
		
		
	}
	
	public void update(Player p) {
		try {
			
			this.setBySpecificity("Minutes Played", p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 30);
			this.setBySpecificity("Meters Boated", p.getStatistic(Statistic.BOAT_ONE_CM) / 100);
			this.setBySpecificity("Meters Flown", p.getStatistic(Statistic.FLY_ONE_CM) / 100);
			this.setBySpecificity("Kills", p.getStatistic(Statistic.PLAYER_KILLS));
			this.setBySpecificity("Mob Kills", p.getStatistic(Statistic.MOB_KILLS));
			this.setBySpecificity("Meters Walked", p.getStatistic(Statistic.WALK_ONE_CM) / 100);
			this.setBySpecificity("Times Jumped", p.getStatistic(Statistic.JUMP));
			this.setBySpecificity("Deaths", p.getStatistic(Statistic.DEATHS));
			
		} catch(Exception err) { err.printStackTrace(); }
	}
	
	public boolean exists() {
		return this.path.exists();
	}
	
	public boolean checkIfExists() throws IOException {

		if(!this.exists()) {
			playerConfig.set("Broken Blocks", 0);
			playerConfig.set("Placed Blocks", 0);
			playerConfig.set("Redstone Broken", 0);
			playerConfig.set("Iron Broken", 0);
			playerConfig.set("Gold Broken", 0);
			playerConfig.set("Coal Broken", 0);
			playerConfig.set("Diamond Broken", 0);
			playerConfig.set("Lapis Broken", 0);
			playerConfig.set("Emerald Broken", 0);
			playerConfig.set("Minutes Played", 0);
			playerConfig.set("Meters Boated", 0);
			playerConfig.set("Meters Flown", 0);
			playerConfig.set("Kills", 0);
			playerConfig.set("Mob Kills", 0);
			playerConfig.set("Meters Walked", 0);
			playerConfig.set("Times Jumped", 0);
			playerConfig.set("Deaths", 0);
			
			playerConfig.save(this.path);
		}			
		return false;
		
	} 
	
	public OfflinePlayer getPlayer() {
		return Bukkit.getOfflinePlayer(this.player);
	}
	
	public double getXP() {
		
		double xp = 0;
		
		xp += this.getMobKills() * this.plugin.config().getDouble("mob-kills-xp");
		xp += this.getPlayerKills() * this.plugin.config().getDouble("player-kills-xp");
		xp += (this.getTimePlayed() / 60) * this.plugin.config().getDouble("minutes-played-xp");
		xp += this.getMetersWalked() * this.plugin.config().getDouble("meters-walked-xp");
		xp += this.getTimesJumped() * this.plugin.config().getDouble("times-jumped-xp");
		xp += this.getByIntegerSpecificity("Coal Broken") * this.plugin.config().getDouble("coal-broken-xp");
		xp += this.getByIntegerSpecificity("Iron Broken") * this.plugin.config().getDouble("iron-broken-xp");
		xp += this.getByIntegerSpecificity("Lapis Broken") * this.plugin.config().getDouble("lapis-broken-xp");
		xp += this.getByIntegerSpecificity("Redstone Broken") * this.plugin.config().getDouble("redstone-broken-xp");
		xp += this.getByIntegerSpecificity("Gold Broken") * this.plugin.config().getDouble("gold-broken-xp");
		xp += this.getByIntegerSpecificity("Diamond Broken") * this.plugin.config().getDouble("diamond-broken-xp");
		xp += this.getByIntegerSpecificity("Emerald Broken") * this.plugin.config().getDouble("emerald-broken-xp");
		xp += this.getByIntegerSpecificity("Broken Blocks") * this.plugin.config().getDouble("blocks-broken-xp");
		xp += this.getByIntegerSpecificity("Placed Blocks") * this.plugin.getConfig().getDouble("blocks-placed-xp");
		
		
		
		return xp;
	}
	
	public int getLevel() {
		return (int) Math.floor(.07 * Math.sqrt(this.getXP()));
	}
	
	public double calculateEndOfLevelXP(int level) {
		return this.calcXPByLevel(level);
	}
	
	/**
	 * The statistics manager for this one is awfully weird. It doesn't return in a value of minutes played, but every tick 20-30 is added to the value. Dividing by 20, and then 60 appears to provide a seemingly accurate played by minute count.
	 * @return Seconds played
	 */
	public int getTimePlayed() {
		return this.getByIntegerSpecificity("Minutes Played");
	}
	
	public int getMetersBoated() {
		return this.getByIntegerSpecificity("Meters Boated");
	}
	
	public int getMetersFlown() {
		return this.getByIntegerSpecificity("Meters Flown");
	}

	public int getPlayerKills() {
		return this.getByIntegerSpecificity("Kills");
	}
	
	public int getMobKills() {
		return this.getByIntegerSpecificity("Mob Kills");
	}
	
	public int getMetersWalked() {
		return this.getByIntegerSpecificity("Meters Walked");
	}
	
	public int getTimesJumped() {
		return this.getByIntegerSpecificity("Times Jumped");
	}
	
	
	public int getDeaths() {
		return this.getByIntegerSpecificity("Deaths");
	}
	
	public int getByIntegerSpecificity(String dataType) {
		return this.playerConfig.getInt(dataType);
	}
	
	public void setBySpecificity(String dataType, int value) throws IOException {
		this.playerConfig.set(dataType, value);
		this.playerConfig.save(this.path);
	}
	
	private double calcXPByLevel(int level) {
		// weird issue with powers
		return (level / .07) * (level / .07); 
	}
}
